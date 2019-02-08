package com.sigopt.model;

public class ConstraintTerm extends StructObject {
    public ConstraintTerm() {
        super();
    }

    public Double getWeight() {
        return (Double) this.get("weight");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public static class Builder extends StructObjectBuilder<ConstraintTerm> {
        public Builder() {
            this.obj = new ConstraintTerm();
        }

        public Builder weight(double weight) {
            this.obj.set("weight", weight);
            return this;
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }
    }
}
