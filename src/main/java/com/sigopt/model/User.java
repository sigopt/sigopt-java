package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIObject;
import com.sigopt.net.APIPathKey;

import java.lang.reflect.Type;
import java.util.List;

public class User extends APIObject {
    String id;
    String name;
    String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @APIPathKey(key="id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public static APIMethodCaller<User> retrieve(String id) {
        return new APIMethodCaller<User>("get", "/users/:id", User.class).addParam("id", id);
    }

    public APIMethodCaller<List<Role>> roles() {
        Type type = new TypeToken<List<Role>>() {}.getType();
        return new APIMethodCaller<List<Role>>("get", "/users/:id/roles", this, type);
    }

    public static class Builder {
        String id;
        String name;
        String email;

        public User build() {
            return new User(id, name, email);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }
}
