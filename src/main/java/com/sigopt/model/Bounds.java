package com.sigopt.model;

import com.sigopt.net.APIObject;

public class Bounds extends APIObject {
    Double min;
    Double max;

    public Bounds(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public static class Builder {
        Double min;
        Double max;

        public Builder() {
        }

        public Bounds build() {
            return new Bounds(this.min, this.max);
        }

        public Builder min(Double min) {
            this.min = min;
            return this;
        }

        public Builder min(Integer min) {
            this.min = (double) min;
            return this;
        }

        public Builder max(Double max) {
            this.max = max;
            return this;
        }

        public Builder max(Integer max) {
            this.max = (double) max;
            return this;
        }
    }
}
