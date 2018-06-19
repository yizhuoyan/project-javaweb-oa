package com.neusoft.oa.core.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SQLExecutor {

	public static List<Map<String, Object>> selectManyRow2map(Object sql, Object... parameters) throws SQLException {
		List<Map<String, Object>> result = new LinkedList<>();
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					String key = metaData.getColumnLabel(i);
					row.put(key, rs.getObject(i));
				}
				result.add(row);
			}
		}
		return result;
	}

	public static <T> List<T> selectManyRowOneColumn(Object sql, Object... parameters) throws SQLException {
		List<Object> result = new LinkedList<>();
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			while (rs.next()) {
				result.add(rs.getObject(1));
			}
		}
		return (List<T>) result;
	}

	public static Map<String, Object> selectOneRow2map(Object sql, Object... parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			if (rs.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					String key = metaData.getColumnLabel(i);
					row.put(key, rs.getObject(i));
				}
				return row;
			}
		}
		return null;
	}

	public static Object[] selectOneRow2Array(Object sql, Object... parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			if (rs.next()) {
				Object[] result = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					result[i] = rs.getObject(i);
				}
				return result;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public static <T> T selectOneRowOneColumn(Object sql, Object... parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return (T) rs.getObject(1);
			}
		}
		return null;
	}

	public static int selectCount(Object sql, Object... parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	public static int selectCount(Object sql, List<Object> parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	public static void executeDeleteSql(Object sql, Object... parameters) throws SQLException {
		executeUpdateSql(sql, parameters);
	}

	public static void executeInsertSql(Object sql, Object... parameters) throws SQLException {
		executeUpdateSql(sql, parameters);
	}

	public static void executeUpdateSql(Object sql, Object... parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(false);
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			System.out.println(ps);
			ps.executeUpdate();
		}
	}

}
