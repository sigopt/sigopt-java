package com.sigopt.net;

import com.squareup.okhttp.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.*;

public class Requester {

    public static final String CHARSET = "UTF-8";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client = new OkHttpClient();

    public static class Response {
        public String body;
        public Integer code;

        public Response(String body, Integer code) {
            this.body = body;
            this.code = code;
        }
    }

    public static Response request(String method, String url, Map params, Map<String, String> headers) throws Exception {
        com.squareup.okhttp.Response res = client.newCall(createRequest(method, url, params, headers)).execute();
        return new Response(res.body().string(), res.code());
    }

    public static Request createRequest(String method, String url, Map params, Map<String, String> headers) throws Exception {
        Request.Builder rb = new Request.Builder();

        RequestBody reqBody = composeRequestBody(params);
        method = method.toLowerCase();
        if(method.equals("get")) {
            rb = rb.get();
            url = composeUrlEncodedUrl(url, params);
        } else if(method.equals("delete")) {
            rb = rb.delete();
            url = composeUrlEncodedUrl(url, params);
        } else if (method.equals("post")) {
            rb = rb.post(reqBody);
        } else if(method.equals("put")) {
            rb = rb.put(reqBody);
        } else {
            throw new Exception("Invalid Method Type: " + method);
        }

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            rb = rb.header(entry.getKey(), entry.getValue());
        }

        Request req = rb.url(url).build();
        return req;
    }

    static String composeUrlEncodedUrl(String url, Map params) {
        String urlSuffix = mapToUrlEncodedString(params);
        if(url.contains("?")) {
            url = url + "&" + urlSuffix;
        } else {
            url = url + "?" + urlSuffix;
        }
        return url;
    }

    static RequestBody composeRequestBody(Map params) {
        if(params.size() == 0) {
            return null;
        }

        FormEncodingBuilder formBodyBuilder = new FormEncodingBuilder();
        List<KeyValue> list = mapToKeyValueList(params);
        for(KeyValue kv : list) {
            formBodyBuilder = formBodyBuilder.add(kv.key, kv.value);
        }
        return formBodyBuilder.build();
    }

    static String mapToUrlEncodedString(Map map) {
        StringBuilder ret = new StringBuilder();
        List<KeyValue> list = mapToKeyValueList(map);
        for(KeyValue kv : list) {
            ret.append(kv.toString() + "&");
        }
        return ret.substring(0, ret.length() - 1);
    }

    static List<Object> arrayToList(Object array) {
        Class arrayKlass = array.getClass().getComponentType();
        if (arrayKlass.isPrimitive()) {
            List ar = new ArrayList();
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                ar.add(Array.get(array, i));
            }
            return ar;
        }
        else {
            return Arrays.asList((Object[]) array);
        }
    }

    static class KeyValue {
        public String key;
        public String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            String.format("%s=%s", urlEncode(key), urlEncode(value));
            return key + "=" + value;
        }

        public String urlEncode(String str) {
            try {
                return URLEncoder.encode(str, CHARSET);
            } catch(UnsupportedEncodingException e) {
                return str;
            }
        }

    }

    static List<KeyValue> mapToKeyValueList(Object obj) {
        return mapToKeyValueList(obj, "");
    }

    static List<KeyValue> mapToKeyValueList(Object obj, String prefix) {
        List<KeyValue> ret = new ArrayList<KeyValue>();

        if(obj.getClass().isArray() || obj instanceof Collection) {
            Collection<Object> list;
            if(obj instanceof Collection) {
                list = (Collection)obj;
            } else {
                list = arrayToList(obj);
            }
            for(Object o : list) {
                ret.addAll(mapToKeyValueList(o, prefix + "[]"));
            }
        } else if(obj instanceof Map) {
            Map<Object, Object> map = (Map<Object, Object>)obj;
            for(Map.Entry<Object, Object> entry : map.entrySet()) {
                String newPrefix = prefix.length() == 0 ? entry.getKey().toString() : prefix + "[" + entry.getKey().toString() + "]";
                ret.addAll(mapToKeyValueList(entry.getValue(), newPrefix));
            }
        } else {
            ret.add(new KeyValue(prefix, obj.toString()));
        }

        return ret;
    }

}
