package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.net.APIObject;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIResource;
import com.sigopt.net.BoundObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Experiment extends APIResource {
    Integer created;
    List<Parameter> parameters;
    Map<String, String> metadata;
    Metric metric;
    Progress progress;
    String client;
    String id;
    String name;
    String state;
    String type;

    public Experiment(String id) {
        this.id = id;
    }

    protected Experiment(
        Integer created,
        List<Parameter> parameters,
        Map<String, String> metadata,
        Metric metric,
        Progress progress,
        String client,
        String id,
        String name,
        String state,
        String type
    ) {
        this.created = created;
        this.parameters = parameters;
        this.metadata = metadata;
        this.metric = metric;
        this.progress = progress;
        this.client = client;
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public Metric getMetric() {
        return metric;
    }

    public Progress getProgress() {
      return progress;
    }

    public Map<String, String> getMetadata() {
      return metadata;
    }

    public String getClient() {
      return client;
    }

    public String getState() {
      return state;
    }

    public Integer getCreated() {
      return created;
    }

    public static APIMethodCaller<Experiment> fetch() {
        return new APIMethodCaller<Experiment>("get", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> fetch(String id) {
        return Experiment.fetch().addPathComponent("id", id);
    }

    public static APIMethodCaller<Pagination<Experiment>> list() {
        Type type = new TypeToken<Pagination<Experiment>>() {}.getType();
        return new APIMethodCaller<Pagination<Experiment>>("get", "/experiments", type);
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

    public static APIMethodCaller<Experiment> delete() {
        return new APIMethodCaller<Experiment>("delete", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> delete(String id) {
        return Experiment.delete().addPathComponent("id", id);
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

        public APIMethodCaller<Pagination<T>> list() {
            Type type = new TypeToken<Pagination<T>>() {}.getType();
            return new APIMethodCaller<Pagination<T>>("get", this.prefix() + "/" + this.name, type);
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

        public APIMethodCaller<Void> deleteList() {
            return new APIMethodCaller<Void>("delete", this.prefix() + "/" + this.name, null);
        }

        public APIMethodCaller<Void> delete() {
            return new APIMethodCaller<Void>("delete", this.prefix() + "/" + this.name + "/:id", null);
        }

        public APIMethodCaller<Void> delete(String id) {
            return this.delete().addPathComponent("id", id);
        }
    }

    public Subresource<Observation> observations() {
        return new Subresource<Observation>("/experiments/" + this.id, "observations", Observation.class);
    }

    public Subresource<Suggestion> suggestions() {
        return new Subresource<Suggestion>("/experiments/" + this.id, "suggestions", Suggestion.class);
    }

    public static class Builder {
        Integer created;
        List<Parameter> parameters;
        Map<String, String> metadata;
        Metric metric;
        Progress progress;
        String client;
        String id;
        String name;
        String state;
        String type;

        public Builder() {
        }

        public Experiment build() {
            return new Experiment(
                created,
                parameters,
                metadata,
                metric,
                progress,
                client,
                id,
                name,
                state,
                type
            );
        }

        public Builder created(Integer created) {
            this.created = created;
            return this;
        }

        public Builder parameters(List<Parameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder metric(Metric metric) {
            this.metric = metric;
            return this;
        }

        public Builder progress(Progress progress) {
            this.progress = progress;
            return this;
        }

        public Builder client(String client) {
            this.client = client;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }
    }
}
