package com.sigopt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parameter extends APIObject {
    public Parameter() {
        super();
    }

    public String getName() {
        return (String) this.get("name");
    }

    public String getType() {
        return (String) this.get("type");
    }

    public Bounds getBounds() {
        return Utils.mergeInto(new Bounds(), this.get("bounds"));
    }

    public List<CategoricalValue> getCategoricalValues() {
        return Utils.mergeIntoList(new ArrayList<CategoricalValue>(), this.get("categorical_values"), CategoricalValue.class);
    }

    public static class Builder {
        Parameter p;

        public Builder() {
            this.p = new Parameter();
        }

        public Parameter build() {
            return this.p;
        }

        public Builder name(String name) {
            this.p.set("name", name);
            return this;
        }

        public Builder type(String type) {
            this.p.set("type", type);
            return this;
        }

        public Builder bounds(Bounds bounds) {
            this.p.set("bounds", bounds);
            return this;
        }

        public Builder categoricalValues(List<CategoricalValue> categoricalValues) {
            this.p.set("categorical_values", categoricalValues);
            return this;
        }
    }
}
