package com.neusoft.oa.base.dao;

import java.util.List;

import com.neusoft.oa.base.entity.SysRoleEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface SysRoleDao extends TemplateDao<SysRoleEntity> {

	public List<SysRoleEntity> selectsByKey(String key)throws Exception;
	
	public void joinModules(String id,String... moduelIds)throws Exception;
	
	public void unjoinModules(String id,String... moduelIds)throws Exception;
	
	public List<SysRoleEntity> selectsByUser(String userId)throws Exception;
}
