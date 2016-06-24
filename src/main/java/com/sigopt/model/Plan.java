package com.sigopt.model;

public class Plan extends APIResource {
    public Plan() {
        super();
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public PlanRules getPlanRules() {
        return Utils.mergeInto(new PlanRules(), this.get("rules"));
    }

    public PlanPeriod getCurrentPeriod() {
        return Utils.mergeInto(new PlanPeriod(), this.get("current_period"));
    }
}
