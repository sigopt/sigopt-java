package com.sigopt.net;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class APIResource extends APIObject {

    static final JsonParser jsonParser = new JsonParser();
    static final Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public static <T extends Object> T constructFromJson(String json, Class <T> klass) {
        return gson.fromJson(json, klass);
    }

    public static <T extends Object> T constructTypedFromJson(String json, Type typeOfT) {
        return (T) gson.fromJson(json, typeOfT);
    }
}
