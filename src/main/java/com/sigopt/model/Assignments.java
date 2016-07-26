package com.sigopt.model;

public class Assignments extends RichMapObject {
    public Assignments() {
        super();
    }

    public static class Builder {
        Assignments a;

        public Builder() {
            this.a = new Assignments();
        }

        public Assignments build() {
            return this.a;
        }

        public Builder set(String key, String value) {
            this.a.set(key, value);
            return this;
        }

        public Builder set(String key, double value) {
            this.a.set(key, value);
            return this;
        }

        public Builder set(String key, int value) {
            this.a.set(key, value);
            return this;
        }
    }
}
