package com.neusoft.oa.core.service;

import java.util.Map;
import java.util.WeakHashMap;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.SQLExecutor;

public class PinYinService {
	private static Map<Integer, String> CACHE_MAP = new WeakHashMap<>();
	public static String pinyin(String str) {
		StringBuilder result=new StringBuilder();
		for(int i=0;i<str.length();i++) {
			int code=Character.codePointAt(str, i);
			if(0x4e00<code&&code<=0x9fa5) {
				String one=pinyin(code);
				if(one!=null) {
					 result.append(one.replaceAll("\\d", ""));
				 }
			}else {
				result.append(str.charAt(i));
			}
		}
		return result.toString();
	}
	public static String pinyin(int unicode) {
		try {
			Integer key = Integer.valueOf(unicode);
			String result = CACHE_MAP.get(key);
			if (result == null) {
				StringBuilder sql = new StringBuilder("select spelling from dic_pinyin where unicode=?");
				result = SQLExecutor.selectOneRowOneColumn(sql, unicode);
				CACHE_MAP.put(key, result);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
