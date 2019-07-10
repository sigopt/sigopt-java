package com.sigopt.model;

import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.PaginatedAPIMethodCaller;

public class Subresource<T extends APIObject> extends BoundObject {
    String name;
    Class<T> klass;

    public Subresource(String prefix, String name, Class<T> klass) {
        super(prefix);
        this.name = name;
        this.klass = klass;
    }

    public APIMethodCaller<T> fetch() {
        return new APIMethodCaller<T>("get", this.prefix() + "/" + this.name + "/:id", klass);
    }

    public APIMethodCaller<T> fetch(String id) {
        return this.fetch().addPathComponent("id", id);
    }

    public PaginatedAPIMethodCaller<T> list() {
        return new PaginatedAPIMethodCaller<T>("get", this.prefix() + "/" + this.name, klass);
    }

    public APIMethodCaller<T> create() {
        return new APIMethodCaller<T>("post", this.prefix() + "/" + this.name, klass);
    }

    public APIMethodCaller<T> create(T o) {
        return this.create().data(o);
    }

    public APIMethodCaller<T> update() {
        return new APIMethodCaller<T>("put", this.prefix() + "/" + this.name + "/:id", klass);
    }

    public APIMethodCaller<T> update(String id) {
        return this.update().addPathComponent("id", id);
    }

    public APIMethodCaller<T> update(String id, T o) {
        return this.update(id).data(o);
    }

    public APIMethodCaller<VoidObject> deleteList() {
        return new APIMethodCaller<VoidObject>("delete", this.prefix() + "/" + this.name, VoidObject.class);
    }

    public APIMethodCaller<VoidObject> delete() {
        return new APIMethodCaller<VoidObject>("delete", this.prefix() + "/" + this.name + "/:id", VoidObject.class);
    }

    public APIMethodCaller<VoidObject> delete(String id) {
        return this.delete().addPathComponent("id", id);
    }
}