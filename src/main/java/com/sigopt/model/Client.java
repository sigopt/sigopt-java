package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.exception.APIException;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIPathKey;
import com.sigopt.net.APIResource;

import java.lang.reflect.Type;
import java.util.List;

public class Client extends APIResource {
    String id;
    String name;

    public Client(String id) {
        this.id = id;
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @APIPathKey(key="id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static APIMethodCaller<Client> retrieve(String id) {
        return new APIMethodCaller<Client>("get", "/clients/:id", Client.class).addParam("id", id);
    }

    public APIMethodCaller<List<Experiment>> experiments() {
        Type type = new TypeToken<List<Experiment>>() {}.getType();
        return new APIMethodCaller<List<Experiment>>("get", "/clients/:id/experiments", this, type);
    }

    public static class Builder {
        String id;
        String name;

        public Builder() {
        }

        public Client build() {
            return new Client(id, name);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
    }
}
