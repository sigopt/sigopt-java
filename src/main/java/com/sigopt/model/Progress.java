package com.sigopt.model;

import java.util.Map;

public class Progress extends StructObject {
    public Progress() {
        super();
    }

    private Observation asObservation(Object obj) {
        return Utils.mergeInto(new Observation(), obj);
    }

    public Observation getBestObservation() {
        return this.asObservation(this.get("best_observation"));
    }

    public Observation getFirstObservation() {
        return this.asObservation(this.get("first_observation"));
    }

    public Observation getLastObservation() {
        return this.asObservation(this.get("last_observation"));
    }

    public Integer getObservationCount() {
        return Utils.asInteger(this.get("observation_count"));
    }

    public static class Builder {
        Progress p;
        public Builder() {
            this.p = new Progress();
        }

        public Progress build() {
            return this.p;
        }

        public Builder bestObservation(Observation bestObservation) {
            this.p.set("best_observation", bestObservation);
            return this;
        }

        public Builder firtObservation(Observation firstObservation) {
            this.p.set("first_observation", firstObservation);
            return this;
        }

        public Builder lastObservation(Observation lastObservation) {
            this.p.set("last_observation", lastObservation);
            return this;
        }

        public Builder observationCount(Integer observationCount) {
            this.p.set("observation_count", observationCount);
            return this;
        }
    }
}
