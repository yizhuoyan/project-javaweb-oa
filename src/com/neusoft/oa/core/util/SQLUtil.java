package com.neusoft.oa.core.util;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class SQLUtil {
	private static final Map<Class<?>, String> TYPE_MAPPING = new HashMap<>();
	static {
		TYPE_MAPPING.put(String.class, "varchar(32)");
		TYPE_MAPPING.put(int.class, "int(4)");
		TYPE_MAPPING.put(Integer.class, "int(4)");
		TYPE_MAPPING.put(long.class, "int(8)");
		TYPE_MAPPING.put(Long.class, "int(8)");
		TYPE_MAPPING.put(float.class, "decimal(4,2)");
		TYPE_MAPPING.put(double.class, "decimal(4,2)");
		TYPE_MAPPING.put(Date.class, "datetime");
		TYPE_MAPPING.put(Instant.class, "datetime");
		TYPE_MAPPING.put(LocalDate.class, "date");
		TYPE_MAPPING.put(boolean.class, "bit(1)");
	}
	
	private static Map<String,Method> getGetterMethod(Class type)throws Exception{
		Method[] methods = type.getMethods();
		Map<String, Method> getterMap = new HashMap<>();
		for (Method method : methods) {
			String methodName = method.getName();
			if ((methodName.startsWith("get") || methodName.startsWith("is")) && method.getParameterCount() == 0
					&& method.getDeclaringClass() == type) {
				getterMap.put(clearSetterMethodName(methodName), method);
			}
		}
		return getterMap;
	}
	public static String generateCreateSQL(Class type) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("create table ").append(type.getSimpleName()).append("(\n");

		Map<String,Method> getterMap=getGetterMethod(type);

		for (Map.Entry<String, Method> entry : getterMap.entrySet()) {
			String name = entry.getKey();
			Method method = entry.getValue();

			Class<?> fieldType = method.getReturnType();
			String columnType = TYPE_MAPPING.get(fieldType);
			if (columnType == null) {
				columnType = "char(32)";
				name = name + "_id";
			}
			sql.append(name).append(" ").append(columnType).append(",\n");
		}
		sql.delete(sql.length() - 2, sql.length() - 1);
		sql.append(")");

		return sql.toString();
	}
	
	private static String clearSetterMethodName(String getterName) {
		if (getterName.startsWith("is")) {
			char[] cs = getterName.toCharArray();
			cs[2] += 32;
			return new String(cs, 2, cs.length - 2);
		}
		char[] cs = getterName.toCharArray();
		cs[3] += 32;
		return new String(cs, 3, cs.length - 3);
	}
	
	public static String generateInsertSql(Class type) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ")
		.append(type.getSimpleName()).append("(");

		Map<String,Method> getterMap=getGetterMethod(type);
		
		for (Map.Entry<String, Method> entry : getterMap.entrySet()) {
			String name = entry.getKey();
			Method method = entry.getValue();
			sql.append(name).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")\nvalues(");
		for(int i=getterMap.size();i-->1;) {
			sql.append("?,");
		}
		sql.append("?)");

		return sql.toString();
	}

	public static void main(String[] args) throws Exception {
		String sql = generateInsertSql(EmployeeEntity.class);
		System.out.println(sql);
	}
}
