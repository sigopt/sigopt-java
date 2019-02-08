package com.sigopt.model;

public class MetricEvaluation extends StructObject {
    public MetricEvaluation() {
        super();
    }

    public String getName() {
        return (String) this.get("name");
    }

    public Double getValue() {
        return (Double) this.get("value");
    }

    public Double getValueStddev() {
        return (Double) this.get("value_stddev");
    }

    public static class Builder extends StructObjectBuilder<MetricEvaluation> {
        public Builder() {
            this.obj = new MetricEvaluation();
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder value(Double value) {
            this.obj.set("value", value);
            return this;
        }

        public Builder valueStddev(Double valueStddev) {
            this.obj.set("value_stddev", valueStddev);
            return this;
        }
    }
}
