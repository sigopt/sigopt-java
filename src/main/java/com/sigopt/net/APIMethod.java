package com.sigopt.net;

import com.sigopt.Sigopt;
import com.sigopt.exception.APIConnectionError;
import com.sigopt.exception.APIException;
import com.sigopt.exception.SigoptException;

import java.util.HashMap;
import java.util.Map;

public class APIMethod {

    public String method;
    public String path;
    public Map<String, Object> params = new HashMap<String, Object>();
    public Map<String, String> pathComponents = new HashMap<String, String>();
    public Map<String, String> headers = new HashMap<String, String>();
    public String data;

    public Requester.Response response;
    public SigoptException exception;

    public String clientToken;

    public APIMethod(String method, String path, Map<String, Object> params, Map<String, String> headers, String clientToken, String data, Map<String, String> pathComponents)  {
        this.clientToken = (clientToken == null) ? Sigopt.clientToken : clientToken;
        params = MapHelper.ensure(params);
        headers = MapHelper.ensure(headers);
        pathComponents = MapHelper.ensure(pathComponents);

        this.method = method.toLowerCase();
        this.path = PathBuilder.build(path, pathComponents);
        this.params = ParamsBuilder.build(params);
        this.headers = HeadersBuilder.build(headers, this.clientToken);
        this.data = data;
    }

    public APIMethod execute() throws SigoptException {
        try {
            this.response = Requester.request(this.method, url(), this.params, this.headers, this.data);
        } catch(Exception e) {
            this.exception = new APIConnectionError("An error occurred while connecting to the API.", e);
            throw this.exception;
        }

        if (response.code >= 200 && response.code < 300) {
            return this;
        } else {
            this.exception = new APIException(response.body, this);
            throw this.exception;
        }
    }

    public String url() {
        return String.format("%s/%s%s", Sigopt.getApiBase(), Sigopt.apiVersion, this.path);
    }

    public static class Builder {
        String method;
        String path;
        Map<String, Object> params;
        Map<String, String> headers;
        Map<String, String> pathComponents;
        String clientToken;
        String data;

        public Builder() {
        }

        public APIMethod build() {
            return new APIMethod(method, path, params, headers, clientToken, data, pathComponents);
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
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

        public Builder addPathComponent(String key, String value) {
            this.pathComponents = MapHelper.ensure(this.pathComponents);
            this.pathComponents.put(key, value);
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

        public Builder data(String data) {
            this.data = data;
            return this;
        }
    }
}
