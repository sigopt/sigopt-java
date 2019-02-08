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

    public Double getValueBaseline() {
        return (Double) this.get("value_baseline");
    }

    public static class Builder extends StructObjectBuilder<Metric> {
        public Builder() {
            this.obj = new Metric();
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder valueBaseline(Double valueBaseline) {
            this.obj.set("value_baseline", valueBaseline);
            return this;
        }
    }
}
