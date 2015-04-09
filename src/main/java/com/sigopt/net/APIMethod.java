package com.sigopt.net;

import com.sigopt.Sigopt;
import com.sigopt.exception.APIException;

import java.util.HashMap;
import java.util.Map;

public class APIMethod {

    public String method;
    public String path;
    public Map<String, Object> params = new HashMap<String, Object>();
    public Map<String, String> headers = new HashMap<String, String>();

    public Requester.Response response;
    public APIException exception;

    public String clientToken;
    public String userToken;
    public String apiBase;

    public APIMethod(String method, String path, Map<String, Object> params, Map<String, String> headers, Object obj, String clientToken, String userToken, String apiBase) {
        this.clientToken = (clientToken == null) ? Sigopt.clientToken : clientToken;
        this.userToken = (userToken == null) ? Sigopt.userToken : userToken;
        this.apiBase = (apiBase == null) ? Sigopt.apiBase : apiBase;
        params = MapHelper.ensure(params);
        headers = MapHelper.ensure(headers);

        this.method = method.toLowerCase();
        this.path = PathBuilder.build(path, obj, params);
        this.params = ParamsBuilder.build(params, this.clientToken, this.userToken);
        this.headers = HeadersBuilder.build(headers);
    }

    public APIMethod execute() throws APIException {
        try {
            this.response = Requester.request(this.method, url(), this.params, this.headers);
        } catch(Exception e) {
            // TODO(jon): Compose an API error here.
            this.exception = new APIException("An error occurred while connecting to the API.", this, e);
            throw this.exception;
        }

        if(response.code >= 200 && response.code < 300) {
            return this;
        } else {
            this.exception = new APIException(response.body, this);
            throw this.exception;
        }
    }

    public String url() {
        return String.format("%s%s", this.apiBase, this.path);
    }

    public static class Builder {
        String method;
        String path;
        Object instance;
        Map<String, Object> params;
        Map<String, String> headers;
        String clientToken;
        String userToken;
        String apiBase;

        public Builder() {
        }

        public APIMethod build() {
            return new APIMethod(method, path, params, headers, instance, clientToken, userToken, apiBase);
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder instance(Object instance) {
            this.instance = instance;
            return this;
        }

        public Builder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder addParam(String key, Object value) {
            this.params = MapHelper.ensure(this.params);
            this.params.put(key, value);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers = MapHelper.ensure(this.headers);
            this.headers.put(key, value);
            return this;
        }

        public Builder clientToken(String clientToken) {
            this.clientToken = clientToken;
            return this;
        }

        public Builder userToken(String userToken) {
            this.userToken = userToken;
            return this;
        }

        public Builder apiBase(String apiBase) {
            this.apiBase = apiBase;
            return this;
        }

    }

}
