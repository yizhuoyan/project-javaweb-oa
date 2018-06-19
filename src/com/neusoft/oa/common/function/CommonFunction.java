package com.neusoft.oa.common.function;

import java.util.List;

import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.system.entity.SysModuleEntity;

public interface CommonFunction {
	/**
	 * 用户登陆
	 * @param account
	 * @param password
	 * @param loginIp
	 * @return
	 * @throws Exception
	 */
	UserContext login(String account,String password,String loginIp)throws Exception;
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param newPasswordConfig
	 * @throws Exception
	 */
	void modifyPassword(String userId,String oldPassword,String newPassword,String newPasswordConfig)throws Exception;
	
	/**
	 * 
	 * @param userId 用户id
	 * @throws Exception
	 */
	List<SysModuleEntity> loadUserModule(String userId)throws Exception;
	/**
	 * 获取系统用户账号总数
	 * @return
	 * @throws Exception
	 */
	int loadTotalAccount()throws Exception;
	
	void modifyUserName(String userId,String name)throws Exception;
	void modifySecurityEmail(String userId,String email)throws Exception;
	void modifyUserAvatar(String userId,String newAvatar)throws Exception;
	String sendModifySecurityEmailValidteCodeEmail(String userId,String email)throws Exception;
	
	
}
