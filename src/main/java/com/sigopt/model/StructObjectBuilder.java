package com.sigopt.model;

public class StructObjectBuilder<T extends StructObject> {
    T obj;

    final public T build() {
        return this.obj;
    }

    final public StructObjectBuilder<T> set(String key, Object value) {
        this.obj.set(key, value);
        return this;
    }
}
