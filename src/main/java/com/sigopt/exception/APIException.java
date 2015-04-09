package com.sigopt.exception;

import com.sigopt.net.APIMethod;

public class APIException extends SigoptException {
	APIMethod apiMethod;
	private static final long serialVersionUID = 1L;

	public APIException(String message) {
		this(message, null, null);
	}

	public APIException(String message, APIMethod apiMethod) {
		this(message, apiMethod, null);
	}

	public APIException(String message, APIMethod apiMethod, Throwable e) {
		super(message, e);
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
