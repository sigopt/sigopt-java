package com.sigopt.model;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class StoppingCriteria extends StructObject {
	public StoppingCriteria() {
		super();
	}

	public Boolean getShouldStop() {
		return (Boolean) this.get("should_stop");
	}

  public List<String> getReasons() {
    return (List<String>) this.get("reasons");
  }

  public static class Builder extends StructObjectBuilder<StoppingCriteria> {
    public Builder() {
      this.obj = new StoppingCriteria();
    }

    public Builder shouldStop(Boolean shouldStop) {
      this.obj.set("should_stop", shouldStop);
      return this;
    }

    public Builder reasons(List<String> reasons) {
      this.obj.set("reasons", reasons);
      return this;
    }

  }

}
