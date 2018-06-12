package com.neusoft.oa.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DBUtil {
	private static final String DRIVER = System.getProperty("db.driver-class"), URL = System.getProperty("db.url"),
			USERNAME = System.getProperty("db.username"), PASSWORD = System.getProperty("db.password");

	private final static ThreadLocal<Connection> THREADLOCAL = new ThreadLocal<>();
	static {
		// 加载数据库驱动
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("未找到数据库驱动【" + DRIVER + "】，请检查！");
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() throws SQLException {
		// 1先从当前线程中获取数据库连接
		Connection connection = THREADLOCAL.get();
		// 2如果没有或者已关闭，则新建
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3再放入当前线程中
			THREADLOCAL.set(connection);
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			Connection connection = THREADLOCAL.get();
			if (connection != null && !connection.isClosed()) {
				connection.close();
				THREADLOCAL.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String uuid() {
		String uuid = UUID.randomUUID().toString();
		char[] cs = new char[32];
		char c = 0;
		for (int i = uuid.length(), j = 0; i-- > 0;) {
			if ((c = uuid.charAt(i)) != '-') {
				cs[j++] = c;
			}
		}
		return new String(cs);
	}
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
