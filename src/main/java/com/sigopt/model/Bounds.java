package com.sigopt.model;

public class Bounds extends StructObject {
    public Bounds() {
        super();
    }

    public Bounds(Double min, Double max) {
        super();
        this.set("min", min);
        this.set("max", max);
    }

    public Double getMin() {
        return (Double) this.get("min");
    }

    public Double getMax() {
        return (Double) this.get("max");
    }

    public static class Builder extends APIObjectBuilder<Bounds> {
        public Builder() {
            this.obj = new Bounds();
        }

        public Builder min(double min) {
            this.obj.set("min", min);
            return this;
        }

        public Builder min(int min) {
            this.obj.set("min", (double) min);
            return this;
        }

        public Builder max(double max) {
            this.obj.set("max", max);
            return this;
        }

        public Builder max(int max) {
            this.obj.set("max", (double) max);
            return this;
        }
    }
}
