package com.neusoft.thirdparty;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.neusoft.oa.core.util.JsonUtil;

public class WeatherThirdParty  {
	private static final String URL="https://www.sojson.com/open/api/weather/json.shtml?city=";
	/**
	 * 获取指定城市的天气预报
	 * @param cityName 城市名
	 */
	public static WeatherResponseJson get(String cityName)throws Exception{
		cityName=URLEncoder.encode(cityName,"utf-8");
		HttpURLConnection http=(HttpURLConnection) new java.net.URL(URL+cityName).openConnection();
		
		http.setRequestMethod("GET");
		
		http.connect();
		
		InputStream inputStream = http.getInputStream();
		
		WeatherResponseJson result = JsonUtil.parse(inputStream,WeatherResponseJson.class);
		
		return result;
	}
	
	public static void main(String[] args)throws Exception {
		WeatherResponseJson result = get("重庆");
		System.out.println(result);
	}
	

}
