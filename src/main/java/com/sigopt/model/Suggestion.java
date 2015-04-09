package com.sigopt.model;

import com.sigopt.net.APIMethod;
import com.sigopt.net.APIResource;
import com.sigopt.net.MapHelper;

import java.util.HashMap;
import java.util.Map;

public class Suggestion extends APIResource {
    Map<String, Object> assignments;
    Double expectedImprovement;

    public Suggestion(Map<String, Object> assignments, Double expectedImprovement) {
        this.assignments = assignments;
        this.expectedImprovement = expectedImprovement;
    }

    public Map<String, Object> getAssignments() {
        return assignments;
    }

    public Double getExpectedImprovement() {
        return expectedImprovement;
    }

    public static class Builder {
        Map<String, Object> assignments = new HashMap<String, Object>();
        Double expectedImprovement;

        public Builder() {
        }

        public Suggestion build() {
            return new Suggestion(assignments, expectedImprovement);
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.assignments = assignments;
            return this;
        }

        public Builder addAssignment(String key, Object value) {
            this.assignments.put(key, value);
            return this;
        }

        public Builder expectedImprovement(Double expectedImprovement) {
            this.expectedImprovement = expectedImprovement;
            return this;
        }
    }
}
