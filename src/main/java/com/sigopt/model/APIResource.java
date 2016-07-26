package com.sigopt.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class APIResource {
    public static <T extends APIObject> T constructFromJson(String json, Class <T> klass) {
        T instance;
        try {
            instance = klass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> map = APIObject.fromJson(json, type);
        instance.setAll(map);
        return instance;
    }

    public static <T extends APIObject> Pagination<T> constructPaginationFromJson(
        String json,
        Class<T> subklass
    ) {
        Pagination<T> p = new Pagination<T>(subklass);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> map = APIObject.fromJson(json, type);
        p.setAll(map);
        return p;
    }
}
