package com.sigopt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class APIObject {
    Map<String, Object> model;

    public APIObject() {
        this.model = new HashMap<String, Object>();
    }

    Object get(String key) {
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

    <T extends Object> void set(String key, T value) {
        this.model.put(key, this.adaptForStorage(value));
    }

    void setAll(Map<String, Object> map) {
        this.model.clear();
        if (map != null) {
            this.model.putAll(map);
        }
    }

    public static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public String toJson() {
        return GSON.toJson(this.model);
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

    public boolean equals(Object other) {
        return (
            other != null &&
            this.getClass().equals(other.getClass()) &&
            this.model.equals(((APIObject)other).model)
        );
    }

    public static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}
