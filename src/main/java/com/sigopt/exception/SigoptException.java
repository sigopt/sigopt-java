package com.sigopt.exception;

public abstract class SigoptException extends Exception {
    private static final long serialVersionUID = 1L;

    public SigoptException(String message) {
        super(message, null);
    }

    public SigoptException(String message, Throwable e) {
        super(message, e);
    }

}
