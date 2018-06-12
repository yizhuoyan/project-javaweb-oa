package com.neusoft.oa.message.function;

import java.util.List;

import com.neusoft.oa.message.entity.MessageEntity;

public interface MessageFunction {
	/**
	 * 新建消息
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	MessageEntity addMessage(MessageEntity msg) throws Exception;

	/**
	 * 发送消息
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	MessageEntity sendMessage(MessageEntity msg) throws Exception;

	/**
	 * 查看消息详情
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	MessageEntity checkMeassge(String id) throws Exception;

	/**
	 * 删除信息到回收站
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	MessageEntity deleteMeassge(String id) throws Exception;

	/**
	 * 从回收站彻底删除信息
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	MessageEntity completeDelet(String id) throws Exception;

	/**
	 * 群发消息
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	List<MessageEntity> massMessage(List<MessageEntity> msg) throws Exception;

	/**
	 * 管理员查看消息记录
	 * 
	 * @return
	 * @throws Exception
	 */
	MessageEntity completeCheck() throws Exception;
}


