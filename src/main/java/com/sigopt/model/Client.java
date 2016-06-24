package com.sigopt.model;

import com.sigopt.exception.APIException;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.PaginatedAPIMethodCaller;

public class Client extends StructObject {
    public Client() {
        super();
    }

    public Client(String id) {
        super();
        this.set("id", id);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public static APIMethodCaller<Client> fetch(String id) {
        return new APIMethodCaller<Client>("get", "/clients/:id", Client.class).addPathComponent("id", id);
    }

    public static class Experiments extends BoundObject {
        public Experiments(String prefix) {
            super(prefix);
        }

        public PaginatedAPIMethodCaller<Experiment> list() {
            return new PaginatedAPIMethodCaller<Experiment>(
                "get",
                this.prefix() + "/experiments",
                Experiment.class
            );
        }

        public APIMethodCaller<Experiment> create() {
            return new APIMethodCaller<Experiment>("post", this.prefix() + "/experiments", Experiment.class);
        }
    }

    public Experiments experiments() {
        return new Experiments("/clients/" + this.getId());
    }

    public static class Builder {
        Client c;
        public Builder() {
            this.c = new Client();
        }

        public Client build() {
            return this.c;
        }

        public Builder id(String id) {
            this.c.set("id", id);
            return this;
        }

        public Builder name(String name) {
            this.c.set("name", name);
            return this;
        }

        public Builder created(Integer created) {
            this.c.set("created", created);
            return this;
        }
    }
}
