package com.neusoft.oa.organization.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public interface EmployeeDao extends TemplateDao<EmployeeEntity>{

	int selectsByKey(String key,int pageNo,int pageSize,List<EmployeeEntity> pageData)throws Exception;
	int selectWorkEmailLikeCount(String workEmail)throws Exception;
	
	
}
