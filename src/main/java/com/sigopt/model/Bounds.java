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

    public static class Builder {
        Bounds b;
        public Builder() {
            this.b = new Bounds();
        }

        public Bounds build() {
            return this.b;
        }

        public Builder min(double min) {
            this.b.set("min", min);
            return this;
        }

        public Builder min(int min) {
            this.b.set("min", (double) min);
            return this;
        }

        public Builder max(double max) {
            this.b.set("max", max);
            return this;
        }

        public Builder max(int max) {
            this.b.set("max", (double) max);
            return this;
        }
    }
}
