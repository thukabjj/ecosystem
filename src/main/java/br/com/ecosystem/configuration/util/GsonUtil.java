package br.com.ecosystem.configuration.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static Gson gson;

	public static synchronized String getInstance(Object item) {
		if (gson == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gson = gsonBuilder.create();
		}
		String json = gson.toJson(item);
		return json;
	}
}
