package com.sigopt.model;

import com.sigopt.net.APIObject;

import java.util.ArrayList;
import java.util.List;

public class ReportMultidata extends APIObject {
    List<Observation> data = new ArrayList<Observation>();

    public ReportMultidata() {
    }

    public ReportMultidata(List<Observation> data) {
        this.data = data;
    }

    public List<Observation> getData() {
        return data;
    }

    public static class Builder {
        List<Observation> data = new ArrayList<Observation>();

        public Builder() {
        }

        public ReportMultidata build() {
            return new ReportMultidata(data);
        }

        public Builder data(List<Observation> data) {
            this.data = data;
            return this;
        }

        public Builder addData(Observation obs) {
            this.data.add(obs);
            return this;
        }

        public Builder addObservation(Observation obs) {
            return addData(obs);
        }
    }
}