package com.neusoft.oa.base.function;

import java.util.List;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.entity.SysRoleEntity;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.web.servlet.administrator.sysmodule.ao.SysModuleAo;
import com.neusoft.oa.base.web.servlet.administrator.sysrole.ao.SysRoleAo;
import com.neusoft.oa.base.web.servlet.administrator.sysuser.ao.SysUserAo;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.dto.QueryResult;

public interface AdministratorFunction extends CommonFunction {
	/**
	 * 新增功能模块
	 * @param ao
	 * @throws Exception
	 */
	SysModuleEntity addSysModule(SysModuleAo ao)throws Exception;
	/**
	 * 模糊查询功能模块
	 * 如果关键字为null或者为空白字符串，则查询全部
	 * @param key 查询关键字
	 * @throws Exception
	 */
	QueryResult<SysModuleEntity> querySysModuleByKey(String key)throws Exception;
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
	SysModuleEntity  checkSysModule(String id)throws Exception;
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
	SysRoleEntity checkSysRole(String id)throws Exception;
	List<SysRoleEntity> loadAllRole()throws Exception;
	List<SysRoleEntity> loadRolesOfUser(String userId)throws Exception;
	
	
	QueryResult<SysRoleEntity> querySysRoleByKey(String key)throws Exception;
	
	SysUserEntity addSysUser(SysUserAo ao)throws Exception;
	void deleteSysUser(String id)throws Exception;
	void modifySysUser(String id,SysUserAo ao)throws Exception;
	SysUserEntity checkSysUser(String id)throws Exception;
	PaginationQueryResult<SysUserEntity> querySysUserByKey(String key,String pageNo,String pageSize)throws Exception;
}
