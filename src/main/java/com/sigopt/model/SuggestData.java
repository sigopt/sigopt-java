package com.sigopt.model;

import com.sigopt.net.APIResource;

import java.util.HashMap;
import java.util.Map;

public class SuggestData extends APIResource {
    Map<String, Object> assignments;

    public SuggestData(Map<String, Object> assignments) {
        this.assignments = assignments;
    }

    public Map<String, Object> getAssignments() {
        return assignments;
    }

    public static class Builder {
        Map<String, Object> assignments = new HashMap<String, Object>();

        public Builder() {
        }

        public SuggestData build() {
            return new SuggestData(assignments);
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.assignments = assignments;
            return this;
        }
    }
}
