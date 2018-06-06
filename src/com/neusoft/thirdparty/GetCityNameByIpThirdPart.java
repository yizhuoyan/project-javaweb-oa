package com.neusoft.thirdparty;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.oa.core.util.JsonUtil;


public class GetCityNameByIpThirdPart {
	private static final String URL="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
	public static String get(String ip)throws Exception{
		
		HttpURLConnection http=(HttpURLConnection) new java.net.URL(URL+ip).openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		http.setRequestProperty("Accept-Encoding","gzip, deflate, sdch");
		http.setRequestProperty("Accept-Language","en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		http.setRequestProperty("Cache-Control","no-cache");
		http.setRequestProperty("Host","int.dpool.sina.com.cn");
		http.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
				
		http.connect();
		
		InputStream inputStream = http.getInputStream();
		
		ObjectMapper mapper=JsonUtil.getObjectMapper();
		JsonNode root = mapper.readTree(inputStream);
		return root.get("city").asText();
	}
	
	public static void main(String[] args) throws Exception{
		String city=get("183.67.56.132");
		System.out.println(city);
	}
	
}
