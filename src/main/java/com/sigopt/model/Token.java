package com.sigopt.model;

import com.sigopt.net.APIMethod;
import java.util.*;

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

    public Integer getPermissions() {
        return Utils.asInteger(this.get("permissions"));
    }

    public String getToken() {
        return (String) this.get("token");
    }

    public String getClientId() {
        return (String) this.get("client_id");
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

        public Builder permissions(int permissions) {
            this.t.set("permissions", permissions);
            return this;
        }

        public Builder token(String token){
            this.t.set("token", token);
            return this;
        }

        public Builder clientId(String clientId){
            this.t.set("client_id", clientId);
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