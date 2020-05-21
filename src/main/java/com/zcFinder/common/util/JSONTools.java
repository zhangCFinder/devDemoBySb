package com.zcFinder.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangc on 2019-3-29.
 */
public class JSONTools {
	public static final JSONArray getJSONArray(JSONObject json, String key) {
		JSONArray result = null;
		Object obj = getObject(json, key);
		if (obj != null && obj instanceof JSONArray) {
			result = (JSONArray)obj;
		}

		return result;
	}


	public static final Object getObject(JSONObject json, String key) {
		Object result = null;
		if (json != null && StringUtils.isNotEmpty(key) && json.containsKey(key)) {
			result = json.get(key);
		}

		return result;
	}


	public static final JSONObject getJSONObject(JSONObject json, String key) {
		JSONObject result = null;
		Object obj = getObject(json, key);
		if (obj != null && obj instanceof JSONObject) {
			result = (JSONObject)obj;
		}

		return result;
	}


}
