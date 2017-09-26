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

    public static class Builder {
        ConstraintTerm c;

        public Builder() {
            this.c = new ConstraintTerm();
        }

        public ConstraintTerm build() {
            return this.c;
        }

        public Builder weight(double weight) {
            this.c.set("weight", weight);
            return this;
        }

        public Builder name(String name) {
            this.c.set("name", name);
            return this;
        }
    }
}
