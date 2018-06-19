/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.attendance.dao.AdminCountDao;
import com.neusoft.oa.attendance.entity.AdminCountEntity;
import com.neusoft.oa.core.dao.TemplateDaoImpl;

public class AdminCountDaoImpl extends TemplateDaoImpl<AdminCountEntity> implements AdminCountDao{

	

	protected AdminCountDaoImpl() {
		super("atte_count");
	}

	@Override
	public void insert(AdminCountEntity t) throws Exception {
		
	}

	@Override
	protected AdminCountEntity resultset2entity(ResultSet rs) throws Exception {
		return null;
	}

	




}
