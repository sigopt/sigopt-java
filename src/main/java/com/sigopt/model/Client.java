package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.exception.APIException;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIResource;
import com.sigopt.net.BoundObject;

import java.lang.reflect.Type;
import java.util.List;

public class Client extends APIResource {
    Integer created;
    String id;
    String name;

    public Client(String id) {
      this.id = id;
    }

    protected Client(Integer created, String id, String name) {
        this.created = created;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCreated() {
        return created;
    }

    public static APIMethodCaller<Client> fetch(String id) {
        return new APIMethodCaller<Client>("get", "/clients/:id", Client.class).addPathComponent("id", id);
    }

    private static class Experiments extends BoundObject {
        public Experiments(String prefix) {
            super(prefix);
        }

        public APIMethodCaller<List<Experiment>> list() {
            Type type = new TypeToken<List<Experiment>>() {}.getType();
            return new APIMethodCaller<List<Experiment>>("get", this.prefix() + "/experiments", type);
        }

        public APIMethodCaller<Experiment> create() {
            return new APIMethodCaller<Experiment>("post", this.prefix() + "/experiments", Experiment.class);
        }
    }

    public Experiments experiments() {
        return new Experiments("/clients/" + this.id);
    }

    public static class Builder {
        String id;
        String name;
        Integer created;

        public Builder() {
        }

        public Client build() {
            return new Client(created, id, name);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder created(Integer created) {
            this.created = created;
            return this;
        }
    }
}
