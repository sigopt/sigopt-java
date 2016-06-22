package com.sigopt.model;

class BoundObject {
    private String prefix;
    public BoundObject(String prefix) {
        this.prefix = prefix;
    }

    public String prefix() {
        return prefix;
    }
}
