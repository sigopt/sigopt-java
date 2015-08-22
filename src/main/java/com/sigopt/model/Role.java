package com.sigopt.model;

import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIObject;
import com.sigopt.net.APIPathKey;

public class Role extends APIObject {
    String role;
    User user;
    Client client;

    public Role() {
    }

    public Role(String role, User user, Client client) {
        this.role = role;
        this.user = user;
        this.client = client;
    }

    public String getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public Client getClient() {
        return client;
    }

    public static class Builder {
        String role;
        User user;
        Client client;

        public Role build() {
            return new Role(role, user, client);
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }
    }
}
