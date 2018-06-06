package com.neusoft.oa.core.util;

import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {
		JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
		JSON_MAPPER.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
	}
	public static ObjectMapper getObjectMapper() {
		return JSON_MAPPER;
	}
	public static String toJsonString(Object o) {
		try {
			return JSON_MAPPER.writeValueAsString(o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T parse(String jsonString, Class<T> type) {
		try {
			return JSON_MAPPER.readValue(jsonString, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T parse(InputStream inputStream, Class<T> type) {
		try {
			return JSON_MAPPER.readValue(inputStream, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
