package com.sigopt.net;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sigopt.exception.APIException;

class PathBuilder {
    public static String build(String path, Map<String, String> params) throws APIException {
        params = MapHelper.ensure(params);

        Pattern r = Pattern.compile(":([^\\/]*)");
        Matcher m = r.matcher(path);
        while(m.find()) {
            String match = m.group(1);
            int start = m.start();
            int end = start + match.length() + 1; // +1 for :
            path = path.substring(0, start) + determineValue(match, params) + path.substring(end, path.length());
            m = r.matcher(path);
        }
        return path;
    }

    public static String determineValue(String match, Map<String, String> params) throws APIException {
        String param = params.get(match);
        if (param == null) {
            throw new APIException("Missing required parameter: " + match);
        }
        return param;
    }
}
