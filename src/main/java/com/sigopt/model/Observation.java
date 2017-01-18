package com.sigopt.model;

import java.util.*;

public class Observation extends StructObject {
    public Observation() {
        super();
    }

    public Observation(String id) {
        super();
        this.set("id", id);
    }

    public Boolean isFailed() {
        return (Boolean) this.get("failed");
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

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public Assignments getAssignments() {
        return Utils.mergeInto(new Assignments(), this.get("assignments"));
    }

    public Metadata getMetadata() {
        return Utils.mergeInto(new Metadata(), this.get("metadata"));
    }

    public String getExperiment() {
        return (String) this.get("experiment");
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getSuggestion() {
        return (String) this.get("suggestion");
    }

    public static class Builder {
        Observation o;

        public Builder() {
            this.o = new Observation();
        }

        public Observation build() {
            return this.o;
        }

        public Builder failed(boolean failed) {
            this.o.set("failed", failed);
            return this;
        }

        public Builder value(double value) {
            this.o.set("value", value);
            return this;
        }

        public Builder valueStddev(double valueStddev) {
            this.o.set("value_stddev", valueStddev);
            return this;
        }

        public Builder values(List<Value> values) {
            this.o.set("values", values);
            return this;
        }

        public Builder created(int created) {
            this.o.set("created", created);
            return this;
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.o.set("assignments", assignments);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.o.set("metadata", metadata);
            return this;
        }

        public Builder experiment(String experiment) {
            this.o.set("experiment", experiment);
            return this;
        }

        public Builder id(String id) {
            this.o.set("id", id);
            return this;
        }

        public Builder suggestion(String suggestion) {
            this.o.set("suggestion", suggestion);
            return this;
        }
    }
}
