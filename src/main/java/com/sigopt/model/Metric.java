package com.sigopt.model;

public class Metric extends StructObject {
    public Metric() {
        super();
    }

    public Metric(String name) {
        super();
        this.set("name", name);
    }

    public String getName() {
        return (String) this.get("name");
    }

    public String getObjective() {
        return (String) this.get("objective");
    }

    public String getStrategy() {
        return (String) this.get("strategy");
    }

    public Double getThreshold() {
        return (Double) this.get("threshold");
    }

    public static class Builder extends APIObjectBuilder<Metric> {
        public Builder() {
            this.obj = new Metric();
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder objective(String objective) {
            this.obj.set("objective", objective);
            return this;
        }

        public Builder strategy(String strategy) {
            this.obj.set("strategy", strategy);
            return this;
        }

        public Builder threshold(Double threshold) {
            this.obj.set("threshold", threshold);
            return this;
        }
    }
}
