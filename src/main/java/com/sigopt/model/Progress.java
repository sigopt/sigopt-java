package com.sigopt.model;

import com.sigopt.net.APIObject;

public class Progress extends APIObject {
    Observation bestObservation;
    Observation firstObservation;
    Observation lastObservation;
    Integer observation_count;

    public Progress(
        Observation bestObservation,
        Observation firstObservation,
        Observation lastObservation,
        Integer observation_count
    ) {
        this.bestObservation = bestObservation;
        this.firstObservation = firstObservation;
        this.lastObservation = lastObservation;
        this.observation_count = observation_count;
    }

    public Observation getBestObservation() {
        return bestObservation;
    }

    public Observation getFirstObservation() {
        return firstObservation;
    }

    public Observation getLastObservation() {
        return lastObservation;
    }

    public static class Builder {
        Observation bestObservation;
        Observation firstObservation;
        Observation lastObservation;
        Integer observation_count;

        public Builder() {
        }

        public Progress build() {
            return new Progress(bestObservation, firstObservation, lastObservation, observation_count);
        }

        public Builder bestObservation(Observation bestObservation) {
            this.bestObservation = bestObservation;
            return this;
        }

        public Builder firtObservation(Observation firstObservation) {
            this.firstObservation = firstObservation;
            return this;
        }

        public Builder lastObservation(Observation lastObservation) {
            this.lastObservation = lastObservation;
            return this;
        }

        public Builder observationCount(Integer observation_count) {
            this.observation_count = observation_count;
            return this;
        }
    }
}
