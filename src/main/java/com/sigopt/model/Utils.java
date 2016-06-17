package com.sigopt.model;

import java.util.List;
import java.util.Map;

class Utils {
    public static Integer asInteger (Object jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Double d = (Double) jsonObject;
        return d.intValue();
    }

    public static <T extends APIObject> T mergeInto(T t, Object jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Map<String, Object> toSet = (Map<String, Object>) jsonObject;
        t.setAll(toSet);
        return t;
    }

    public static <T extends APIObject> List<T> mergeIntoList(List<T> list, Object jsonObject, Class<T> klass) {
        List<Map<String, Object>> storedList = (List<Map<String, Object>>) jsonObject;
        if (storedList == null) {
            return null;
        }
        for (Map<String, Object> storedObj : storedList) {
            T t;
            try {
                t = klass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            t.setAll(storedObj);
            list.add(t);
        }
        return list;
    }
}
