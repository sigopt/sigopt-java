package com.sigopt.model;

import java.util.ArrayList;
import java.util.List;

public class LinearConstraint extends StructObject {
    public LinearConstraint() {
        super();
    }

    public List<ConstraintTerm> getTerms() {
        return Utils.mergeIntoList(new ArrayList<ConstraintTerm>(), this.get("terms"), ConstraintTerm.class);
    }

    public Double getThreshold() {
        return (Double) this.get("threshold");
    }

    public String getType() {
        return (String) this.get("type");
    }

    public static class Builder {
        LinearConstraint c;

        public Builder() {
            this.c = new LinearConstraint();
        }

        public LinearConstraint build() {
            return this.c;
        }

        public Builder terms(List<ConstraintTerm> terms) {
            this.c.set("terms", terms);
            return this;
        }

        public Builder threshold(double threshold) {
            this.c.set("threshold", threshold);
            return this;
        }

        public Builder type(String type) {
            this.c.set("type", type);
            return this;
        }
    }
}
