package com.sigopt.model;

import com.sigopt.net.APIMethod;

import java.util.HashMap;
import java.util.Map;

public class Suggestion extends StructObject {
    public Suggestion() {
        super();
    }

    public Suggestion(String id) {
        super();
        this.set("id", id);
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

    public String getState() {
        return (String) this.get("state");
    }

    public static class Builder {
        Suggestion s;
        public Builder() {
            this.s = new Suggestion();
        }

        public Suggestion build() {
            return this.s;
        }

        public Builder created(Integer created) {
            this.s.set("created", created);
            return this;
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.s.set("assignments", assignments);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.s.set("metadata", metadata);
            return this;
        }

        public Builder experiment(String experiment) {
            this.s.set("experiment", experiment);
            return this;
        }

        public Builder id(String id) {
            this.s.set("id", id);
            return this;
        }

        public Builder state(String state) {
            this.s.set("state", state);
            return this;
        }
    }
}
