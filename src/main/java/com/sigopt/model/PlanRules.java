package com.sigopt.model;

public class PlanRules extends StructObject {
    public PlanRules() {
        super();
    }

    public Integer getMaxDimension() {
        return Utils.asInteger(this.get("max_dimension"));
    }

    public Integer getMaxExperiments() {
        return Utils.asInteger(this.get("max_experiments"));
    }

    public Integer getMaxObservations() {
        return Utils.asInteger(this.get("max_observations"));
    }

    public Integer getMaxParallelism() {
        return Utils.asInteger(this.get("max_parallelism"));
    }

    public Integer getMaxUsers() {
        return Utils.asInteger(this.get("max_users"));
    }
}
