package com.sigopt.net;

public class BoundObject {
    private String prefix;
    public BoundObject(String prefix) {
        this.prefix = prefix;
    }

    public String prefix() {
        return prefix;
    }
}
