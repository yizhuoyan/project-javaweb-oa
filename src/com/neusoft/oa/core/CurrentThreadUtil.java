package com.neusoft.oa.core;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.oa.core.dto.UserContext;

public class CurrentThreadUtil {
	private static final ThreadLocal<Map<String,Object>> THREAD_LOCAL=new ThreadLocal<>();
	private static final String KEY_CURRENT_USER_ID="currentUserId";
	
	public static String getCurrentUserId() {
		Map<String,Object> map=THREAD_LOCAL.get();
		return (String)map.get(KEY_CURRENT_USER_ID);
	}
	
	public static void setCurrentUserId(String currentUserId) {
		Map<String,Object> map=THREAD_LOCAL.get();
		if(map==null) {
			THREAD_LOCAL.set(map=new HashMap<>());
		}
		map.put(KEY_CURRENT_USER_ID, currentUserId);
	}
}
