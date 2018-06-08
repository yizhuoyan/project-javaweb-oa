package com.neusoft.oa.attendance.dao;

import java.util.List;

import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.attendance.entity.AtteVacateEntity;
import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface ManagerAtteCountDao extends TemplateDao<AtteCountEntity> {

	 
	 
	 int selectsAtteCount(String ManagerId,int mouth,String key,int pageNo,int pageSize,List<AtteCountEntity> pageData)throws Exception;
	 
	
}