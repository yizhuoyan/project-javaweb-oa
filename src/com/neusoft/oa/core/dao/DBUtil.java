package com.neusoft.oa.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class DBUtil {
	private static final String DRIVER = System.getProperty("db.driver-class"), URL = System.getProperty("db.url"),
			USERNAME = System.getProperty("db.username"), PASSWORD = System.getProperty("db.password");
	private static final String IP=System.getProperty("db.ip-port"),
			DATABASE_NAME=System.getProperty("db.name");
	
	
	private final static ThreadLocal<Connection> THREADLOCAL = new ThreadLocal<>();
	
	static {
		init();
	}
	
	private static void init() {
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
	public static Connection getConnection(boolean readonly) throws SQLException {
		// 1先从当前线程中获取数据库连接
		Connection connection = THREADLOCAL.get();
		// 2如果没有或者已关闭，则新建
		if (connection == null || connection.isClosed()) {
			connection=getNewConnection();
			// 3再放入当前线程中
			THREADLOCAL.set(connection);
		}
		connection.setReadOnly(readonly);
		return connection;
	}
	public static Connection getConnection() throws SQLException {
		return getConnection(false);
	}
	public static Connection getNewConnection() throws SQLException{
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
	
	
	
}
