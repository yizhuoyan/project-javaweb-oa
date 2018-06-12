package com.neusoft.oa.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonUtil {
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {
		JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
		JSON_MAPPER.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
		SimpleModule module=new SimpleModule();
		module.addSerializer(LocalDate.class,new JsonSerializer<LocalDate>() {
			@Override
			public void serialize(LocalDate d, JsonGenerator generator, SerializerProvider provider) throws IOException {
				generator.writeString(d.format(DateTimeFormatter.ISO_LOCAL_DATE));
			}
		});
		
		module.addSerializer(Instant.class,new JsonSerializer<Instant>() {
			@Override
			public void serialize(Instant d, JsonGenerator generator, SerializerProvider provider) throws IOException {
				generator.writeNumber(d.getEpochSecond()*1000);
			}
		});
		JSON_MAPPER.registerModule(module);
		
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
