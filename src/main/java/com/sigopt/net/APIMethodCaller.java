package com.sigopt.net;

import com.sigopt.exception.SigoptException;
import com.sigopt.model.APIObject;
import com.sigopt.model.APIResource;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class APIMethodCaller<T extends APIObject> {
    APIMethod apiMethod;
    APIMethod.Builder apiMethodBuilder = new APIMethod.Builder();
    Class<T> klass;
    Type typeOfT;
    String path;

    public APIMethodCaller(String method, String path) {
        this(method, path, null);
    }
    public APIMethodCaller(String method, String path, Class<T> klass) {
        this.apiMethodBuilder.method(method).path(path);
        this.path = path;
        this.klass = klass;
    }

    public T call() throws SigoptException {
        this.apiMethod = apiMethodBuilder.build();
        apiMethod.execute();
        return this.processBody(apiMethod.response.body);
    }

    protected T processBody(String body) {
        return APIResource.constructFromJson(apiMethod.response.body, this.klass, this.path);
    }

    public APIMethod getApiMethod() {
        return apiMethod;
    }

    public Map<String, Object> getParams() {
        return Collections.unmodifiableMap(MapHelper.ensure(this.apiMethodBuilder.params));
    }

    public APIMethodCaller<T> data(APIObject data) {
        return this.data(data.toJson());
    }

    public APIMethodCaller<T> data(String data) {
        this.apiMethodBuilder.data(data);
        return this;
    }

    public APIMethodCaller<T> params(Map<String, Object> params) {
        this.apiMethodBuilder.params(params);
        return this;
    }

    public APIMethodCaller<T> addParam(String key, Object value) {
        this.apiMethodBuilder.addParam(key, value);
        return this;
    }

    public APIMethodCaller<T> removeParam(String key) {
        this.apiMethodBuilder.removeParam(key);
        return this;
    }

    public APIMethodCaller<T> addPathComponent(String key, String value) {
        this.apiMethodBuilder.addPathComponent(key, value);
        return this;
    }

    public APIMethodCaller<T> headers(Map<String, String> headers) {
        this.apiMethodBuilder.headers(headers);
        return this;
    }

    public APIMethodCaller<T> addHeader(String key, String value) {
        this.apiMethodBuilder.addHeader(key, value);
        return this;
    }
}
