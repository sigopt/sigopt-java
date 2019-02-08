package com.sigopt.model;

import java.util.*;

public class BestAssignments extends StructObject {
    public BestAssignments() {
        super();
    }

    public String getID() {
        return (String) this.get("id");
    }

    public Double getValue() {
        return (Double) this.get("value");
    }

    public Double getValueStddev() {
        return (Double) this.get("value_stddev");
    }

    public List<MetricEvaluation> getValues() {
      return Utils.mergeIntoList(new ArrayList<MetricEvaluation>(), this.get("values"), MetricEvaluation.class);
    }

    public Assignments getAssignments() {
        return Utils.mergeInto(new Assignments(), this.get("assignments"));
    }

    public static class Builder extends StructObjectBuilder<BestAssignments> {
        public Builder() {
            this.obj = new BestAssignments();
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.obj.set("assignments", assignments);
            return this;
        }

        public Builder id(String id) {
            this.obj.set("id", id);
            return this;
        }

        public Builder value(double value) {
            this.obj.set("value", value);
            return this;
        }

        public Builder valueStddev(double valueStddev) {
            this.obj.set("value_stddev", valueStddev);
            return this;
        }

        public Builder values(List<MetricEvaluation> values) {
            this.obj.set("values", values);
            return this;
        }

    }
}
