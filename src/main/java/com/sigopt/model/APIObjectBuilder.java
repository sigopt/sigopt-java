package com.sigopt.model;

public class APIObjectBuilder<T extends StructObject> {
    T obj;

    final public T build() {
        return this.obj;
    }

    final APIObjectBuilder<T> set(String key, Object value) {
        this.obj.set(key, value);
        return this;
    }
}
