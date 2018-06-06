package com.neusoft.oa.base.dao;

import java.util.List;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface SysModuleDao extends TemplateDao<SysModuleEntity> {

	 void unjoinRole(String id) throws Exception;
	 
	 List<SysModuleEntity> selectsByKey(String key)throws Exception;
	
	 List<SysModuleEntity> selectsByUserId(String userId)throws Exception;
	
	 List<SysModuleEntity> selectsByRoleId(String roleId)throws Exception;
}
