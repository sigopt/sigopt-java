package com.sigopt.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class APIResource extends APIObject {

	static final JsonParser jsonParser = new JsonParser();
	static final Gson gson = new GsonBuilder().create();

	public static <T> T constructFromJson(String json, Class <T> klass) {

		return gson.fromJson(preProcessJson(json), klass);
	}

	public static <T> T constructListFromJson(String json, Type typeOfT) {
		return (T) gson.fromJson(preProcessJson(json), typeOfT);
	}

	static <T> String preProcessJson(String json) {
		JsonElement jsonElement = jsonParser.parse(json);
		for(Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().getAsJsonObject("response").entrySet()) {
			return entry.getValue().toString();
		}
		// This shouldn't happen.
		return json;
	}

	static String getClassNameUnderscore(Class klass) {
		String ret = klass.getSimpleName();

		Pattern r = Pattern.compile("([a-z])([A-Z])");
		Matcher m = r.matcher(ret);
		while(m.find()) {
			ret = ret.substring(0, m.start()) + m.group(1) + "_" + m.group(2).toLowerCase() + ret.substring(m.start() + 2);
			m = r.matcher(ret);
		}
		return ret.toLowerCase();
	}

}
