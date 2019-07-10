package com.sigopt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public abstract class APIObject {
    Map<String, Object> model;
    public String path_prefix;

    public APIObject() {
        this.model = new HashMap<String, Object>();
    }

    final protected Object mapGet(String key) {
        return this.model.get(key);
    }

    private Object adaptForStorage(Object value) {
        if (value instanceof APIObject) {
            return ((APIObject) value).model;
        } else if (value instanceof Collection) {
            List<Object> ret = new ArrayList<Object>();
            for (Object v: (Collection) value) {
                ret.add(this.adaptForStorage(v));
            }
            return ret;
        } else if (value instanceof Map) {
            Map<Object, Object> ret = new HashMap<Object, Object>();
            Map valueAsMap = (Map) value;
            for (Object key: valueAsMap.keySet()) {
                ret.put(key, this.adaptForStorage(valueAsMap.get(key)));
            }
            return ret;
        } else {
            return value;
        }
    }

    final <T extends Object> void set(String key, T value) {
        this.model.put(key, this.adaptForStorage(value));
    }

    final void setAll(Map<String, Object> map) {
        this.model.clear();
        if (map != null) {
            this.model.putAll(map);
        }
    }

    private static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    private static final Gson PRETTY_PRINT_GSON = new GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public static String toJson(Object value) {
        if (value instanceof APIObject) {
          return GSON.toJson(((APIObject)value).model);
        }
        return GSON.toJson(value);
    }

    public String toJson() {
        return APIObject.toJson(this);
    }

    public static Map<String, Object> fromJson(String json, Type t) {
        return GSON.fromJson(json, t);
    }

    @Override
    public String toString() {
        return String.format(
            "<%s@%s> Attributes: %s",
            this.getClass().getName(),
            System.identityHashCode(this),
            this.toJson()
        );
    }

    @Override
    public boolean equals(Object other) {
        return (
            other != null &&
            this.getClass().equals(other.getClass()) &&
            this.model.equals(((APIObject)other).model)
        );
    }

    @Override
    public int hashCode() {
        return this.model.hashCode();
    }

    public static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}

/**
 * Used for JSON objects with strictly defined fields.
 */
class StructObject extends APIObject {
    final protected Object get(String key) {
        return this.mapGet(key);
    }
}

/**
 * Used for JSON objects which can arbitrary key/value pairs.
 * Enables users to treat JSON objects as both APIObjects and Maps
 */
class MapObject<V extends Object> extends APIObject implements Map<String, V> {
    final public void clear() {
        throw new UnsupportedOperationException();
    }

    final public boolean containsKey(Object key) {
        return this.model.containsKey(key);
    }

    final public boolean containsValue(Object value) {
        return this.model.containsValue(value);
    }

    final public Set<Map.Entry<String,V>> entrySet() {
        return ((Map<String,V>) this.model).entrySet();
    }

    final public boolean equals(Object o) {
        return this.model.equals(o);
    }

    final public V get(Object key) {
        return (V) this.model.get(key);
    }

    final public int hashCode() {
        return this.model.hashCode();
    }

    final public boolean isEmpty() {
        return this.model.isEmpty();
    }

    final public Set<String> keySet() {
        return this.model.keySet();
    }

    final public V put(String key, V value) {
        throw new UnsupportedOperationException();
    }

    final public void putAll(Map<? extends String, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    final public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    final public int size() {
        return this.model.size();
    }

    final public Collection<V> values() {
        return (Collection<V>) this.model.values();
    }
}

/**
 * An extension of MapObject&lt;Object&gt; which adds some handy accessors.
 */
class RichMapObject extends MapObject<Object> {
    final public String getString(String name) {
        Object value = this.get(name);
        if (value == null) {
            throw new NullPointerException("No assignment for " + name);
        }
        return (String)value;
    }

    final public int getInteger(String name) {
        return ((Double)this.get(name)).intValue();
    }

    final public double getDouble(String name) {
        return ((Double)this.get(name));
    }
}
