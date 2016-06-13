package com.sigopt.model;

import com.sigopt.net.APIMethod;
import com.sigopt.net.APIResource;
import com.sigopt.net.MapHelper;

import java.util.HashMap;
import java.util.Map;

public class Suggestion extends APIResource {
    Integer created;
    Map<String, Object> assignments;
    Map<String, String> metadata;
    String experiment;
    String id;
    String state;

    public Suggestion(
        Integer created,
        Map<String, Object> assignments,
        Map<String, String> metadata,
        String experiment,
        String id,
        String state
    ) {
        this.created = created;
        this.assignments = assignments;
        this.metadata = metadata;
        this.experiment = experiment;
        this.id = id;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public static class Builder {
        Integer created;
        Map<String, Object> assignments;
        Map<String, String> metadata;
        String experiment;
        String id;
        String state;

        public Builder() {
        }

        public Suggestion build() {
            return new Suggestion(created, assignments, metadata, experiment, id, state);
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

        public Builder state(String state) {
            this.state = state;
            return this;
        }
    }
}
