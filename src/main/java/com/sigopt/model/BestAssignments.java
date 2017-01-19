package com.sigopt.model;

import java.util.*;

public class BestAssignments extends StructObject {
    public BestAssignments() {
        super();
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

    public static class Builder {
        BestAssignments b;
        public Builder() {
            this.b = new BestAssignments();
        }

        public BestAssignments build(){
            return this.b;
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.b.set("assignments", assignments);
            return this;
        }

        public Builder value(double value) {
            this.b.set("value", value);
            return this;
        }

        public Builder valueStddev(double valueStddev) {
            this.b.set("value_stddev", valueStddev);
            return this;
        }

        public Builder values(List<MetricEvaluation> values) {
            this.b.set("values", values);
            return this;
        }

    }
}
