package com.sigopt.model;

import java.util.*;

public class Checkpoint extends StructObject {
    public Checkpoint() {
        super();
    }

    public Checkpoint(String id) {
        super();
        this.set("id", id);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public Integer getCreated() {
        return Utils.asInteger(this.get("created"));
    }

    public Metadata getMetadata() {
        return Utils.mergeInto(new Metadata(), this.get("metadata"));
    }

    public Boolean getShouldStop() {
        return (Boolean) this.get("should_stop");
    }

    public RichMapObject getStoppingReasons() {
        return Utils.mergeInto(new RichMapObject(), this.get("stopping_reasons"));
    }

    public String getTrainingRun() {
        return (String) this.get("training_run");
    }

    public List<MetricEvaluation> getValues() {
        return Utils.mergeIntoList(new ArrayList<MetricEvaluation>(), this.get("values"), MetricEvaluation.class);
    }

    public static class Builder extends APIObjectBuilder<Checkpoint> {
        public Builder() {
            this.obj = new Checkpoint();
        }

        public Builder id(String id) {
            this.obj.set("id", id);
            return this;
        }

        public Builder created(int created) {
            this.obj.set("created", created);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.obj.set("metadata", metadata);
            return this;
        }

        public Builder shouldStop(boolean shouldStop) {
            this.obj.set("shouldStop", shouldStop);
            return this;
        }

        public Builder stoppingReasons(RichMapObject stoppingReasons) {
            this.obj.set("stopping_reasons", stoppingReasons);
            return this;
        }

        public Builder trainingRun(String trainingRun){
            this.obj.set("training_run", trainingRun);
            return this;
        }

        public Builder values(List<MetricEvaluation> values) {
            this.obj.set("values", values);
            return this;
        }
    }
}
