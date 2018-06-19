package com.neusoft.oa.system.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.system.entity.SysUserEntity;

public interface SysUserDao extends TemplateDao<SysUserEntity> {
	/**
	 * 模糊分页查询用户
	 * @param key 查询关键字，如果为空，则查询全部
	 * @param pageNo 页码
	 * @param pageSize 每页大小
	 * @param pageData 每页数据（不可为空）
	 * @return 满足查询条件的总记录数
	 * @throws Exception
	 */
	public int selectsByKey(String key,int pageNo,int pageSize,List<SysUserEntity> pageData)throws Exception;
	
	public void joinRoles(String id,String... roleIds)throws Exception;
	
	public void unjoinRoles(String id,String... roleIds)throws Exception;
	
	public int countUser()throws Exception;
}