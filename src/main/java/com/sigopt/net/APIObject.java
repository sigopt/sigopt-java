package com.sigopt.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

public class APIObject implements JsonSerializeable {
    public static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public String toJson() {
        return GSON.toJson(this);
    }

    @Override
    public String toString() {
        return String.format(
                "<%s@%s> Attributes: %s",
                this.getClass().getName(),
                System.identityHashCode(this),
                PRETTY_PRINT_GSON.toJson(this));
    }

    protected static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}
