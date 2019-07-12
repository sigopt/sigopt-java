package com.sigopt.model;

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

    public Double getCheckpointCount() {
        return (Double) this.get("checkpoint_count");
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

        public Builder suggestion(String suggestion) {
            this.obj.set("suggestion", suggestion);
            return this;
        }

    }
}
