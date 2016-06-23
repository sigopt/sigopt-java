package com.sigopt.net;

import com.sigopt.model.APIObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class ParamsBuilder {
    public static Map<String, Object> build(Map<String, Object> params) {
        Map<String, Object> ret = new HashMap<String, Object>();

        params = MapHelper.ensure(params);
        ret = MapHelper.merge(ret, params);
        for (Map.Entry<String, Object> entry: ret.entrySet()) {
            entry.setValue(APIObject.toJson(value));
        }
        return ret;
    }
}
