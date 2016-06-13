package com.sigopt.model;

import com.sigopt.net.APIObject;

import java.util.*;

public class Observation extends APIObject {
    Boolean failed;
    Double value;
    Double valueStddev;
    Integer created;
    Map<String, Object> assignments;
    Map<String, String> metadata;
    String experiment;
    String id;
    String suggestion;

    public Observation(String id) {
        this.id = id;
    }

    protected Observation(
        Boolean failed,
        Double value,
        Double valueStddev,
        Integer created,
        Map<String, Object> assignments,
        Map<String, String> metadata,
        String experiment,
        String id,
        String suggestion
    ) {
        this.failed = failed;
        this.value = value;
        this.valueStddev = valueStddev;
        this.created = created;
        this.assignments = assignments;
        this.metadata = metadata;
        this.experiment = experiment;
        this.id = id;
        this.suggestion = suggestion;
    }

    public Boolean isFailed() {
        return failed;
    }

    public Double getValue() {
        return value;
    }

    public Double getValueStddev() {
        return valueStddev;
    }

    public Integer getCreated() {
        return created;
    }

    public Map<String, Object> getAssignments() {
        return assignments;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public String getExperiment() {
        return experiment;
    }

    public String getId() {
        return id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public static class Builder {
        Boolean failed;
        Double value;
        Double valueStddev;
        Integer created;
        Map<String, Object> assignments;
        Map<String, String> metadata;
        String experiment;
        String id;
        String suggestion;

        public Builder() {
        }

        public Observation build() {
            return new Observation(
                failed,
                value,
                valueStddev,
                created,
                assignments,
                metadata,
                experiment,
                id,
                suggestion
            );
        }

        public Builder failed(Boolean failed) {
            this.failed = failed;
            return this;
        }

        public Builder value(Double value) {
            this.value = value;
            return this;
        }

        public Builder valueStddev(Double valueStddev) {
            this.valueStddev = valueStddev;
            return this;
        }

        public Builder created(Integer created) {
            this.created = created;
            return this;
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.assignments = assignments;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder experiment(String experiment) {
            this.experiment = experiment;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder suggestion(String suggestion) {
            this.suggestion = suggestion;
            return this;
        }
    }
}
