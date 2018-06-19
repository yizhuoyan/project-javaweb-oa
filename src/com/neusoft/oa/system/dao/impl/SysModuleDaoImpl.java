package com.neusoft.oa.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.system.dao.SysModuleDao;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.entity.SysUserEntity;

public class SysModuleDaoImpl extends TemplateDaoImpl<SysModuleEntity> implements SysModuleDao {

	private SysModuleDaoImpl() {
		super("sys_module", "id,parent_id,code,name,icon,url,showOrder,flag,remark,createuser_id,createtime");
	}

	@Override
	public void insert(SysModuleEntity e) throws Exception {
		try (PreparedStatement ps = prepareUpdateStatement(generateInsertSql())) {
			int i = 1;
			ps.setString(i++,e.getId());
			ps.setString(i++,e.getParentModule()==null?null:e.getParentModule().getId());
			ps.setString(i++,e.getCode());
			ps.setString(i++,e.getName());
			ps.setString(i++,e.getIcon());
			ps.setString(i++,e.getUrl());
			ps.setString(i++,e.getShowOrder());
			ps.setInt(i++,e.getFlag());
			ps.setString(i++,e.getRemark());
			ps.setString(i++,e.getCreateUser()==null?null:e.getCreateUser().getId());
			ps.setTimestamp(i++,instant2timestamp(e.getCreateTime()));
			ps.executeUpdate();
		}

		
	}
	

	public void unjoinRole(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ").append("rel_moduleofrole").append(" where module_id=? ");
		try (PreparedStatement ps = prepareUpdateStatement(sql)) {
			ps.setString(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public List<SysModuleEntity> selectsByRoleId(String roleId) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select m.* ").append(" from ").append(" sys_module m join rel_moduleofrole mr on m.id=mr.module_id")
				.append(" join sys_role r on mr.role_id=r.id ").append(" where r.id=?")
				.append(" order by showOrder,code");
		return selectManyRow(sql, roleId);
	}

	@Override
	public List<SysModuleEntity> selectsByKey(String key) throws Exception {
		// 2创建语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where flag!=9 ");
		List<Object> parameters=new ArrayList<>();
		if (key != null) {
			sql.append(" and ( code like ? or name like ? or url like ? or remark like ? )");
			key = "%" + key + "%";
			parameters.add(key);
			parameters.add(key);
			parameters.add(key);
			parameters.add(key);
		}
		sql.append(" order by showOrder desc,code");	
		return selectManyRow(sql, parameters);
	}

	@Override
	public List<SysModuleEntity> selectsByUserId(String userId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT m.* ").append(" from ")
				.append(" sys_module m join rel_moduleofrole mr on m.id=mr.module_id")
				.append(" join sys_role r on mr.role_id=r.id ").append(" join rel_userofrole ur on r.id=ur.role_id")
				.append(" join sys_user u on u.id=ur.user_id ").append(" where u.id=?")
				.append(" order by showOrder,code");
		return selectManyRow(sql, userId);
	}

	@Override
	protected SysModuleEntity resultset2entity(ResultSet rs) throws Exception {
		SysModuleEntity e = new SysModuleEntity();
		e.setName(rs.getString("name"));
		e.setFlag(rs.getInt("flag"));
		e.setId(rs.getString("id"));
		e.setCode(rs.getString("code"));
		e.setIcon(rs.getString("icon"));
		e.setUrl(rs.getString("url"));
		String parentModuleId=rs.getString("parent_id");
		if(parentModuleId!=null){
			SysModuleEntity parentModule= new SysModuleEntity();
			parentModule.setId(parentModuleId);
			e.setParentModule(parentModule);
		}
		e.setRemark(rs.getString("remark"));
		e.setShowOrder(rs.getString("showOrder"));
		String createUserId=rs.getString("createUser_id");
		if(createUserId!=null){
			SysUserEntity createUser= new SysUserEntity();
			createUser.setId(createUserId);
			e.setCreateUser(createUser);
		}
		e.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		return e;

	}



}
