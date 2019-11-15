package com.sigopt.model;

import com.sigopt.net.APIMethod;

import java.util.Map;

public class QueuedSuggestion extends StructObject {
    public QueuedSuggestion() {
        super();
    }

    public QueuedSuggestion(String id) {
        super();
        this.set("id", id);
    }

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public Assignments getAssignments() {
        return Utils.mergeInto(new Assignments(), this.get("assignments"));
    }

    public String getExperiment() {
        return (String) this.get("experiment");
    }

    public String getId() {
        return (String) this.get("id");
    }

    public Task getTask() {
        return Utils.mergeInto(new Task(), this.get("task"));
    }

    public static class Builder extends APIObjectBuilder<QueuedSuggestion> {
        public Builder() {
            this.obj = new QueuedSuggestion();
        }

        public Builder created(int created) {
            this.obj.set("created", created);
            return this;
        }

        public Builder assignments(Map<String, Object> assignments) {
            this.obj.set("assignments", assignments);
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

        public Builder task(Task task) {
            this.obj.set("task", task);
            return this;
        }
    }
}
