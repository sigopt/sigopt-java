package com.sigopt.model;

public class ConvergenceCriteria extends StructObject {
    public ConvergenceCriteria() {
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

    public static class Builder extends APIObjectBuilder<ConvergenceCriteria> {
        public Builder() {
            this.obj = new ConvergenceCriteria();
            this.obj.set("type", "convergence");
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder metric(String metric) {
            this.obj.set("metric", metric);
            return this;
        }

        public Builder lookbackCheckpoints(Integer lookbackCheckpoints) {
            this.obj.set("lookback_checkpoints", lookbackCheckpoints);
            return this;
        }

        public Builder minCheckpoints(Integer minCheckpoints) {
            this.obj.set("min_checkpoints", minCheckpoints);
            return this;
        }

    }
}
