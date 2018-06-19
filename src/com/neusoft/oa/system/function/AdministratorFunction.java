package com.neusoft.oa.system.function;

import java.util.List;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.dto.QueryResult;
import com.neusoft.oa.system.ao.SysModuleAo;
import com.neusoft.oa.system.ao.SysRoleAo;
import com.neusoft.oa.system.ao.SysUserAo;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.entity.SysUserEntity;

public interface AdministratorFunction  {
	void lockUser(String id)throws Exception;
	void unlockUser(String id)throws Exception;
	/**
	 * 新增功能模块
	 * @param ao
	 * @throws Exception
	 */
	SysModuleEntity addSysModule(SysModuleAo ao)throws Exception;
	/**
	 * 查询全部功能模块
	 * @return
	 * @throws Exception
	 */
	List<SysModuleEntity> loadAllModules()throws Exception;
	
	List<SysModuleEntity> loadRootModules()throws Exception;
	/**
	 * 修改功能模块
	 * @param id 功能模块id
	 * @param ao 修改参数
	 * @throws Exception
	 */
	void modifySysModule(String id,SysModuleAo ao)throws Exception;
	/**
	 * 查看功能模块
	 * @param id 功能模块id
	 * @return
	 * @throws Exception
	 */
	SysModuleEntity  loadSysModule(String id)throws Exception;
	/**
	 * 删除功能模块
	 * @param id 功能模块id
	 * @throws Exception
	 */
	void deleteSysmodule(String id)throws Exception;
	/**
	 * 加载角色下功能模块
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<SysModuleEntity> loadModulesOfRole(String roleId)throws Exception;
	/**
	 * 给角色分配功能
	 * 如果功能id数组为空，则收回分配的所有的所有功能
	 * @param roleId 角色id
	 * @param modules 功能id数组
	 * @throws Exception
	 */
	void grantModules2Role(String roleId,String... modules)throws Exception;
	
	void grantRoles2User(String userId,String... roles)throws Exception;
	
	SysRoleEntity addSysRole(SysRoleAo ao)throws Exception;
	void deleteSysRole(String id)throws Exception;
	void modifySysRole(String id,SysRoleAo ao)throws Exception;
	SysRoleEntity loadSysRole(String id)throws Exception;
	List<SysRoleEntity> loadAllRole()throws Exception;
	List<SysRoleEntity> loadRolesOfUser(String userId)throws Exception;
	
	
	QueryResult<SysRoleEntity> querySysRoleByKey(String key)throws Exception;
	
	SysUserEntity addSysUser(SysUserAo ao)throws Exception;
	void deleteSysUser(String id)throws Exception;
	void modifySysUser(String id,SysUserAo ao)throws Exception;
	SysUserEntity loadSysUser(String id)throws Exception;
	PaginationQueryResult<SysUserEntity> querySysUserByKey(String key,String pageNo,String pageSize)throws Exception;
}
