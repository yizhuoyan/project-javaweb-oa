package com.neusoft.oa.system.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.system.entity.SysModuleEntity;

public interface SysModuleDao extends TemplateDao<SysModuleEntity> {

	 void unjoinRole(String id) throws Exception;
	 
	 List<SysModuleEntity> selectsByKey(String key)throws Exception;
	
	 List<SysModuleEntity> selectsByUserId(String userId)throws Exception;
	
	 List<SysModuleEntity> selectsByRoleId(String roleId)throws Exception;
}
