package com.sigopt.model;

public class TrainingEarlyStoppingCriteria extends StructObject {
    public TrainingEarlyStoppingCriteria() {
        super();
    }

    public String getName() {
        return (String) this.get("name");
    }

    public String getMetric() {
        return (String) this.get("metric");
    }

    public String getType() {
        return (String) this.get("type");
    }
    
}
