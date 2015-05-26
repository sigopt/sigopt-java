package com.sigopt.model;

import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.APIResource;

public class Worker extends APIResource {
    String id;
    Integer claimedTime;
    Suggestion suggestion;

    public Worker(String id) {
        this.id = id;
    }

    public Worker(String id, Integer claimedTime, Suggestion suggestion) {
        this.id = id;
        this.claimedTime = claimedTime;
        this.suggestion = suggestion;
    }

    public String getId() {
        return this.id;
    }

    public Integer getClaimedTime() {
        return this.claimedTime;
    }

    public Suggestion getSuggestion() {
        return this.suggestion;
    }

    public static APIMethodCaller<Void> release() {
        return new APIMethodCaller<Void>("post", "/experiments/:experiment_id/releaseworker", null);
    }

    public APIMethodCaller<Void> release(String experimentId) {
        return Worker.release()
            .addParam("experiment_id", experimentId)
            .addParam("worker_id", this.getId());
    }
}
