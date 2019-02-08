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

    public static class Builder extends StructObjectBuilder<LinearConstraint> {
        public Builder() {
            this.obj = new LinearConstraint();
        }

        public Builder terms(List<ConstraintTerm> terms) {
            this.obj.set("terms", terms);
            return this;
        }

        public Builder threshold(double threshold) {
            this.obj.set("threshold", threshold);
            return this;
        }

        public Builder type(String type) {
            this.obj.set("type", type);
            return this;
        }
    }
}
