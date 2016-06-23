package com.sigopt.model;

public class CategoricalValue extends StructObject {
    public CategoricalValue() {
        super();
    }

    public CategoricalValue(String name) {
        super();
        this.set("name", name);
    }

    public String getName() {
        return (String) this.get("name");
    }

    public static class Builder {
        CategoricalValue c;
        public Builder() {
            this.c = new CategoricalValue();
        }

        public CategoricalValue build() {
            return this.c;
        }

        public Builder name(String name) {
            this.c.set("name", name);
            return this;
        }
    }
}
