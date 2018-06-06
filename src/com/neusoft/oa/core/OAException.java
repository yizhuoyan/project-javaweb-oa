package com.neusoft.oa.core;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 全局异常类
 * @author yizhuoyan@hotmail.com
 *
 */
public class OAException extends RuntimeException {

	public OAException(String message, Throwable cause) {
		super(message, cause);
	}

	public OAException(String message) {
		super(message);
	}
	public static void main(String[] args) {
		Object[] arr= {"a",new Object[]{1,2}};
		Stream.of(arr).flatMap(m->{
			if(m instanceof Array) {
				return Arrays.stream((Object[])m);
			}else {
				return Stream.of(m);
			}
		}).forEach(System.out::println);
		
	}
	public static void throwWithMessage(String message,Object... args) {
		
		
		for (int i = 0; i < args.length; i++) {
			message=message.replaceFirst("\\?",String.valueOf(args[0]));
		}
		throw new OAException(message);
	}
	
	
}
