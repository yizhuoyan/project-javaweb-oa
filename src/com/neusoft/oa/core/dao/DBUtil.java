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
	
	public static List<Map<String,Object>> selectManyRow2map(Object sql,Object... parameters)throws SQLException{
		List<Map<String, Object>> result=new LinkedList<>();
		Connection connection=DBUtil.getConnection();
		try(PreparedStatement ps=connection.prepareStatement(sql.toString())){
			for(int i=0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			
			while(rs.next()) {
				Map<String, Object> row=new HashMap<>();
				for(int i=0;i<columnCount;i++) {
					String key=metaData.getColumnLabel(i);
					row.put(key, rs.getObject(i));
				}
				result.add(row);
			}
		}
		return result;
	}
	public static <T>List<T> selectManyRowOneColumn(Object sql,Object... parameters)throws SQLException{
		List<Object> result=new LinkedList<>();
		Connection connection=DBUtil.getConnection();
		try(PreparedStatement ps=connection.prepareStatement(sql.toString())){
			for(int i=0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			while(rs.next()) {
				result.add(rs.getObject(1));
			}
		}
		return (List<T>) result;
	}
	
	public static Map<String,Object> selectOneRow2map(Object sql,Object... parameters)throws SQLException{
		Connection connection=DBUtil.getConnection();
		try(PreparedStatement ps=connection.prepareStatement(sql.toString())){
			for(int i=0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			if(rs.next()) {
				Map<String, Object> row=new HashMap<>();
				for(int i=0;i<columnCount;i++) {
					String key=metaData.getColumnLabel(i);
					row.put(key, rs.getObject(i));
				}
				return row;
			}
		}
		return null;
	}

	public static Object[] selectOneRow2Array(Object sql,Object... parameters)throws SQLException{
		Connection connection=DBUtil.getConnection();
		try(PreparedStatement ps=connection.prepareStatement(sql.toString())){
			for(int i=0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			if(rs.next()) {
				Object[] result=new Object[columnCount];
				for(int i=0;i<columnCount;i++) {
					result[i]=rs.getObject(i);
				}
				return result;
			}
		}
		return null;
	}
	/**
	 * count语句返回Long型
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public static <T>T selectOneRowOneColumn(Object sql,Object... parameters)throws SQLException{
		Connection connection=DBUtil.getConnection();
		try(PreparedStatement ps=connection.prepareStatement(sql.toString())){
			for(int i=0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return (T)rs.getObject(1);
			}
		}
		return null;
	}
}
