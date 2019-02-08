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

    public static class Builder extends StructObjectBuilder<CategoricalValue> {
        public Builder() {
            this.obj = new CategoricalValue();
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }
    }
}
