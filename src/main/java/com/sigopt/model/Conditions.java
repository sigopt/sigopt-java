package com.sigopt.model;

import java.util.List;

public class Conditions extends MapObject<List<String>> {
    public Conditions() {
        super();
    }

    public static class Builder {
        Conditions c;

        public Builder() {
            this.c = new Conditions();
        }

        public Conditions build() {
            return this.c;
        }

        public Builder set(String key, List<String> value) {
            this.c.set(key, value);
            return this;
        }
    }
}
