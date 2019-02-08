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

    public Integer getFoldIndex() {
        return Utils.asInteger(this.get("fold_index"));
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

    public Task getTask() {
        return Utils.mergeInto(new Task(), this.get("task"));
    }

    public static class Builder extends StructObjectBuilder<Suggestion> {
        public Builder() {
            this.obj = new Suggestion();
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

        public Builder state(String state) {
            this.obj.set("state", state);
            return this;
        }

        public Builder foldIndex(int foldIndex) {
            this.obj.set("fold_index", foldIndex);
            return this;
        }

        public Builder task(Task task) {
            this.obj.set("task", task);
            return this;
        }
    }
}
