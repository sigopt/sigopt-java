package com.sigopt.net;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sigopt.exception.APIException;

public class PathBuilder {

    public static String build(String path, Object obj, Map<String, Object> params) throws APIException {
        params = MapHelper.ensure(params);

        Pattern r = Pattern.compile(":([^\\/]*)");
        Matcher m = r.matcher(path);
        while(m.find()) {
            String match = m.group(1);
            int start = m.start();
            int end = start + match.length() + 1; // +1 for :
            path = path.substring(0, start) + determineValue(match, obj, params) + path.substring(end, path.length());
            m = r.matcher(path);
        }
        return path;
    }

    public static String determineValue(String match, Object obj, Map<String, Object> params) throws APIException {
        String ret = null;

        if(obj != null) {
            Field field = findField(match, obj.getClass());
            if (field != null) {
                try {
                    return field.get(obj).toString();
                } catch (IllegalAccessException e) {
                }
            }

            Method method = findMethod(match, obj.getClass());
            if (method != null) {
                try {
                    return method.invoke(obj).toString();
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e) {
                }
            }
        }

        Object param = params.get(match);
        if (param == null) {
            throw new APIException("Missing required parameter: " + match);
        } else {
            return param.toString();
        }
    }

    public static Method findMethod(String match, Class klass) {
        for (Method method : klass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(APIPathKey.class)) {
                APIPathKey apiPathKey = method.getAnnotation(APIPathKey.class);
                if (apiPathKey.key().equals(match)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Field findField(String match, Class klass) {
        for (Field field : klass.getDeclaredFields()) {
            if (field.isAnnotationPresent(APIPathKey.class)) {
                APIPathKey apiPathKey = field.getAnnotation(APIPathKey.class);
                if (apiPathKey.key().equals(match)) {
                    return field;
                }
            }
        }
        return null;
    }


}
