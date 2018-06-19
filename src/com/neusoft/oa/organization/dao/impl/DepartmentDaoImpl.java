package com.neusoft.oa.organization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.SQLGenerator;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DepartmentDaoImpl extends TemplateDaoImpl<DepartmentEntity> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super("oa_dept", "id,code,manager_id,createTime,members,name,remark,parent_id");
	}
	@Override
	public void insert(DepartmentEntity e) throws Exception {
		try (PreparedStatement ps = prepareUpdateStatement(generateInsertSql())) {
			int i = 1;
			ps.setString(i++,e.getId());
			ps.setString(i++,e.getCode());
			ps.setString(i++,e.getManager()==null?null:e.getManager().getId());
			ps.setTimestamp(i++,instant2timestamp(e.getCreateTime()));
			ps.setInt(i++,e.getMembers());
			ps.setString(i++,e.getName());
			ps.setString(i++,e.getRemark());
			ps.setString(i++,e.getParent()==null?null:e.getParent().getId());
			ps.executeUpdate();
		}

	}
	@Override
	public void updateDepartmentMembers(String id, int append) throws Exception {
		Connection connection = this.getCurrentConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(tableName).append(" set members=members");
		if (append > 0) {
			sql.append("+").append(append);
		} else {
			sql.append(append);
		}
		sql.append(" where id=?");
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			ps.setString(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public boolean existsChildren(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from ").append(tableName).append(" where parent_id=?");

		return selectCount(sql, id) > 0;
	}

	

	@Override
	public List<DepartmentEntity> selectsExcludeWithDescendantByTreeCode(String treeCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(tableName);
		sql.append(" where code not like ?");
		return selectManyRow(sql, treeCode + "%");
	}
	
	
	@Override
	protected DepartmentEntity resultset2entity(ResultSet rs) throws Exception {
		DepartmentEntity e = new DepartmentEntity();
		e.setId(rs.getString("id"));
		e.setName(rs.getString("name"));
		e.setCode(rs.getString("code"));
		String parentId=rs.getString("parent_id");
		if(parentId!=null){
			DepartmentEntity parent= new DepartmentEntity();
			parent.setId(parentId);
			e.setParent(parent);
		}
		String managerId=rs.getString("manager_id");
		if(managerId!=null){
			EmployeeEntity manager= new EmployeeEntity();
			manager.setId(managerId);
			e.setManager(manager);
		}
		e.setMembers(rs.getInt("members"));
		e.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		e.setRemark(rs.getString("remark"));
		return e;
		
	}

}
