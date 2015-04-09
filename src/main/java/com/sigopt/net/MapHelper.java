package com.sigopt.net;

import java.util.HashMap;
import java.util.Map;

public class MapHelper {

    // toMerge will overwrite map values.
    public static <S, T> Map<S, T> merge(Map<S, T> map, Map<S, T> toMerge) {
        Map<S, T> ret = new HashMap<S, T>();
        ret.putAll(ensure(map));
        ret.putAll(ensure(toMerge));
        return ret;
    }

    public static <S, T> Map<S, T> ensure(Map<S, T> map) {
        if(map == null) {
            return new HashMap<S, T>();
        }
        return map;
    }

}
