package com.neusoft.oa.organization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DepartmentDaoImpl extends TemplateDaoImpl<DepartmentEntity> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super("oa_dept");
	}

	@Override
	public boolean existsChildren(String id) throws Exception {
		Connection connection=this.getConnection();
		
		StringBuilder sql=new StringBuilder();
		sql.append("select count(1) from ")
		.append(tableName).append(" where parent_id=?");
		
		try(PreparedStatement ps=connection.prepareStatement(sql.toString())){
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)>0;
			}
		}
		return false;
	}
	
	@Override
	public void insert(DepartmentEntity t) throws Exception {
		Connection connection = this.getConnection();
		String sql = DBUtil.generateInsertSql(tableName, "id,code,manager_id,createTime,members,name,remark,parent_id");
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			int i = 1;
			ps.setString(i++, t.getId());
			ps.setString(i++, t.getCode());
			{
				EmployeeEntity manager = t.getManager();
				ps.setString(i++, manager == null ? null : manager.getId());
			}
			ps.setTimestamp(i++, instant2timestamp(t.getCreateTime()));
			ps.setInt(i++, t.getMembers());
			ps.setString(i++, t.getName());
			ps.setString(i++, t.getRemark());
			{
				DepartmentEntity parent = t.getParent();
				ps.setString(i++, parent == null ? null : parent.getId());
			}
			ps.executeUpdate();
		}

	}
	@Override
	public List<DepartmentEntity> selectsExcludeWithDescendantByTreeCode(String treeCode) throws Exception {
		List<DepartmentEntity> result=new LinkedList<>(); 	
		Connection connection=this.getConnection();
		
		StringBuilder sql=new StringBuilder();
		sql.append("select * from ").append(tableName);
		sql.append(" where code not like ?");
		try(PreparedStatement ps = connection.prepareStatement(sql.toString())){
			ps.setString(1, treeCode+"%");	
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(resultset2entity(rs));
			}
		}
		return result;
	}

	@Override
	protected DepartmentEntity resultset2entity(ResultSet rs) throws Exception {
		DepartmentEntity e=new DepartmentEntity();
		e.setId(rs.getString("id"));
		e.setCode(rs.getString("code"));
		e.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		e.setMembers(rs.getInt("members"));
		e.setName(rs.getString("name"));
		e.setRemark(rs.getString("remark"));
		{
		String managerId=rs.getString("manager_id");
		if(managerId!=null) {
			EmployeeEntity manager=new EmployeeEntity();
			manager.setId(managerId);
			e.setManager(manager);
		}
		}
		{
			String parentId=rs.getString("parent_id");
			if(parentId!=null) {
				DepartmentEntity parent=new DepartmentEntity();
				parent.setId(parentId);
				e.setParent(parent);
			}
		}
		return e;
	}

}
