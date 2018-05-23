package com.api.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author lxh 2014年12月22日
 * @Desc:
 */
public class GsonUtil {

	private static Gson gson = new Gson();

	/** 
	* @author lxh
	* @Desc: TODO 
	* @param json
	* @param clz
	* @return T
	*/
	public static <T> T toDomain(String json, Class<T> clz) {

		if (gson == null)
			gson = new Gson();

		T t = gson.fromJson(json, clz);
		return t;
	}

	/** 
	* @author lxh
	* @Desc: TODO 
	* @param t
	* @return String
	*/
	public static <T> String toJson(T t) {
		if (gson == null)
			gson = new Gson();

		String json = gson.toJson(t);
		return json;
	}

	public static <T> List<T> toList(String json, Class<T> clz) {
		if (gson == null)
			gson = new Gson();

		List<T> list = gson.fromJson(json, new TypeToken<List<T>>() {
		}.getType());

		return list;
	}

	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		if (gson == null)
			gson = new Gson();

		Map<String, T> map = gson.fromJson(json,
				new TypeToken<Map<String, T>>() {
				}.getType());

		return map;
	}

}
