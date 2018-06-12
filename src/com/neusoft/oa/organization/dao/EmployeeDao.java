package com.neusoft.oa.organization.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public interface EmployeeDao extends TemplateDao<EmployeeEntity>{

	int selectsByKey(String key,int pageNo,int pageSize,List<EmployeeEntity> pageData)throws Exception;
	/**
	 * 查询具有相同邮箱名称的员工数量
	 * @param mailName 邮箱名称
	 * @param emailAdress 公司邮箱统一地址
	 * @return
	 * @throws Exception
	 */
	int selectWorkEmailLikeCount(String mailName,String emailAdress)throws Exception;
	
	
}
