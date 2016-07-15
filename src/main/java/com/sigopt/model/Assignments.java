package com.sigopt.model;

public class Assignments extends MapObject<Object> {
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

        public Builder set(String key, Integer value) {
            this.a.set(key, value);
            return this;
        }

        public Builder set(String key, Double value) {
            this.a.set(key, value);
            return this;
        }
    }

    public String getCategorical(String name) {
        return (String)this.get(name);
    }

    public int getInteger(String name) {
        return ((Double)this.get(name)).intValue();
    }

    public double getDouble(String name) {
        return ((Double)this.get(name));
    }

}
