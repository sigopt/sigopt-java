package com.sigopt.model;

public class Project extends StructObject {
    public Project() {
        super();
    }

    public Project(String id) {
        super();
        this.set("id", id);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getUser() {
        return (String) this.get("user");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public String getClient() {
        return (String) this.get("client");
    }

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public Integer getUpdated() {
        return Utils.asInteger(this.get("updated"));
    }

    public Metadata getMetadata() {
        return Utils.mergeInto(new Metadata(), this.get("metadata"));
    }

    public static class Builder extends APIObjectBuilder<Project> {
        public Builder() {
            this.obj = new Project();
        }

        public Builder id(String id) {
            this.obj.set("id", id);
            return this;
        }

        public Builder name(String name) {
            this.obj.set("name", name);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.obj.set("metadata", metadata);
            return this;
        }
    }
}
