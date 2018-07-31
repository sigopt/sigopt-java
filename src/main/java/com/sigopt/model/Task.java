package com.sigopt.model;

public class Task extends StructObject {
    public Task() {
        super();
    }

    public Double getCost() {
        return (Double) this.get("cost");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public static class Builder {
        Task t;

        public Builder() {
            this.t = new Task();
        }

        public Task build() {
            return this.t;
        }

        public Builder cost(double cost) {
            this.t.set("cost", cost);
            return this;
        }

        public Builder name(String name) {
            this.t.set("name", name);
            return this;
        }
    }
}
