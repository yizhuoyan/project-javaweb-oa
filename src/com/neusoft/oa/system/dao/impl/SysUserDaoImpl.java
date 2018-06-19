package com.neusoft.oa.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.SQLGenerator;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.system.dao.SysUserDao;
import com.neusoft.oa.system.entity.SysUserEntity;

/**
 * Dao 数据访问对象 和用户相关的数据库操作
 * 
 * @author yizhuoyan@hotmail.com
 *
 */
public class SysUserDaoImpl extends TemplateDaoImpl<SysUserEntity> implements SysUserDao {

	private SysUserDaoImpl() {
		super("sys_user",
				"id,account,name,password,createtime,lastLoginIP,lastlogintime,lastmodpasswordtime,phone,securityEmail,avatar,flag,remark,createUser_id");
	}

	@Override
	public void insert(SysUserEntity e) throws Exception {
		try (PreparedStatement ps = prepareUpdateStatement(generateInsertSql())) {
			int i = 1;
			ps.setString(i++,e.getId());
			ps.setString(i++,e.getAccount());
			ps.setString(i++,e.getName());
			ps.setString(i++,e.getPassword());
			ps.setTimestamp(i++,utilDate2timestamp(e.getCreateTime()));
			ps.setTimestamp(i++,utilDate2timestamp(e.getLastLoginTime()));
			ps.setString(i++,e.getLastLoginIP());
			ps.setTimestamp(i++,utilDate2timestamp(e.getLastModPasswordTime()));
			ps.setString(i++,e.getPhone());
			ps.setString(i++,e.getSecurityEmail());
			ps.setString(i++,e.getAvatar());
			ps.setString(i++,e.getRemark());
			ps.setInt(i++,e.getFlag());
			ps.setString(i++, e.getCreateUser()==null?null:e.getCreateUser().getId());
			ps.executeUpdate();
		}
	}

	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<SysUserEntity> pageData) throws Exception {
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName).append(" where flag!=").append(SysUserEntity.FLAG_DELETED);
		List<Object> parameters = new ArrayList<>(2);
		if (key != null) {
			whereSql.append(" and  ( account like ? or name like ?  )");
			key = "%" + key + "%";
			parameters.add(key);
			parameters.add(key);
		}

		int total = selectCount("select count(0) " + whereSql, parameters);
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by account desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
		selectManyRow(pageData,querySql, parameters);
		return total;
	}

	@Override
	public void joinRoles(String id, String... roleIds) throws Exception {
		String sql = SQLGenerator.generateInsertSql("rel_userofrole", "user_Id,role_id");
		PreparedStatement ps = prepareUpdateStatement(sql);
		// 3传入参数并执行语句对象
		for (String rid : roleIds) {
			ps.setString(1, id);
			ps.setString(2, rid);
			System.err.println(ps);
			ps.executeUpdate();
		}
	}

	@Override
	public void unjoinRoles(String id, String... roleIds) throws Exception {
		Connection connection = this.getCurrentConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append("rel_userofrole ").append(" where user_id=? ");
		if (roleIds.length != 0) {
			sql.append(" and role_id ").append("=? ");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			for (String rid : roleIds) {
				ps.setString(1, id);
				ps.setString(2, rid);
				ps.executeUpdate();
			}
		} else {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			ps.executeUpdate();
		}

	}

	@Override
	public int countUser() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from ").append(this.tableName).append(" where flag!=")
				.append(SysUserEntity.FLAG_DELETED);
		return selectCount(sql);
	}
	@Override
	public SysUserEntity select(String ufield, Object value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where flag!= ").append(SysUserEntity.FLAG_DELETED).append(" and ").append(ufield).append("=?");
		return selectOneRow(sql, value);
	}
	
	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from ").append(tableName).append(" where flag!=? and ").append(ufield).append("=?");
		return selectCount(sql,SysUserEntity.FLAG_DELETED, value) > 0;
	}
	

	@Override
	protected SysUserEntity resultset2entity(ResultSet rs) throws Exception {
		SysUserEntity e = new SysUserEntity();
		e.setId(rs.getString("id"));
		e.setName(rs.getString("name"));
		e.setLastModPasswordTime(rs.getTimestamp("lastModPasswordTime"));
		e.setAccount(rs.getString("account"));
		e.setLastLoginIP(rs.getString("lastLoginIP"));
		e.setPassword(rs.getString("password"));
		e.setCreateTime(rs.getTimestamp("createTime"));
		e.setLastLoginTime(rs.getTimestamp("lastLoginTime"));
		e.setPhone(rs.getString("phone"));
		e.setSecurityEmail(rs.getString("securityEmail"));
		e.setAvatar(rs.getString("avatar"));
		e.setRemark(rs.getString("remark"));
		e.setFlag(rs.getInt("flag"));
		String createUserId=rs.getString("createUser_id");
		if(createUserId!=null) {
			SysUserEntity createUser=new SysUserEntity();
			createUser.setId(createUserId);
			e.setCreateUser(createUser);
		}
			
		return e;

	}
}
