package com.sigopt.model;

public class Metric extends StructObject {
    public Metric() {
        super();
    }

    public Metric(String name) {
        super();
        this.set("name", name);
    }

    public String getName() {
        return (String) this.get("name");
    }

    public static class Builder {
        Metric m;

        public Builder() {
            this.m = new Metric();
        }

        public Metric build() {
            return this.m;
        }

        public Builder name(String name) {
            this.m.set("name", name);
            return this;
        }
    }
}
