package com.neusoft.oa.core.dao;

import java.util.Arrays;
import java.util.List;

public interface SQLGenerator {
	
	public static String generateInsertSql(String table,List<String> columns) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(table).append('(');
		
		for (String column:columns) {
			sql.append(column).append(',');
		}
		sql.setCharAt(sql.length() - 1, ')');
		sql.append("values(");
		for (int i = columns.size(); i-->0;) {
			sql.append("?,");
		}
		sql.setCharAt(sql.length() - 1, ')');
		return sql.toString();
	}
	
	public static String generateInsertSql(String table, String columns) {
		return generateInsertSql(table, Arrays.asList(columns.split(",")));
	}
}
