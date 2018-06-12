package com.neusoft.oa.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppConfig {
	private static final String CONFIG_PATH = "/app.config";
	private final static Pattern PATTERN_REPLACE= Pattern.compile("\\{[^{}]+\\}");
	private static boolean LOADED=false;
	
	public static void load() throws Exception {
		if(LOADED)return;
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(AppConfig.class.getResourceAsStream(CONFIG_PATH), "utf-8"))) {
			String line = null;
			while ((reader.readLine()) != null) {
				if ((line = line.trim()).length() == 0) {
					continue;
				}
				String[] parts = line.split("=");
				if (parts.length > 1) {
					putProperty(parts[0], parts[1]);
				}
			}
			LOADED=true;
		}
	}

	static public void putProperty(String key, String value) {
		System.setProperty(actualValue(key), actualValue(value));
	}
	

	private static String actualValue(String str) {
		StringBuilder result = new StringBuilder();
		Matcher matcher = PATTERN_REPLACE.matcher(str);
		int begin = 0;
		while (matcher.find()) {
			result.append(str.substring(begin, matcher.start()));
			String key = str.substring(matcher.start() + 1, matcher.end() - 1);
			String actualValue = System.getProperty(key, key);
			result.append(actualValue);
			begin = matcher.end();
		}
		if (begin != str.length()) {
			result.append(str.substring(begin));
		}
		return result.toString();
	}
}
