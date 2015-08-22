package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.exception.APIException;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIPathKey;
import com.sigopt.net.APIResource;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Experiment extends APIResource {
    String id;
    String type;
    String name;
    List<Parameter> parameters;
    Metric metric;
    List<Cohort> cohorts;

    public Experiment(String id) {
        this.id = id;
    }
    public Experiment(String id, String type, String name, List<Parameter> parameters, Metric metric, List<Cohort> cohorts) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.metric = metric;
        this.cohorts = cohorts;
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

    public Experiment addParameter(Parameter parameter) {
        this.parameters.add(parameter);
        return this;
    }

    public List<Cohort> getCohorts() {
        return cohorts;
    }

    public Metric getMetric() {
        return metric;
    }

    public Experiment setMetric(Metric metric) {
        this.metric = metric;
        return this;
    }

    public static APIMethodCaller<Experiment> retrieve(String id) {
        return new APIMethodCaller<Experiment>("get", "/experiments/:id", Experiment.class).addParam("id", id);
    }

    public static APIMethodCaller<List<Experiment>> all(String clientId) {
        Type type = new TypeToken<List<Experiment>>() {}.getType();
        return new APIMethodCaller<List<Experiment>>("get", "/clients/:client_id/experiments", type).addParam("client_id", clientId);
    }

    public static APIMethodCaller<Experiment> create() {
        return new APIMethodCaller<Experiment>("post", "/experiments/create", Experiment.class);
    }

    public static APIMethodCaller<Experiment> create(String clientId) {
        return Experiment.create().addParam("client_id", clientId);
    }

    public static APIMethodCaller<Experiment> create(Experiment e, String clientId) {
        return Experiment.create(clientId).addParam("data", e);
    }

    public APIMethodCaller<Experiment> insert(String clientId) {
        return Experiment.create(this, clientId);
    }

    public static APIMethodCaller<Experiment> update(String id) {
        return new APIMethodCaller<Experiment>("post", "/experiments/:id/update", Experiment.class)
            .addParam("id", id);
    }

    public static APIMethodCaller<Experiment> update(Experiment experiment) {
        return Experiment.update(experiment.id).addParam("data", experiment);
    }

    public APIMethodCaller<Experiment> save() {
        return Experiment.update(this);
    }

    public APIMethodCaller<Void> reset() {
        return new APIMethodCaller<Void>("post", "/experiments/:id/reset", this, null);
    }

    public APIMethodCaller<Void> delete() {
        return new APIMethodCaller<Void>("post", "/experiments/:id/delete", this, null);
    }

    public APIMethodCaller<List<Cohort>> allocate() {
        return cohorts();
    }

    public APIMethodCaller<List<Cohort>> cohorts() {
        Type type = new TypeToken<List<Cohort>>() {}.getType();
        return new APIMethodCaller<List<Cohort>>("get", "/experiments/:id/allocate", this, type);
    }

    public APIMethodCaller<Cohort> createCohort() {
    return Cohort.create(this.getId());
  }

    public APIMethodCaller<Cohort> createCohort(Cohort cohort) {
        return Cohort.create(cohort, this.getId());
  }

    public APIMethodCaller<Cohort> updateCohort() {
        return Cohort.update(this.getId());
    }

    public APIMethodCaller<Cohort> updateCohort(Cohort cohort) {
        return cohort.save(this.getId());
    }

    public APIMethodCaller<Observation> bestObservation() {
        return new APIMethodCaller<Observation>("get", "/experiments/:id/bestobservation", this, Observation.class);
    }

    public APIMethodCaller<Pagination<Observation>> history() {
        Type type = new TypeToken<Pagination<Observation>>() {}.getType();
        return new APIMethodCaller<Pagination<Observation>>("get", "/experiments/:id/history", this, type);
    }

    public APIMethodCaller<Void> report() {
        return new APIMethodCaller<Void>("post", "/experiments/:id/report", this, null);
    }

    public APIMethodCaller<Void> report(Observation... observations) {
        if(observations.length == 1) {
            return this.report().addParam("data", observations[0]);
        } else {
            ReportMultidata.Builder builder = new ReportMultidata.Builder();
            for(Observation obs : observations) {
                builder.addObservation(obs);
            }
            return this.reportMulti(builder.build());
        }
    }

    public APIMethodCaller<Void> reportMulti() {
        return new APIMethodCaller<Void>("post", "/experiments/:id/reportmulti", this, null);
    }

    public APIMethodCaller<Void> reportMulti(ReportMultidata observation) {
        return this.reportMulti().addParam("multi_data", observation);
    }

    @Deprecated
    public APIMethodCaller<Suggestion> suggestion() {
        return this.suggest();
    }

    public APIMethodCaller<Suggestion> suggest() {
        return new APIMethodCaller<Suggestion>("post", "/experiments/:id/suggest", this, Suggestion.class);
    }

    @Deprecated
    public APIMethodCaller<List<Suggestion>> suggestions(Integer count) {
        return this.suggestMulti(count);
    }

    public APIMethodCaller<List<Suggestion>> suggestMulti() {
        Type type = new TypeToken<List<Suggestion>>() {}.getType();
        return new APIMethodCaller<List<Suggestion>>("post", "/experiments/:id/suggestmulti", this, type);
    }

    public APIMethodCaller<List<Suggestion>> suggestMulti(Integer count) {
        return this.suggestMulti().addParam("count", count);
    }

    public APIMethodCaller<List<Worker>> workers() {
        Type type = new TypeToken<List<Worker>>() {}.getType();
        return new APIMethodCaller<List<Worker>>("get", "/experiments/:id/workers", this, type);
    }

    public APIMethodCaller<Void> releaseWorker() {
        return new APIMethodCaller<Void>("post", "/experiments/:experiment_id/releaseworker", Worker.class)
            .addParam("experiment_id", this.getId());
    }

    public APIMethodCaller<Void> releaseWorker(String workerId) {
        return this.releaseWorker().addParam("worker_id", workerId);
    }

    public static class Builder {
        String id;
        String type;
        String name;
        List<Parameter> parameters = new ArrayList<Parameter>();
        Metric metric;
        List<Cohort> cohorts;

        public Builder() {
        }

        public Experiment build() {
            return new Experiment(id, type, name, parameters, metric, cohorts);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder addParameter(Parameter parameter) {
            this.parameters.add(parameter);
            return this;
        }

        public Builder parameters(List<Parameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder metric(Metric metric) {
            this.metric = metric;
            return this;
        }

        public Builder cohorts(List<Cohort> cohorts) {
            this.cohorts = cohorts;
            return this;
        }
    }

}
