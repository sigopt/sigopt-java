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

    public String getState() {
      return (String) this.get("state");
    }

    public Integer getCreated() {
      return Utils.asInteger(this.get("created"));
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

    public Integer getFolds() {
      return Utils.asInteger(this.get("folds"));
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
    *   LinkedResource is used for a resource linked strongly to another e.g.
    *   StoppingCriteria isn't a full object with its own id but still must be
    *   retrieved as its own resource separate from Experiment
    */
    public static class LinkedResource<T extends APIObject> extends BoundObject {
        String name;
        Class<T> klass;

        public LinkedResource(String prefix, String name, Class<T> klass) {
            super(prefix);
            this.name = name;
            this.klass = klass;
        }

        public APIMethodCaller<T> fetch() {
            return new APIMethodCaller<T>("get", this.prefix() + "/" + this.name, klass);
        }
    }

    public static class Subresource<T extends APIObject> extends BoundObject {
        String name;
        Class<T> klass;

        public Subresource(String prefix, String name, Class<T> klass) {
            super(prefix);
            this.name = name;
            this.klass = klass;
        }

        public APIMethodCaller<T> fetch() {
            return new APIMethodCaller<T>("get", this.prefix() + "/" + this.name + "/:id", klass);
        }

        public APIMethodCaller<T> fetch(String id) {
            return this.fetch().addParam("id", id);
        }

        public PaginatedAPIMethodCaller<T> list() {
            return new PaginatedAPIMethodCaller<T>("get", this.prefix() + "/" + this.name, klass);
        }

        public APIMethodCaller<T> create() {
            return new APIMethodCaller<T>("post", this.prefix() + "/" + this.name, klass);
        }

        public APIMethodCaller<T> create(T o) {
            return this.create().data(o);
        }

        public APIMethodCaller<T> update() {
            return new APIMethodCaller<T>("put", this.prefix() + "/" + this.name + "/:id", klass);
        }

        public APIMethodCaller<T> update(String id) {
            return this.update().addPathComponent("id", id);
        }

        public APIMethodCaller<T> update(String id, T o) {
            return this.update(id).data(o);
        }

        public APIMethodCaller<VoidObject> deleteList() {
            return new APIMethodCaller<VoidObject>("delete", this.prefix() + "/" + this.name, VoidObject.class);
        }

        public APIMethodCaller<VoidObject> delete() {
            return new APIMethodCaller<VoidObject>("delete", this.prefix() + "/" + this.name + "/:id", VoidObject.class);
        }

        public APIMethodCaller<VoidObject> delete(String id) {
            return this.delete().addPathComponent("id", id);
        }
    }

    public Subresource<Observation> observations() {
        return new Subresource<Observation>("/experiments/" + this.getId(), "observations", Observation.class);
    }

    public LinkedResource<StoppingCriteria> stoppingCriteria() {
        return new LinkedResource<StoppingCriteria>("/experiments/" + this.getId(), "stopping_criteria", StoppingCriteria.class);
    }

    public Subresource<Suggestion> suggestions() {
        return new Subresource<Suggestion>("/experiments/" + this.getId(), "suggestions", Suggestion.class);
    }

    public Subresource<Token> tokens() {
        return new Subresource<Token>("/experiments/" + this.getId(), "tokens", Token.class);
    }

    public Subresource<BestAssignments> bestAssignments() {
        return new Subresource<BestAssignments>("/experiments/" + this.getId(), "best_assignments", BestAssignments.class);
    }

    public static class Builder {
        Experiment e;

        public Builder() {
            this.e = new Experiment();
        }

        public Experiment build() {
            return this.e;
        }

        public Builder created(int created) {
            this.e.set("created", created);
            return this;
        }

        public Builder parameters(List<Parameter> parameters) {
            this.e.set("parameters", parameters);
            return this;
        }

        public Builder metrics(List<Metric> metrics) {
            this.e.set("metrics", metrics);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.e.set("metadata", metadata);
            return this;
        }

        public Builder metric(Metric metric) {
            this.e.set("metric", metric);
            return this;
        }

        public Builder progress(Progress progress) {
            this.e.set("progress", progress);
            return this;
        }

        public Builder client(String client) {
            this.e.set("client", client);
            return this;
        }

        public Builder id(String id) {
            this.e.set("id", id);
            return this;
        }

        public Builder name(String name) {
            this.e.set("name", name);
            return this;
        }

        public Builder state(String state) {
            this.e.set("state", state);
            return this;
        }

        public Builder type(String type) {
            this.e.set("type", type);
            return this;
        }

        public Builder observationBudget(int budget) {
            this.e.set("observation_budget", budget);
            return this;
        }

        public Builder numSolutions(int numSolutions) {
            this.e.set("num_solutions", numSolutions);
            return this;
        }

        public Builder development(boolean development) {
            this.e.set("development", development);
            return this;
        }

        public Builder folds(int folds) {
            this.e.set("folds", folds);
            return this;
        }
    }
}
