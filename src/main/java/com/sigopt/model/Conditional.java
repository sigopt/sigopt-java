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

    public static class Builder extends APIObjectBuilder<Conditional> {
        public Builder() {
            this.obj = new Conditional();
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder values(List<String> values) {
            this.obj.set("values", values);
            return this;
        }
    }
}
