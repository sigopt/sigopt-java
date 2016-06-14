package com.sigopt.net;

import com.squareup.okhttp.*;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.*;

public class Requester {

    public static final String CHARSET = "UTF-8";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient();

    public static class Response {
        public String body;
        public Integer code;

        public Response(String body, Integer code) {
            this.body = body;
            this.code = code;
        }
    }

    public static Response request(String method, String url, Map<String, Object> params, Map<String, String> headers, String data) throws Exception {
        com.squareup.okhttp.Response res = client.newCall(createRequest(method, url, params, headers, data)).execute();
        return new Response(res.body().string(), res.code());
    }

    public static Request createRequest(String method, String url, Map<String, Object> params, Map<String, String> headers, String data) throws Exception {
        Request.Builder rb = new Request.Builder();

        if (data == null) {
            data = "";
        }
        RequestBody reqBody = RequestBody.create(MEDIA_TYPE_JSON, data);
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

    static String composeUrlEncodedUrl(String url, Map<String, Object> params) {
        String urlSuffix = mapToUrlEncodedString(params);
        if(url.contains("?")) {
            url = url + "&" + urlSuffix;
        } else {
            url = url + "?" + urlSuffix;
        }
        return url;
    }

    static String mapToUrlEncodedString(Map<String, Object> map) {
        StringBuilder ret = new StringBuilder();
        List<KeyValue> list = mapToKeyValueList(map);
        if (list.isEmpty()) {
            return "";
        }
        for(KeyValue kv : list) {
            ret.append(kv.toString() + "&");
        }
        return ret.substring(0, ret.length() - 1);
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

    static List<KeyValue> mapToKeyValueList(Map<String, Object> map) {
        List<KeyValue> ret = new ArrayList<KeyValue>();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            ret.add(new KeyValue(entry.getKey(), entry.getValue().toString()));
        }
        return ret;
    }
}
