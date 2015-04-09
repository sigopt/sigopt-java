package com.sigopt.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class ParamsBuilder {
    static final Gson gson = new GsonBuilder().create();

//    public static Map<String, Object> build(Map<String, Object> params) {
//        return MapHelper.ensure(params);
//    }

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
        if(ret.containsKey("data") && !(ret.get("data") instanceof String)) {
            ret.put("data", gson.toJson(ret.get("data")));
        }
        if(ret.containsKey("multi_data") && !(ret.get("multi_data") instanceof String)) {
            ret.put("multi_data", gson.toJson(ret.get("multi_data")));
        }

        return ret;
    }


}
