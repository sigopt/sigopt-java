package com.sigopt.model;

import com.sigopt.exception.APIException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pagination<T extends APIObject> extends StructObject {
    Class<T> klass;

    public Pagination(Class<T> klass) {
        super();
        this.klass = klass;
    }

    public Integer getCount() {
        return Utils.asInteger(this.get("count"));
    }

    public List<T> getData() {
        return Utils.mergeIntoList(new ArrayList<T>(), this.get("data"), this.klass);
    }

    public Paging getPaging() {
        return Utils.mergeInto(new Paging(), this.get("paging"));
    }
}

