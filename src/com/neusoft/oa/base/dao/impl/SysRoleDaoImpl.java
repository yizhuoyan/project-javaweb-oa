package com.neusoft.oa.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.base.dao.SysRoleDao;
import com.neusoft.oa.base.entity.SysRoleEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;

public class SysRoleDaoImpl extends TemplateDaoImpl<SysRoleEntity> implements SysRoleDao {

	private SysRoleDaoImpl() {
		super("sys_role");
	}

	@Override
	protected SysRoleEntity resultset2entity(ResultSet rs) throws Exception {
		SysRoleEntity e = new SysRoleEntity();
		e.setCode(rs.getString("code"));
		e.setName(rs.getString("name"));
		e.setId(rs.getString("id"));
		e.setRemark(rs.getString("remark"));
		return e;
	}

	@Override
	public void insert(SysRoleEntity t) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = DBUtil.generateInsertSql(this.tableName, "id,code,name,remark");
		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		int i = 1;
		ps.setString(i++, t.getId());
		ps.setString(i++, t.getCode());
		ps.setString(i++, t.getName());
		ps.setString(i++, t.getRemark());
		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();

	}

	@Override
	public void joinModules(String id, String... moduelIds) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = DBUtil.generateInsertSql("rel_moduleofrole", "role_id,module_id");
		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		for (String mid : moduelIds) {
			ps.setString(1, id);
			ps.setString(2, mid);
			ps.executeUpdate();
		}
	}

	@Override
	public void unjoinModules(String id, String... moduelIds) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append("rel_moduleofrole").append(" where role_id=? ");
		PreparedStatement ps = null;
		if (moduelIds.length != 0) {
			sql.append(" and module_id ").append("=? ");
			connection.prepareStatement(sql.toString());
			for (String mid : moduelIds) {
				ps.setString(1, id);
				ps.setString(2, mid);
				ps.executeUpdate();
			}
		} else {
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public List<SysRoleEntity> selectsByKey(String key) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName);
		if (key != null) {
			sql.append(" where  ( code like ? or name like ? or remark like ? )");

		}

		sql.append(" order by code desc");

		PreparedStatement ps = connection.prepareStatement(sql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		List<SysRoleEntity> result = new LinkedList<>();
		while (rs.next()) {
			SysRoleEntity e = resultset2entity(rs);
			result.add(e);
		}

		// 6关闭连接
		return result;
	}

	@Override
	public List<SysRoleEntity> selectsByUser(String userId) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName)
		.append(" where id in(")
		.append("select role_id from  rel_userofrole  where  user_id=? ")
		.append(") order by code");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		// 3传入参数
		ps.setString(1, userId);
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		List<SysRoleEntity> result = new LinkedList<>();
		while (rs.next()) {
			SysRoleEntity e = resultset2entity(rs);
			result.add(e);
		}

		return result;
	}

}
