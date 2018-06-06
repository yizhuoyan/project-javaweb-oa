package com.neusoft.oa.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.base.dao.SysModuleDao;
import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;

public class SysModuleDaoImpl extends TemplateDaoImpl<SysModuleEntity> implements SysModuleDao {

	private SysModuleDaoImpl() {
		super("sys_module");
	}
	@Override
	public void insert(SysModuleEntity t) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = DBUtil.generateInsertSql(this.tableName, "id,code,name,icon,url,parent_id,remark,showorder,flag");
		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		int i = 1;
		ps.setString(i++, t.getId());
		ps.setString(i++, t.getCode());
		ps.setString(i++, t.getName());
		ps.setString(i++, t.getIcon());
		ps.setString(i++, t.getUrl());
		ps.setString(i++, t.getParentId());
		ps.setString(i++, t.getRemark());
		ps.setString(i++, t.getShoworder());
		ps.setInt(i++, t.getFlag());
		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();
	}
	
	public void unjoinRole(String id) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append("rel_moduleofrole").append(" where module_id=? ");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, id);
		ps.executeUpdate();
		
	}
	@Override
	public List<SysModuleEntity> selectsByRoleId(String roleId) throws Exception {
		
		StringBuilder sql=new StringBuilder();
		sql.append("select m.* ")
		.append(" from ")
		.append(" sys_module m join rel_moduleofrole mr on m.id=mr.module_id") 
		.append(" join sys_role r on mr.role_id=r.id ")
		.append(" where r.id=?") 
		.append(" order by showorder,code");
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, roleId);
		ResultSet rs = ps.executeQuery();
		List<SysModuleEntity> result=new LinkedList<>();
		while(rs.next()) {
			result.add(this.resultset2entity(rs));
		}
		return result;
	}
	@Override
	public List<SysModuleEntity> selectsByKey(String key) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where flag!=9 ");
		if (key != null) {
			sql.append(" and ( code like ? or name like ? or url like ? or remark like ? )");

		}

		sql.append(" order by showorder desc,code");

		PreparedStatement ps = connection.prepareStatement(sql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		List<SysModuleEntity> result = new LinkedList<>();
		while (rs.next()) {
			SysModuleEntity e = resultset2entity(rs);
			result.add(e);
		}
		return result;
	}
	@Override
	public List<SysModuleEntity> selectsByUserId(String userId) throws Exception {
		StringBuilder sql=new StringBuilder();
		sql.append("select DISTINCT m.* ")
		.append(" from ")
		.append(" sys_module m join rel_moduleofrole mr on m.id=mr.module_id") 
		.append(" join sys_role r on mr.role_id=r.id ")
		.append(" join rel_userofrole ur on r.id=ur.role_id")
		.append(" join sys_user u on u.id=ur.user_id ")
		.append(" where u.id=?") 
		.append(" order by showorder,code");
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
		List<SysModuleEntity> result=new LinkedList<>();
		while(rs.next()) {
			result.add(this.resultset2entity(rs));
		}
		return result;
	}

	@Override
	protected SysModuleEntity resultset2entity(ResultSet rs) throws Exception {
		SysModuleEntity e = new SysModuleEntity();
		e.setCode(rs.getString("code"));
		e.setIcon(rs.getString("icon"));
		e.setName(rs.getString("name"));
		e.setUrl(rs.getString("url"));
		e.setParentId(rs.getString("parent_id"));
		e.setId(rs.getString("id"));
		e.setShoworder(rs.getString("showorder"));
		e.setRemark(rs.getString("remark"));
		e.setFlag(rs.getInt("flag"));
		return e;
	}
}
