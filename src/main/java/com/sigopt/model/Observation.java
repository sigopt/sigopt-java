package com.sigopt.model;

import com.sigopt.net.APIObject;

import java.util.*;

public class Observation extends APIObject {
    Map<String, Object> assignments;
    Double value;
    Double valueStddev;
    Boolean failed;
    String cohortId;

    public Observation(Map<String, Object> assignments, Double value, Double valueStddev, Boolean failed, String cohortId) {
        this.assignments = assignments;
        this.value = value;
        this.valueStddev = valueStddev;
        this.failed = failed;
        this.cohortId = cohortId;
    }

    public Map<String, Object> getAssignments() {
        return assignments;
    }

    public Double getValue() {
        return value;
    }

    public Double getValueStddev() {
        return valueStddev;
    }

    public Boolean isFailed() {
        return failed;
    }

    public String getCohortId() {
        return cohortId;
    }

    public static class Builder {
        Map<String, Object> assignments = new HashMap<String, Object>();
        Double value;
        Double valueStddev;
        Boolean failed;
        String cohortId;

        public Builder() {
        }

        public Observation build() {
            return new Observation(assignments, value, valueStddev, failed, cohortId);
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.assignments = assignments;
            return this;
        }

        public Builder addAssignment(String key, Object value) {
            this.assignments.put(key, value);
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

        public Builder failed(Boolean failed) {
            this.failed = failed;
            return this;
        }

        public Builder cohortId(String cohortId) {
            this.cohortId = cohortId;
            return this;
        }
    }
}
