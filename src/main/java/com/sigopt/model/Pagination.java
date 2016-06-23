package com.sigopt.model;

import com.sigopt.exception.APIException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pagination<T extends APIObject> extends APIObject {
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

class Paging extends StructObject {
    public Paging() {
        super();
    }

    public Paging(String before, String after) {
        super();
        this.set("before", before);
        this.set("after", after);
    }

    public String getBefore() {
        return (String) this.get("before");
    }

    public String getAfter() {
        return (String) this.get("after");
    }
}
