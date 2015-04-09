package com.sigopt.model;

import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIObject;

public class Cohort extends APIObject {
    String id;
    String name;
    Double allocation;
    Suggestion suggestion;
    Integer successes;
    Integer attempts;
    String state;

    public Cohort() {
    }

    public Cohort(String id, String name, Double allocation, Suggestion suggestion, Integer successes, Integer attempts, String state) {
        this.id = id;
        this.name = name;
        this.allocation = allocation;
        this.suggestion = suggestion;
        this.successes = successes;
        this.attempts = attempts;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cohort setName(String name) {
        this.name = name;
        return this;
    }

    public Double getAllocation() {
        return allocation;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public Integer getSuccess() {
        return successes;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public String getState() {
        return state;
    }

    public Cohort setState(String state) {
        this.state = state;
        return this;
    }

    public static APIMethodCaller<Cohort> create(Cohort cohort, String experimentId) {
        return new APIMethodCaller<Cohort>("post", "/experiments/:experiment_id/createcohort", Cohort.class)
            .addParam("data", cohort)
            .addParam("experiment_id", experimentId);
    }

    public APIMethodCaller<Cohort> create(String experimentId) {
        return Cohort.create(this, experimentId);
    }

    public APIMethodCaller<Cohort> save(String experimentId) {
        return new APIMethodCaller<Cohort>("post", "/experiments/:experiment_id/updatecohort", Cohort.class)
            .addParam("data", this)
            .addParam("experiment_id", experimentId)
            .addParam("cohort_id", this.getId());
    }

    public static class Builder {
        String id;
        String name;
        Double allocation;
        Suggestion suggestion;
        Integer successes;
        Integer attempts;
        String state;

        public Cohort build() {
            return new Cohort(id, name, allocation, suggestion, successes, attempts, state);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder allocation(Double allocation) {
            this.allocation = allocation;
            return this;
        }

        public Builder suggestion(Suggestion suggestion) {
            this.suggestion = suggestion;
            return this;
        }

        public Builder successes(Integer successes) {
            this.successes = successes;
            return this;
        }

        public Builder attempts(Integer attempts) {
            this.attempts = attempts;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }
    }
}
