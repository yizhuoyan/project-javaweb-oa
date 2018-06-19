package com.neusoft.oa.system.dao.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.SQLExecutor;
import com.neusoft.oa.core.dao.SQLGenerator;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.system.dao.SysRoleDao;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.entity.SysUserEntity;

public class SysRoleDaoImpl extends TemplateDaoImpl<SysRoleEntity> implements SysRoleDao {

	private SysRoleDaoImpl() {
		super("sys_role", "id,code,name,remark,createUser_id,createTime");
	}

	@Override
	public void insert(SysRoleEntity t) throws Exception {
		try (PreparedStatement ps = prepareUpdateStatement(generateInsertSql())) {
			int i = 1;
			ps.setString(i++, t.getId());
			ps.setString(i++, t.getCode());
			ps.setString(i++, t.getName());
			ps.setString(i++, t.getRemark());
			ps.setString(i++, t.getCreateUser()==null?null:t.getCreateUser().getId());
			ps.setTimestamp(i++, instant2timestamp(t.getCreateTime()));
			ps.executeUpdate();
		}
	}

	@Override
	public void joinModules(String id, String... moduelIds) throws Exception {
		String sql = SQLGenerator.generateInsertSql("rel_moduleofrole", "role_id,module_id");
		try (PreparedStatement ps = prepareUpdateStatement(sql)) {
			for (String mid : moduelIds) {
				ps.setString(1, id);
				ps.setString(2, mid);
				ps.executeUpdate();
			}
		}
	}

	@Override
	public void unjoinModules(String id, String... moduelIds) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append("rel_moduleofrole").append(" where role_id=? ");
		if (moduelIds.length != 0) {
			sql.append(" and module_id ").append("=? ");
			try (PreparedStatement ps = prepareUpdateStatement(sql)) {
				for (String mid : moduelIds) {
					ps.setString(1, id);
					ps.setString(2, mid);
					ps.executeUpdate();
				}
			}
		} else {
			try (PreparedStatement ps = prepareUpdateStatement(sql)) {
				ps.setString(1, id);
				ps.executeUpdate();
			}
		}
	}

	@Override
	public List<SysRoleEntity> selectsByKey(String key) throws Exception {
		// 2创建语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName);
		List<Object> parameters = new ArrayList<>(3);
		if (key != null) {
			sql.append(" where  ( code like ? or name like ? or remark like ? )");
			key = "%" + key + "%";
			parameters.add(key);
			parameters.add(key);
			parameters.add(key);
		}
		sql.append(" order by code desc");

		return selectManyRow(sql, parameters);
	}

	@Override
	public List<SysRoleEntity> selectsByUser(String userId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where id in(")
				.append("select role_id from  rel_userofrole  where  user_id=? ").append(") order by code");
		
		return selectManyRow(sql, userId);
		
	}
	

	@Override
	protected SysRoleEntity resultset2entity(ResultSet rs) throws Exception {
		SysRoleEntity e = new SysRoleEntity();
		e.setName(rs.getString("name"));
		e.setId(rs.getString("id"));
		e.setCode(rs.getString("code"));
		e.setRemark(rs.getString("remark"));
		e.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		String createUserId=rs.getString("createUser_id");
		if(createUserId!=null) {
			SysUserEntity createUser=new SysUserEntity();
			createUser.setId(createUserId);
			e.setCreateUser(createUser);
		}
		return e;
	}

	
}
