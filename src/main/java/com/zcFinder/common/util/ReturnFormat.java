package com.zcFinder.common.util;

import java.util.HashMap;
import java.util.Map;

//格式化返回客户端数据格式（json）
public class ReturnFormat {
	
	public static Map<String, Object> createResponse(String code, String message, Object data) {
		Map<String, Object> model = new HashMap<>();
		model.put("code", code);
		model.put("data", data);
		model.put("msg", message);
    	return model;
	}
}