package com.neusoft.oa.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.base.dao.SysUserDao;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;

/**
 * Dao 数据访问对象 和用户相关的数据库操作
 * 
 * @author yizhuoyan@hotmail.com
 *
 */
public class SysUserDaoImpl extends TemplateDaoImpl<SysUserEntity> implements SysUserDao {

	private SysUserDaoImpl() {
		super("sys_user");
	}
	
	@Override
	protected SysUserEntity resultset2entity(ResultSet rs) throws Exception {
		SysUserEntity u = new SysUserEntity();
		u.setAccount(rs.getString("account"));
		u.setAvatar(rs.getString("avatar"));
		u.setCreateTime(rs.getTimestamp("createtime"));
		u.setId(rs.getString("id"));
		u.setLastLoginTime(rs.getTimestamp("LastLoginTime"));
		u.setLastModPasswordTime(rs.getTimestamp("LastModPasswordTime"));
		u.setName(rs.getString("name"));
		u.setPassword(rs.getString("password"));
		u.setPhone(rs.getString("phone"));
		u.setRemark(rs.getString("remark"));
		u.setSecurityEmail(rs.getString("SecurityEmail"));
		u.setFlag(rs.getInt("flag"));
		u.setLastLoginIP(rs.getString("lastLoginIp"));
		return u;
	}
	public void insert(SysUserEntity u) throws SQLException {
		// 1 获取数据库连接
		Connection connection = DBUtil.getConnection();
			// 2 创建语句对象
			String sql = DBUtil.generateInsertSql(tableName,
					"id,account,name,password,createtime,lastlogintime,lastLoginIP,lastmodpasswordtime,phone,securityEmail,Avatar,remark,flag");
			try (PreparedStatement ps = connection.prepareStatement(sql.toString());) {

				// 3 执行语句对象（设置参数）
				int i = 1;
				ps.setString(i++, u.getId());
				ps.setString(i++, u.getAccount());
				ps.setString(i++, u.getName());
				ps.setString(i++, u.getPassword());
				ps.setTimestamp(i++, utilDate2timestamp(u.getCreateTime()));
				ps.setTimestamp(i++, utilDate2timestamp(u.getLastLoginTime()));
				ps.setString(i++, u.getLastLoginIP());
				ps.setTimestamp(i++, utilDate2timestamp(u.getLastModPasswordTime()));
				ps.setString(i++, u.getPhone());
				ps.setString(i++, u.getSecurityEmail());
				ps.setString(i++, u.getAvatar());
				ps.setString(i++, u.getRemark());
				ps.setInt(i++, u.getFlag());

				ps.executeUpdate();
			}
	}



	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<SysUserEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		//组装wheresql 
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName).append(" where flag!=")
				.append(SysUserEntity.FLAG_DELETED);
		if (key != null) {
			whereSql.append(" and  ( account like ? or name like ? or remark like ? )");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement("select count(*) "+whereSql);
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
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by account desc");
		querySql.append(" limit ").append((pageNo-1)*pageSize).append(",")
		.append(pageSize);
		

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			SysUserEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}
	@Override
	public void joinRoles(String id, String... roleIds) throws Exception {
		// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 2创建sql语句对象
				String sql = DBUtil.generateInsertSql("rel_userofrole", "user_Id,role_id");
				PreparedStatement ps = connection.prepareStatement(sql);
				// 3传入参数并执行语句对象
				for (String rid : roleIds) {
					ps.setString(1, id);
					ps.setString(2, rid);
					ps.executeUpdate();
				}
	}
	@Override
	public void unjoinRoles(String id, String... roleIds) throws Exception {
		// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 2创建sql语句对象
				StringBuilder sql = new StringBuilder();
				sql.append("delete from  ").append("rel_userofrole ").append(" where user_id=? ");
				if (roleIds.length != 0) {
					sql.append(" and role_id ").append("=? ");
					PreparedStatement ps=connection.prepareStatement(sql.toString());
					for (String rid : roleIds) {
						ps.setString(1, id);
						ps.setString(2, rid);
						ps.executeUpdate();
					}
				} else {
					PreparedStatement ps= connection.prepareStatement(sql.toString());
					ps.setString(1, id);
					ps.executeUpdate();
				}
				
	}
	
	@Override
	public int countUser() throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from ").append(this.tableName);
		
		Statement ps = connection.createStatement();

		ResultSet rs = ps.executeQuery(sql.toString());
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
}
