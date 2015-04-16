package com.sigopt.net;

import com.sigopt.Sigopt;
import com.sigopt.exception.AuthenticationException;
import com.squareup.okhttp.Request;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class HeadersBuilder {

    public static Map<String, String> build(Map<String, String> headers) throws AuthenticationException {
        return build(headers, null, null);
    }

    public static Map<String, String> build(Map<String, String> headers, String apiKey) throws AuthenticationException {
        return build(headers, apiKey, null);
    }

    public static Map<String, String> build(Map<String, String> headers, String apiKey, String authKey) throws AuthenticationException {
        Map<String, String> ret = new HashMap<String, String>();
        ret = MapHelper.merge(ret, defaultHeaders());

        if(authKey != null && !authKey.isEmpty()) {
            ret = MapHelper.merge(ret, customAuthHeader(authKey, apiKey));
        } else if(apiKey != null && !apiKey.isEmpty()) {
            ret = MapHelper.merge(ret, basicAuthHeader(apiKey));
        }
        if(headers != null) {
            ret.putAll(headers);
        }

        return ret;
    }

    public static Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<String, String>();

        String userAgent = "Sigopt/" + Sigopt.apiVersion + " JavaBindings/" + Sigopt.VERSION;
        headers.put("User-Agent", userAgent);

        return headers;
    }

    public static Map<String, String> customAuthHeader(String authKey, String apiKey) {
        Map<String, String> ret = new HashMap<String, String>();
        ret.put(authKey, apiKey);
        return ret;
    }

    public static Map<String, String> basicAuthHeader(String apiKey) throws AuthenticationException {
        if(apiKey == null) {
            throw new AuthenticationException("An API key is required but was never set. Please see " + Sigopt.DOCS_URL + " for more information.");
        }

        Map<String, String> ret = new HashMap<String, String>();
        byte[] apiKeyBytes = String.format("%s:", apiKey).getBytes();
        String base64Key = Base64.getEncoder().encodeToString(apiKeyBytes);
        ret.put("Authorization", base64Key);
        return ret;
    }

}
