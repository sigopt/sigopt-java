package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIPathKey;
import com.sigopt.net.APIResource;

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

    @APIPathKey(key="id")
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

    public static APIMethodCaller<Experiment> fetch(String id) {
        return new APIMethodCaller<Experiment>("get", "/experiments/:id", Experiment.class).addParam("id", id);
    }

    public static APIMethodCaller<List<Experiment>> fetch() {
        Type type = new TypeToken<List<Experiment>>() {}.getType();
        return new APIMethodCaller<List<Experiment>>("get", "/experiments", type);
    }

    public static APIMethodCaller<Experiment> create() {
        return new APIMethodCaller<Experiment>("post", "/experiments", Experiment.class);
    }

    public static APIMethodCaller<Experiment> create(Experiment e) {
        return Experiment.create().data(e);
    }

    public static APIMethodCaller<Experiment> update(String id, Experiment e) {
        return new APIMethodCaller<Experiment>("put", "/experiments/:id", e, Experiment.class)
            .data(e);
    }

    public static APIMethodCaller<Experiment> delete(Experiment e) {
        return new APIMethodCaller<Experiment>("delete", "/experiments/:id", e, Experiment.class);
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
