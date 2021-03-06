package com.sigopt.model;

import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.PaginatedAPIMethodCaller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Experiment extends StructObject {
    public Experiment() {
        super();
    }

    public Experiment(String id) {
        super();
        this.set("id", id);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getUser() {
        return (String) this.get("user");
    }

    public String getType() {
        return (String) this.get("type");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public List<Parameter> getParameters() {
        return Utils.mergeIntoList(new ArrayList<Parameter>(), this.get("parameters"), Parameter.class);
    }

    public List<Metric> getMetrics() {
        return Utils.mergeIntoList(new ArrayList<Metric>(), this.get("metrics"), Metric.class);
    }

    public Metric getMetric() {
        return Utils.mergeInto(new Metric(), this.get("metric"));
    }

    public Progress getProgress() {
        return Utils.mergeInto(new Progress(), this.get("progress"));
    }

    public Metadata getMetadata() {
        return Utils.mergeInto(new Metadata(), this.get("metadata"));
    }

    public String getClient() {
        return (String) this.get("client");
    }

    public String getProject() {
        return (String) this.get("project");
    }

    public String getState() {
        return (String) this.get("state");
    }

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public Integer getUpdated() {
        return Utils.asInteger(this.get("updated"));
    }

    public Integer getObservationBudget() {
        return Utils.asInteger(this.get("observation_budget"));
    }

    public Integer getNumSolutions() {
        return Utils.asInteger(this.get("num_solutions"));
    }

    public Boolean getDevelopment() {
        return (Boolean) this.get("development");
    }

    public Integer getParallelBandwidth() {
        return Utils.asInteger(this.get("parallel_bandwidth"));
    }

    public List<LinearConstraint> getLinearConstraints() {
        return Utils.mergeIntoList(new ArrayList<LinearConstraint>(), this.get("linear_constraints"), LinearConstraint.class);
    }

    public List<Conditional> getConditionals() {
        return Utils.mergeIntoList(new ArrayList<Conditional>(), this.get("conditionals"), Conditional.class);
    }

    public List<Task> getTasks() {
        return Utils.mergeIntoList(new ArrayList<Task>(), this.get("tasks"), Task.class);
    }

    public TrainingMonitor getTrainingMonitor() {
        return Utils.mergeInto(new TrainingMonitor(), this.get("training_monitor"));
    }

    public static APIMethodCaller<Experiment> fetch() {
        return new APIMethodCaller<Experiment>("get", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> fetch(String id) {
        return Experiment.fetch().addPathComponent("id", id);
    }

    public static PaginatedAPIMethodCaller<Experiment> list() {
        return new PaginatedAPIMethodCaller<Experiment>("get", "/experiments", Experiment.class);
    }

    public static APIMethodCaller<Experiment> create() {
        return new APIMethodCaller<Experiment>("post", "/experiments", Experiment.class);
    }

    public static APIMethodCaller<Experiment> create(Experiment e) {
        return Experiment.create().data(e);
    }

    public static APIMethodCaller<Experiment> update() {
        return new APIMethodCaller<Experiment>("put", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> update(String id) {
        return Experiment.update().addPathComponent("id", id);
    }

    public static APIMethodCaller<Experiment> update(String id, Experiment e) {
        return Experiment.update(id).data(e);
    }

    public static APIMethodCaller<VoidObject> delete() {
        return new APIMethodCaller<VoidObject>("delete", "/experiments/:id", VoidObject.class);
    }

    public static APIMethodCaller<VoidObject> delete(String id) {
        return Experiment.delete().addPathComponent("id", id);
    }

    /**
    *   PropertyResource is used for a resource linked strongly to another e.g.
    *   StoppingCriteria isn't a full object with its own id but still must be
    *   retrieved as its own resource separate from Experiment
    */
    public static class PropertyResource<T extends APIObject> extends BoundObject {
        String name;
        Class<T> klass;

        public PropertyResource(String prefix, String name, Class<T> klass) {
            super(prefix);
            this.name = name;
            this.klass = klass;
        }

        public APIMethodCaller<T> fetch() {
            return new APIMethodCaller<T>("get", this.prefix() + "/" + this.name, klass);
        }
    }

    public static class Subresource<T extends APIObject> extends com.sigopt.model.Subresource<T> {
        public Subresource(String prefix, String name, Class<T> klass) {
            super(prefix, name, klass);
        }
    }

    public Subresource<Observation> observations() {
        return new Subresource<Observation>("/experiments/" + this.getId(), "observations", Observation.class);
    }

    public PropertyResource<StoppingCriteria> stoppingCriteria() {
        return new PropertyResource<StoppingCriteria>("/experiments/" + this.getId(), "stopping_criteria", StoppingCriteria.class);
    }

    public Subresource<Suggestion> suggestions() {
        return new Subresource<Suggestion>("/experiments/" + this.getId(), "suggestions", Suggestion.class);
    }

    public Subresource<QueuedSuggestion> queuedSuggestions() {
        return new Subresource<QueuedSuggestion>("/experiments/" + this.getId(), "queued_suggestions", QueuedSuggestion.class);
    }

    public Subresource<TrainingRun> trainingRuns() {
        return new Subresource<TrainingRun>("/experiments/" + this.getId(), "training_runs", TrainingRun.class);
    }

    public Subresource<Token> tokens() {
        return new Subresource<Token>("/experiments/" + this.getId(), "tokens", Token.class);
    }

    public Subresource<BestAssignments> bestAssignments() {
        return new Subresource<BestAssignments>("/experiments/" + this.getId(), "best_assignments", BestAssignments.class);
    }

    public static class Builder extends APIObjectBuilder<Experiment> {
        public Builder() {
            this.obj = new Experiment();
        }

        public Builder created(int created) {
            this.obj.set("created", created);
            return this;
        }

        public Builder parameters(List<Parameter> parameters) {
            this.obj.set("parameters", parameters);
            return this;
        }

        public Builder metrics(List<Metric> metrics) {
            this.obj.set("metrics", metrics);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.obj.set("metadata", metadata);
            return this;
        }

        public Builder metric(Metric metric) {
            this.obj.set("metric", metric);
            return this;
        }

        public Builder progress(Progress progress) {
            this.obj.set("progress", progress);
            return this;
        }

        public Builder client(String client) {
            this.obj.set("client", client);
            return this;
        }

        public Builder project(String project) {
            this.obj.set("project", project);
            return this;
        }

        public Builder id(String id) {
            this.obj.set("id", id);
            return this;
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder state(String state) {
            this.obj.set("state", state);
            return this;
        }

        public Builder type(String type) {
            this.obj.set("type", type);
            return this;
        }

        public Builder observationBudget(int budget) {
            this.obj.set("observation_budget", budget);
            return this;
        }

        public Builder numSolutions(int numSolutions) {
            this.obj.set("num_solutions", numSolutions);
            return this;
        }

        public Builder development(boolean development) {
            this.obj.set("development", development);
            return this;
        }

        public Builder conditionals(List<Conditional> conditionals) {
            this.obj.set("conditionals", conditionals);
            return this;
        }

        public Builder linearConstraints(List<LinearConstraint> linearConstraints) {
            this.obj.set("linear_constraints", linearConstraints);
            return this;
        }

        public Builder parallelBandwidth(int parallelBandwidth) {
            this.obj.set("parallel_bandwidth", parallelBandwidth);
            return this;
        }

        public Builder tasks(List<Task> tasks) {
            this.obj.set("tasks", tasks);
            return this;
        }

        public Builder trainingMonitor(TrainingMonitor trainingMonitor) {
            this.obj.set("training_monitor", trainingMonitor);
            return this;
        }
    }
}
