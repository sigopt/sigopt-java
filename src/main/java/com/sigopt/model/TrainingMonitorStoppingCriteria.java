package com.sigopt.model;

public class TrainingMonitorStoppingCriteria extends StructObject {
    public TrainingMonitorStoppingCriteria() {
        super();
    }

    public Integer getLookbackCheckpoints() {
        return (Integer) this.get("lookback_checkpoints");
    }

    public Integer getMinCheckpoints() {
        return (Integer) this.get("min_checkpoints");
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

    public static class Builder extends APIObjectBuilder<TrainingMonitorStoppingCriteria> {
        public Builder() {
            this.obj = new TrainingMonitorStoppingCriteria();
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder metric(String metric) {
            this.obj.set("metric", metric);
            return this;
        }

        public Builder lookbackCheckpoints(int lookbackCheckpoints) {
            this.obj.set("lookback_checkpoints", lookbackCheckpoints);
            return this;
        }

        public Builder minCheckpoints(int minCheckpoints) {
            this.obj.set("min_checkpoints", minCheckpoints);
            return this;
        }

        public Builder type(String type) {
            this.obj.set("type", type);
            return this;
        }

    }
}
