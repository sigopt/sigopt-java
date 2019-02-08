package com.sigopt.model;

import java.util.Map;

public class Progress extends StructObject {
    public Progress() {
        super();
    }

    private Observation asObservation(Object obj) {
        return Utils.mergeInto(new Observation(), obj);
    }

    /**
     * @deprecated Prefer Experiment.bestAssignments
     */
    @Deprecated
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

    public static class Builder extends StructObjectBuilder<Progress> {
        public Builder() {
            this.obj = new Progress();
        }

        /**
         * @deprecated Prefer Experiment.bestAssignments
         */
        @Deprecated
        public Builder bestObservation(Observation bestObservation) {
            this.obj.set("best_observation", bestObservation);
            return this;
        }

        public Builder firtObservation(Observation firstObservation) {
            this.obj.set("first_observation", firstObservation);
            return this;
        }

        public Builder lastObservation(Observation lastObservation) {
            this.obj.set("last_observation", lastObservation);
            return this;
        }

        public Builder observationCount(int observationCount) {
            this.obj.set("observation_count", observationCount);
            return this;
        }
    }
}
