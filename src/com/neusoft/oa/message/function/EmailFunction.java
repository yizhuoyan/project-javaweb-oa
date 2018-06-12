package com.neusoft.oa.message.function;

import java.util.List;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.message.ao.EmailAo;
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
	 * 删除邮件到回收站
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	int deleteEmail(String emailId)throws Exception;
	/**
	 * 删除邮件到回收站
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	int deleteEmail(String[] arry)throws Exception;
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
	int selectRecyeBinServletByUserEmailTotal(String userId,String key, int pageNo, int pageSize, List<EmailEntity> pageData)throws Exception;
	/**
	 * 管理员查看邮件记录
	 * @return
	 * @throws Exception
	 */
	EmailEntity completeQurey()throws Exception;
	int recoverEmail(String emailId)throws Exception; 
	int completelyDelete(String emailId)throws Exception;
	PaginationQueryResult<EmailEntity> querySentAndReceivedEmailByKey(String user, String key,String pageNo,String pageSize)throws Exception;
	PaginationQueryResult<EmailEntity> querySentEmailByKey(String sender, String key,String pageNo,String pageSize)throws Exception;
	PaginationQueryResult<EmailEntity> queryReceivedEmailByKey(String recipient, String key,String pageNo,String pageSize)throws Exception;
	PaginationQueryResult<EmailEntity> queryAllEmailByKey(String key,String pageNo,String pageSize)throws Exception; 
	int queryDraftBins(String recipient,String key, int pageNo, int pageSize, List<EmailEntity> pageData) throws Exception;
	EmailEntity  loadUnreadEmail(String emailId)throws Exception;  
	EmailEntity  loadSendEmailById(String emailId)throws Exception;
	int sendEmail(String emailId)throws Exception; 
	String loadFilePath(String emailId) throws Exception;

}
