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

    public static class Builder {
        MetricEvaluation v;

        public Builder() {
            this.v = new MetricEvaluation();
        }

        public MetricEvaluation build() {
            return this.v;
        }

        public Builder name(String name) {
            this.v.set("name", name);
            return this;
        }

        public Builder value(Double value) {
            this.v.set("value", value);
            return this;
        }

        public Builder valueStddev(Double valueStddev) {
            this.v.set("value_stddev", valueStddev);
            return this;
        }
    }
}
