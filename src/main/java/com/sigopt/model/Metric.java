package com.sigopt.model;

import com.sigopt.net.APIObject;

public class Metric extends APIObject {
    String name;

    public Metric(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Metric setName(String name) {
        this.name = name;
        return this;
    }

    public static class Builder {
        String name;

        public Builder() {
        }

        public Metric build() {
            return new Metric(name);
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
    }
}
