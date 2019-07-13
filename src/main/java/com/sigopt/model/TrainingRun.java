package com.sigopt.model;

import java.util.*;

public class TrainingRun extends StructObject {
    public TrainingRun() {
        super();
    }

    public TrainingRun(String id) {
        super();
        this.set("id", id);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public Integer getCheckpointCount() {
        return Utils.asInteger(this.get("checkpoint_count"));
    }

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public Boolean getFinished() {
        return (Boolean) this.get("finished");
    }

    public Metadata getMetadata() {
        return Utils.mergeInto(new Metadata(), this.get("metadata"));
    }

    public String getObservation() {
        return (String) this.get("observation");
    }

    public String getSuggestion() {
        return (String) this.get("suggestion");
    }

    public Integer getUpdated() {
        return Utils.asInteger(this.get("updated"));
    }

    public Subresource<Checkpoint> checkpoints() {
        return new Subresource<Checkpoint>(this.pathPrefix + "/" + this.getId(), "checkpoints", Checkpoint.class);
    }

    public static class Builder extends APIObjectBuilder<TrainingRun> {
        public Builder() {
            this.obj = new TrainingRun();
        }

        public Builder id(String id) {
            this.obj.set("id", id);
            return this;
        }
        
        public Builder created(int created) {
            this.obj.set("created", created);
            return this;
        }

        public Builder checkpointCount(int checkpointCount) {
            this.obj.set("checkpointCount", checkpointCount);
            return this;
        }

        public Builder finished(boolean finished) {
            this.obj.set("finished", finished);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.obj.set("metadata", metadata);
            return this;
        }

        public Builder observation(String observation) {
            this.obj.set("observation", observation);
            return this;
        }

        public Builder suggestion(String suggestion) {
            this.obj.set("suggestion", suggestion);
            return this;
        }

        public Builder updated(int updated) {
            this.obj.set("updated", updated);
            return this;
        }

    }
}
