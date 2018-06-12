package com.neusoft.oa.core.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseTable {
	public final String schema;
	public final String tableName;
	private List<String> primaryColumns;
	private List<String> columns;

	public DatabaseTable(String schema, String tableName) {
		this.tableName = tableName;
		this.schema = schema;
	}

	
	private static List<String> loadTableColumns(String schema, String table) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select COLUMN_NAME ,COLUMN_KEY ").append(" from").append(" information_schema.COLUMNS")
					.append(" where TABLE_SCHEMA=?").append(" and table_name=?").append(" order by ORDINAL_POSITION");

			List<Map<String, Object>> result = DBUtil.selectManyRow2map(sql, schema, table.toUpperCase());
			for(Map<String,Object> map:result) {
				if("PRI".equalsIgnoreCase((String)map.get("COLUMN_KEY"))) {
					
				}
			}
			return null;
		} catch (SQLException e) {

		}
		return null;
	}

}
