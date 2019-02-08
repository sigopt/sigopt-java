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

    public static class Builder extends StructObjectBuilder<Task> {
        public Builder() {
            this.obj = new Task();
        }

        public Builder cost(double cost) {
            this.obj.set("cost", cost);
            return this;
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }
    }
}
