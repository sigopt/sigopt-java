package com.sigopt.exception;

public class APIConnectionError extends SigoptException {
    public APIConnectionError(String message) {
        super(message);
    }

    public APIConnectionError(String message, Throwable e) {
        super(message, e);
    }
}
