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

    public Task getTask() {
        return Utils.mergeInto(new Task(), this.get("task"));
    }

    public static class Builder extends APIObjectBuilder<Observation> {
        public Builder() {
            this.obj = new Observation();
        }

        public Builder failed(boolean failed) {
            this.obj.set("failed", failed);
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

        public Builder created(int created) {
            this.obj.set("created", created);
            return this;
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.obj.set("assignments", assignments);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.obj.set("metadata", metadata);
            return this;
        }

        public Builder experiment(String experiment) {
            this.obj.set("experiment", experiment);
            return this;
        }

        public Builder id(String id) {
            this.obj.set("id", id);
            return this;
        }

        public Builder suggestion(String suggestion) {
            this.obj.set("suggestion", suggestion);
            return this;
        }

        public Builder task(Task task) {
            this.obj.set("task", task);
            return this;
        }

        public Builder trainingRun(String trainingRun) {
            this.obj.set("training_run", trainingRun);
            return this;
        }

    }
}
