package com.sigopt.model;
import java.util.ArrayList;
import java.util.List;

public class TrainingMonitor extends StructObject {
    public TrainingMonitor() {
        super();
    }

    public Integer getMaxCheckpoints() {
        return Utils.asInteger(this.get("max_checkpoints"));
    }

    public List<APIObject> getEarlyStoppingCriteria() {
        return Utils.mergeIntoList(new ArrayList<APIObject>(), this.get("early_stopping_criteria"), APIObject.class);
    }

    public static class Builder extends APIObjectBuilder<TrainingMonitor> {
        public Builder() {
            this.obj = new TrainingMonitor();
        }

        public Builder maxCheckpoints(int maxCheckpoints) {
            this.obj.set("max_checkpoints", maxCheckpoints);
            return this;
        }

        public Builder earlyStoppingCriteria(List<APIObject> earlyStoppingCriteria) {
            this.obj.set("early_stopping_criteria", earlyStoppingCriteria);
            return this;
        }

    }
}
