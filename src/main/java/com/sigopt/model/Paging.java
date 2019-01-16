package com.sigopt.model;

public class Paging extends StructObject {
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
