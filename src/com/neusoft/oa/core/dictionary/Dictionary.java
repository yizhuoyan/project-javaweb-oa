package com.neusoft.oa.core.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.neusoft.oa.core.util.JsonUtil;

public class Dictionary {
	private final static Map<String,Dictionary> DICTIONARY_MAP=new HashMap<>();
	private static final Class<Dictionary> TYPE=Dictionary.class;
	
	private final Map<String,Object> map=new LinkedHashMap<>();
	public final String type;
	public Dictionary(String type,Map<String,Object> map) {
		this.map.putAll(map);
		this.type=type;
	}
	
	public Object put(String key,Object value) {
		return this.map.put(key, value);
	}
	
	public boolean contains(String key) {
		return this.map.containsKey(key);
	}
	
	public String toJsonString() {
		return JsonUtil.toJsonString(this.map);
	}
	
	
	
	
	
	
	public static Dictionary of(String type){
		Dictionary dictionary=DICTIONARY_MAP.get(type);
		if(dictionary==null) {
			dictionary=loadDictionary(type);
			if(dictionary==null)return null;
			DICTIONARY_MAP.put(type, dictionary);
		}
		return dictionary;
	}
	private static Dictionary loadDictionary(String type) {
		InputStream in = TYPE.getResourceAsStream(type);
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(in, "utf-8"))){
			String line=null;
			Map<String,Object> map=new LinkedHashMap<>();
			int rowno=0;
			while((line=reader.readLine())!=null) {
				line=line.trim();
				if(line.length()==0||line.charAt(0)=='#') {
					continue;
				}
				rowno++;
				String[] items=line.split("=");
				switch (items.length) {
				case 1:
					map.put(Integer.toString(rowno), items[0]);
					break;
				case 2:
					map.put(items[0], items[1]);
					break;
				default:
					break;
				}
			}
			return new Dictionary(type,map);
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		Dictionary of = Dictionary.of("marital-status");
		System.out.println(of.toJsonString());
	}
}
