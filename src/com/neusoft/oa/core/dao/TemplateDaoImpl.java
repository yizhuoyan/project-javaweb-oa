package com.neusoft.oa.core.dao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class TemplateDaoImpl<T> extends AbstractTemplateDaoImpl<T> implements TemplateDao<T>, TypeConverter {
	final protected String tableName;
	final protected List<String> columns;
	final protected String idColumn;
	final protected Class<T> entityType;
	final protected Map<String, Method> entitySetterMap = new HashMap<>();

	public TemplateDaoImpl(String tableName, String columns) {
		this.tableName = tableName;
		this.columns = Arrays.asList(columns.toUpperCase().split(","));
		this.idColumn = this.columns.get(0);
		this.entityType = this.getEntityClass();
		saveSetter();
	}

	public TemplateDaoImpl(String tableName) {
		this(tableName, "id");
	}

	protected boolean containsColumn(String column) {
		return this.columns.contains(column.toUpperCase());
	}

	// 获取子类的定义的泛型T类型
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<T>) params[0];
	}

	protected PreparedStatement prepareUpdateStatement(Object sql) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(false);
		return connection.prepareStatement(sql.toString());
	}

	protected PreparedStatement prepareSelectStatement(Object sql) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setReadOnly(true);
		return connection.prepareStatement(sql.toString());
	}

	public T selectOneRow(Object sql, Object... parameters) throws Exception {
		try (PreparedStatement ps = prepareSelectStatement(sql)) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[0]);
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			if (rs.next()) {
				return resultset2entity(rs);
			}
		}
		return null;
	}

	public T selectOneRow(Object sql, List<Object> parameters) throws Exception {
		try (PreparedStatement ps = prepareSelectStatement(sql)) {
			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return resultset2entity(rs);
			}
		}
		return null;

	}

	public List<T> selectManyRow(Object sql, List<Object> parameters) throws Exception {
		List<T> result = new LinkedList<>();
		selectManyRow(result,sql, parameters);
		return result;
	}


	public List<T> selectManyRow(Object sql, Object... parameters) throws Exception {
		List<T> result = new LinkedList<>();
		selectManyRow(result, sql, parameters);
		return result;
	}

	public void selectManyRow(List<T> result, Object sql, Object... parameters) throws Exception {
		try (PreparedStatement ps = prepareSelectStatement(sql)) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(resultset2entity(rs));
			}
		}
	}
	public void selectManyRow(List<T> result, Object sql, List<Object> parameters) throws Exception {
		try (PreparedStatement ps = prepareSelectStatement(sql)) {
			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(resultset2entity(rs));
			}
		}
	}

	/**
	 * 保存所有setter方法 key=去掉setter全大写 value=Method
	 */
	private void saveSetter() {
		Method[] methods = this.entityType.getMethods();
		String methodName = null;
		for (Method m : methods) {
			methodName = m.getName();
			if (methodName.startsWith("set") && m.getParameterCount() == 1) {
				methodName = methodName.substring(3);
				entitySetterMap.put(methodName.toUpperCase(), m);
			}
		}
	}

	public abstract void insert(T t) throws Exception;

	protected String generateInsertSql(String... excludes) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(tableName).append('(');
		if (excludes.length > 0) {
			Set<String> excludeSet = new HashSet<>(excludes.length, 1);
			for (String ex : excludes) {
				excludeSet.add(ex.toUpperCase());
			}
			for (String column : columns) {
				if (excludeSet.contains(column)) {
					excludeSet.remove(column);
					continue;
				}
				sql.append(column).append(',');
			}
		} else {
			for (String column : columns) {
				sql.append(column).append(',');
			}
		}
		sql.setCharAt(sql.length() - 1, ')');
		sql.append("values(");
		for (int i = columns.size(); i-- > 0;) {
			sql.append("?,");
		}
		sql.setCharAt(sql.length() - 1, ')');
		return sql.toString();
	}

	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from ").append(tableName).append(" where ").append(ufield).append("=?");
		return selectCount(sql, value) > 0;
	}

	@Override
	public int selectCountAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) from ").append(this.tableName);
		return selectCount(sql);
	}

	@Override
	public List<T> selectAll(String... orderbys) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName);
		if (orderbys.length > 0) {
			sql.append(" order by ");
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length() - 1, ' ');
		}
		return selectManyRow(sql);
	}

	@Override
	public List<T> selectAllByColumn(String field, Object value, String... orderbys) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where ").append(field);
		if (value == null) {
			sql.append(" is null ");
		} else {
			sql.append("=?");
		}
		if (orderbys.length > 0) {
			sql.append(" order by ");
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length() - 1, ' ');
		}
		if (value != null) {
			return selectManyRow(sql, value);
		} else {
			return selectManyRow(sql);
		}
	}

	@Override
	public void delete(String ufield, Object value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append(tableName).append(" where ");
		sql.append(ufield).append("=? ");
		executeDeleteSql(sql, value);
	}

	@Override
	public void update(Serializable id, Map<String, Object> columns) throws Exception {
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(tableName).append(" set ");
		// 保持参数值
		Object[] values = new Object[columns.size() + 1];
		int parameterIndex = 0;
		for (Map.Entry<String, Object> entry : columns.entrySet()) {
			sql.append(entry.getKey()).append("=?,");
			values[parameterIndex++] = entry.getValue();
		}
		sql.setCharAt(sql.length() - 1, ' ');
		sql.append(" where ").append(idColumn).append("=?");
		values[parameterIndex++] = id;
		executeUpdateSql(sql, values);

	}

	@Override
	public void update(Serializable id, String column, Object value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(this.tableName).append(" set ");
		sql.append(column).append("=? ");
		sql.append(" where ").append(idColumn).append("=?");
		executeUpdateSql(sql, value, id);
	}

	@Override
	public T select(String ufield, Object value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where ").append(ufield).append("=?");
		return selectOneRow(sql, value);
	}

	abstract protected T resultset2entity(ResultSet rs) throws Exception;

}
