package com.sigopt.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParamsBuilder {
    public static final Gson gson = new GsonBuilder()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public static Map<String, Object> build(Map<String, Object> params, String clientToken, String userToken) {
        Map<String, Object> ret = new HashMap<String, Object>();

        // TODO(jon): Make this more generic.
        if(clientToken != null) {
            ret.put("client_token", clientToken);
        }
        if(userToken != null) {
            ret.put("user_token", userToken);
        }

        params = MapHelper.ensure(params);
        ret = MapHelper.merge(ret, params);
        for (Map.Entry<String, Object> entry: ret.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map || value instanceof Collection || value instanceof JsonSerializeable) {
                entry.setValue(gson.toJson(value));
            }
        }
        return ret;
    }
}
