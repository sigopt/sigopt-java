package com.sigopt.net;

import com.sigopt.model.APIObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class ParamsBuilder {
    public static Map<String, Object> build(Map<String, Object> params) {
        Map<String, Object> ret = new HashMap<String, Object>();
        params = MapHelper.ensure(params);
        for (Map.Entry<String, Object> entry: params.entrySet()) {
            if (entry.getValue() == null) { continue; }
            if (entry.getValue() instanceof APIObject) {
                ret.put(entry.getKey(), APIObject.toJson(entry.getValue()));
            } else {
                ret.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return ret;
    }
}
