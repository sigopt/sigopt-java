package com.sigopt.net;


import com.sigopt.exception.APIException;

import java.lang.reflect.Type;
import java.util.Map;

public class APIMethodCaller<T> {

    APIMethod apiMethod;
    APIMethod.Builder apiMethodBuilder = new APIMethod.Builder();
    Class<T> klass;
    Type typeOfT;

    public APIMethodCaller(String method, String path) {
        this(method, path, null);
    }
    public APIMethodCaller(String method, String path, Class<T> klass) {
        this(method, path, null, klass);
    }
    public APIMethodCaller(String method, String path, Type typeOfT) {
        this(method, path, null, typeOfT);
    }
    public APIMethodCaller(String method, String path, Object instance, Class<T> klass) {
        this.apiMethodBuilder.method(method).path(path).instance(instance);
        this.klass = klass;
    }
    public APIMethodCaller(String method, String path, Object instance, Type typeOfT) {
        this.apiMethodBuilder.method(method).path(path).instance(instance);
        this.typeOfT = typeOfT;
    }

    public T call() throws APIException {
        this.apiMethod = apiMethodBuilder.build();
        apiMethod.execute();

        if(klass != null) {
            if (APIObject.class.isAssignableFrom(klass) || APIResource.class.isAssignableFrom(klass)) {
                return APIResource.constructFromJson(apiMethod.response.body, klass);
            } else {
                return null;
            }
        } else if(typeOfT != null) {
            return APIResource.constructTypedFromJson(apiMethod.response.body, typeOfT);
        } else {
            return null;
        }
    }

    public APIMethod getApiMethod() {
        return apiMethod;
    }

    public APIMethodCaller<T> params(Map<String, Object> params) {
        this.apiMethodBuilder.params(params);
        return this;
    }

    public APIMethodCaller<T> addParam(String key, Object value) {
        this.apiMethodBuilder.addParam(key, value);
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

    public APIMethodCaller<T> clientToken(String clientToken) {
        this.apiMethodBuilder.clientToken(clientToken);
        return this;
    }

    public APIMethodCaller<T> userToken(String userToken) {
        this.apiMethodBuilder.userToken(userToken);
        return this;
    }

    public APIMethodCaller<T> apiBase(String apiBase) {
        this.apiMethodBuilder.apiBase(apiBase);
        return this;
    }
}
