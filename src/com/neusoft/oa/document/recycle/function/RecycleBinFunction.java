package com.neusoft.oa.document.recycle.function;

import java.util.List;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.document.recycle.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.recycle.entity.DocumentEntity;
import com.neusoft.oa.document.recycle.entity.RecycleEntity;

/**
 * 业务层
 * @author zhoujingmeng
 *
 */
public interface RecycleBinFunction {
	
	/**
	 * 还原文档
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	void restoreDocument(String id) throws Exception;
	
	/**
	 * 还原附件
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	void restoreAttachment(String id) throws Exception;
	
	/**
	 * 删除文档
	 * @param 
	 * @throws Exception
	 */
	 void deleteDocument(String id) throws Exception;
	
	/**
	 * 删除附件
	 * @param no
	 * @throws Exception
	 */
	void deleteAttachment(String id) throws Exception;
	/**
	 * 清空回收站数据
	 * @throws Exception
	 */
	void emptyRecycle()throws Exception;
	
	/**
	 * 查询回收站数据是否超时
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	
	/**
	 * 分页模块
	 * @param key
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	
	PaginationQueryResult<DocumentAttachmentEntity> queryRecycleAttachment(String userId, String key,int pageNo,int pageSize)throws Exception;
	PaginationQueryResult<DocumentEntity> queryRecycleDocument(String userId, String key,int pageNo,int pageSize)throws Exception;
}
