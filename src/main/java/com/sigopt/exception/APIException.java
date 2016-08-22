package com.sigopt.exception;

import com.sigopt.net.APIMethod;

public class APIException extends SigoptException {
    APIMethod apiMethod;

    public APIException(String message, APIMethod apiMethod) {
        super(message);
        this.apiMethod = apiMethod;
    }

    public APIMethod getApiMethod() {
        return apiMethod;
    }

    public String getBody() {
        return apiMethod.response.body;
    }

    public Integer getCode() {
        return apiMethod.response.code;
    }
}
