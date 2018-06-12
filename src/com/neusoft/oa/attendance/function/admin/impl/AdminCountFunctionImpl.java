/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.function.admin.impl;

import java.util.List;

import com.neusoft.oa.attendance.dao.AdminCountDao;
import com.neusoft.oa.attendance.entity.AdminCountEntity;
import com.neusoft.oa.attendance.function.admin.AdminCountFunction;
import com.neusoft.oa.core.dao.DaoFactory;

public class AdminCountFunctionImpl implements AdminCountFunction{

	@Override
	public List<AdminCountEntity> loadAllCount() throws Exception{
		// TODO Auto-generated method stub
		AdminCountDao dao = DaoFactory.getDao(AdminCountDao.class);
		List<AdminCountEntity> countList = dao.selectAll("id");
		return countList;
	}
	
}
