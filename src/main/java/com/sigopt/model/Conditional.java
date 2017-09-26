package com.sigopt.model;

import java.util.List;

public class Conditional extends StructObject {
    public Conditional() {
        super();
    }

    public String getName() {
        return (String) this.get("name");
    }

    public List<String> getValues() {
        return (List<String>) this.get("values");
    }

    public static class Builder {
        Conditional c;

        public Builder() {
            this.c = new Conditional();
        }

        public Conditional build() {
            return this.c;
        }

        public Builder name(String name) {
            this.c.set("name", name);
            return this;
        }

        public Builder values(List<String> values) {
            this.c.set("values", values);
            return this;
        }
    }
}
