package com.sigopt.model;

import com.sigopt.net.APIObject;

import java.util.List;

public class Parameter extends APIObject {
    String name;
    String type;
    Bounds bounds;
    List<CategoricalValue> categoricalValues;
    String transformation;
    Boolean tunable;

    public Parameter() {
    }

    public Parameter(String name, String type, Bounds bounds, List<CategoricalValue> categoricalValues, String transformation, Boolean tunable) {
        this.name = name;
        this.type = type;
        this.bounds = bounds;
        this.categoricalValues = categoricalValues;
        this.transformation = transformation;
        this.tunable = tunable;
    }

    public String getName() {
        return name;
    }

    public Parameter setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public Parameter setBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public List<CategoricalValue> getCategoricalValues() {
        return categoricalValues;
    }

    public Parameter setCategoricalValues(List<CategoricalValue> categoricalValues) {
        this.categoricalValues = categoricalValues;
        return this;
    }

    public Parameter addCategoricalValue(CategoricalValue categoricalValue) {
        this.categoricalValues.add(categoricalValue);
        return this;
    }
    public Parameter removeCategoricalValue(CategoricalValue categoricalValue) {
        this.categoricalValues.remove(categoricalValue);
        return this;
    }

    public String getTransformation() {
        return transformation;
    }

    public Boolean isTunable() {
        return tunable;
    }

    public static class Builder {
        String name;
        String type;
        Bounds bounds;
        List<CategoricalValue> categoricalValues;
        String transformation;
        Boolean tunable;

        public Builder() {
        }

        public Parameter build() {
            return new Parameter(name, type, bounds, categoricalValues, transformation, tunable);
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder bounds(Bounds bounds) {
            this.bounds = bounds;
            return this;
        }

        public Builder categoricalValues(List<CategoricalValue> categoricalValues) {
            this.categoricalValues = categoricalValues;
            return this;
        }

        public Builder transformation(String transformation) {
            this.transformation = transformation;
            return this;
        }

        public Builder tunable(Boolean tunable) {
            this.tunable = tunable;
            return this;
        }
    }

}
