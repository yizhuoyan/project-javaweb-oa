package com.neusoft.oa.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class TemplateDaoImpl<T> implements TemplateDao<T> {
	final protected String tableName;

		
	protected TemplateDaoImpl(String tableName) {
		this.tableName = tableName;
	}
	
	abstract	public void insert(T t) throws Exception;
	protected Connection getConnection() throws SQLException{
		return DBUtil.getConnection();
	}
	protected Timestamp utilDate2timestamp(java.util.Date d ) {
		if (d != null) {
			return  new Timestamp(d.getTime());
		} 
		return null;
	}
	protected Timestamp instant2timestamp(Instant instant) {
		if (instant != null) {
			return  new Timestamp(instant.getEpochSecond());
		} 
		return null;
	}
	protected Instant timestamp2Instant(Timestamp timestamp ) {
		if (timestamp != null) {
			return  timestamp.toInstant();
		} 
		return null;
	}
	protected LocalDate sqlDate2LocalDate(java.sql.Date d) {
		if (d != null) {
			return  d.toLocalDate();
		} 
		return null;
	}
	protected java.sql.Date localDate2sqlDate(LocalDate localDate) {
		if (localDate != null) {
			return  java.sql.Date.valueOf(localDate);
		} 
		return null;
	}
	
	@Override
	public List<T> selectAll(String... orderbys) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName);
		if(orderbys.length>0) {
			sql.append(" order by ");	
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length()-1, ' ');
		}
		PreparedStatement ps = connection.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		List<T> result=new LinkedList<>();
		while (rs.next()) {
			result.add(resultset2entity(rs));
		}
		return result;
	}
	@Override
	public List<T> selectAllByColumn(String field, Object value,String... orderbys) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName)
		.append(" where ").append(field);
		if(value==null) {
			sql.append(" is null ");
		}else {
			sql.append("=?");
		}
		if(orderbys.length>0) {
			sql.append(" order by ");
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length()-1, ' ');
		}
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		if(value!=null) {
			ps.setObject(1, value);
		}
		ResultSet rs = ps.executeQuery();
		List<T> result=new LinkedList<>();
		while (rs.next()) {
			result.add(resultset2entity(rs));
		}
		return result;
	}

	@Override
	public void delete(String ufield, Object value) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append(tableName).append(" where ");
		sql.append(ufield).append("=? ");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		// 3传入参数并执行语句对象
		ps.setObject(1, value);
		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();
	}

	@Override
	public void update(Serializable id, Map<String, Object> columns) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(tableName).append(" set ");
		// 保持参数值
		List<Object> values = new ArrayList<>(columns.size());
		for (Map.Entry<String, Object> entry : columns.entrySet()) {
			sql.append(entry.getKey()).append("=?,");
			values.add(entry.getValue());
		}
		sql.setCharAt(sql.length() - 1, ' ');
		sql.append(" where id=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());

		// 3传入参数并执行语句对象
		for (int i = 0, len = values.size(); i < len; i++) {
			ps.setObject(i + 1, values.get(i));
		}
		ps.setObject(values.size() + 1, id);
		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();

	}

	@Override
	public void update(Serializable id, String column, Object value) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(this.tableName).append(" set ");
		sql.append(column).append("=? ");
		sql.append(" where id=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());

		// 3传入参数并执行语句对象
		ps.setObject(1, value);
		ps.setObject(2, id);
		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();
	}

	@Override
	public T select(String ufield, Object value) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where ").append(ufield).append("=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		
		ps.setObject(1, value);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return resultset2entity(rs);
		}
		return null;
	}

	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		Connection connection = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from ").append(tableName).append(" where ").append(ufield).append("=?");
			PreparedStatement ps = connection.prepareStatement(sql.toString());

			ps.setObject(1, value);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
			return false;
	}

	protected abstract T resultset2entity(ResultSet rs) throws Exception;
}
