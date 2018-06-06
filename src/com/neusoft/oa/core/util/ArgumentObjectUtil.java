package com.neusoft.oa.core.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ArgumentObjectUtil {
/**
 * 
 * @param req
 * @param type
 * @return
 * @throws Exception
 */
static	public <T> T parse(HttpServletRequest req,Class<T> type)throws Exception{
	  	//1 创建对象
		T t = type.newInstance();
		//2 获取其类中定义所有setter方法（要求setter放仅有一个字符串参数）
		//2.1 获取所有公开的方法
		Method[] methods = type.getMethods();
		//2.2 找到其中的setter方法(仅有一个字符串参数)
		Map<String, Method> setterMap=new HashMap<>();
		for (Method method : methods) {
			if(method.getName().startsWith("set")
					&&method.getParameterCount()==1
					&&method.getParameterTypes()[0]==String.class) {
				setterMap.put(clearSetterMethodName(method.getName()), method);
			}
		}
		
		//3遍历执行setter方法
		for(Map.Entry<String, Method> entry:setterMap.entrySet()) {
			String argumentName=entry.getKey();
			String argumentValue=req.getParameter(argumentName);
			if(argumentValue!=null) {
				Method method=entry.getValue();
				method.invoke(t, argumentValue);
			}
		}
		return t;
	}
	private static String clearSetterMethodName(String setterName) {
		char[] cs=setterName.toCharArray();
		cs[3]+=32;
		return new String(cs,3,cs.length-3);
	}
	
	public static void main(String[] args) {
		String result=clearSetterMethodName("setNidaye");
		System.out.println(result);
	}
	
}
