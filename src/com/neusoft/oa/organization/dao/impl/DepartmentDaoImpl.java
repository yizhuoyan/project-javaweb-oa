package com.neusoft.oa.organization.dao.impl;

import java.sql.ResultSet;

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
	public void insert(DepartmentEntity t) throws Exception {
		
	}
	
	@Override
	protected DepartmentEntity resultset2entity(ResultSet rs) throws Exception {
		return null;
	}

	
}
