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

    public static class Builder extends APIObjectBuilder<Token> {
        public Builder() {
            this.obj = new Token();
        }

        public Builder allExperiments(boolean allExperiments) {
            this.obj.set("all_experiments", allExperiments);
            return this;
        }

        public Builder development(boolean development) {
            this.obj.set("development", development);
            return this;
        }

        public Builder permissions(String permissions) {
            this.obj.set("permissions", permissions);
            return this;
        }

        public Builder token(String token){
            this.obj.set("token", token);
            return this;
        }

        public Builder client(String client){
            this.obj.set("client", client);
            return this;
        }

        public Builder experiment(String experiment){
            this.obj.set("experiment", experiment);
            return this;
        }

        public Builder user(String user){
            this.obj.set("user", user);
            return this;
        }
    }
}
