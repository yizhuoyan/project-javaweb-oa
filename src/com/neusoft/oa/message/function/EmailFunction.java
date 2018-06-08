package com.neusoft.oa.message.function;

import java.util.List;

import com.neusoft.oa.message.ao.EmailAo;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.entity.EmailEntity;

public interface EmailFunction {
	/**
	 * 新建邮件，  不发送保存到草稿箱
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	EmailEntity addEmail(EmailAo email)throws Exception;
	
	/**
	 * 查看邮件详情
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	EmailEntity loadMeassge(String id)throws Exception;
	
	/**
	 * 删除邮件到回收站
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	EmailEntity deleteMeassge(String id)throws Exception;
	/**
	 * 从回收站彻底删除邮件
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	EmailEntity completeDelet(String id)throws Exception;
	
	/**
	 * 管理员群发邮件
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	List<EmailEntity> adminMassMessage(List<EmailEntity> email)throws Exception;
	
	/**
	 * 部门经理群发邮件
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	List<EmailEntity> managerMassMessage(List<EmailEntity> email)throws Exception;
	
	/**
	 * 管理员查看邮件记录
	 * @return
	 * @throws Exception
	 */
	EmailEntity completeQurey()throws Exception;
	List<AddressBookEntity> SearchEmailAddressBookByKey(String key)throws Exception; 
}
