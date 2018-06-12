package com.neusoft.oa.attendance.dao;

import java.util.List;

import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface ManagerAtteCountDao extends TemplateDao<AtteCountEntity> {

	 
	 
	int selectsAtteCount(String departmentId,String key,int pageNo,int pageSize,List<AtteCountEntity> pageData)throws Exception;
	String selectsDepartmentId(String managerid)throws Exception;
	String selectEmpName() throws Exception;
}