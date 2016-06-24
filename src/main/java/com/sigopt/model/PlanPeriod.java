package com.sigopt.model;

import java.util.List;

public class PlanPeriod extends StructObject {
    public PlanPeriod() {
        super();
    }

    public Integer getStart() {
        return Utils.asInteger(this.get("start"));
    }

    public Integer getEnd() {
        return Utils.asInteger(this.get("end"));
    }

    public List<String> getExperiments() {
        return (List<String>) this.get("experiments");
    }
}
