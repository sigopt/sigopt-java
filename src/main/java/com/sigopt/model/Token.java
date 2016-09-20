package com.sigopt.model;

import com.sigopt.net.APIMethod;

public class Token extends StructObject {
    public Token() {
        super();
    }

    public Boolean getAllExperiments() {
        return (Boolean) this.get("all_experiments");
    }

    public Boolean getDevelopment() {
        return (Boolean) this.get("development");
    }

    public String getPermissions() {
        return (String) this.get("permissions");
    }

    public String getToken() {
        return (String) this.get("token");
    }

    public String getClient() {
        return (String) this.get("client");
    }

    public String getExperiment() {
        return (String) this.get("experiment");
    }

    public String getUser() {
        return (String) this.get("user");
    }

    public static class Builder {
        Token t;
        public Builder() {
            this.t = new Token();
        }

        public Token build(){
            return this.t;
        }

        public Builder allExperiments(boolean allExperiments) {
            this.t.set("all_experiments", allExperiments);
            return this;
        }

        public Builder development(boolean development) {
            this.t.set("development", development);
            return this;
        }

        public Builder permissions(String permissions) {
            this.t.set("permissions", permissions);
            return this;
        }

        public Builder token(String token){
            this.t.set("token", token);
            return this;
        }

        public Builder client(String client){
            this.t.set("client", client);
            return this;
        }

        public Builder experiment(String experiment){
            this.t.set("experiment", experiment);
            return this;
        }

        public Builder user(String user){
            this.t.set("user", user);
            return this;
        }
    }
}
