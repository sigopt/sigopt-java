package com.sigopt.model;

public class Metadata extends MapObject<String> {
    public Metadata() {
        super();
    }

    public static class Builder {
        Metadata m;

        public Builder() {
            this.m = new Metadata();
        }

        public Metadata build() {
            return this.m;
        }

        public Builder set(String key, String value) {
            this.m.set(key, value);
            return this;
        }
    }
}
