package com.neusoft.oa.document.function;

import java.util.List;

import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;

public interface DocumentFunction {
	
	/**
	 * 新建文档
	 * @author wangshuteng
	 *
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	DocumentEntity addDocument(String userId,DocumentAo ao)throws Exception;
	/**
	 * 修改文档
	 * @author wangshuteng
	 *
	 * @param documentNo
	 * @param ao
	 * @throws Exception
	 */
	void modifyDocument(String documentNo,DocumentAo ao)throws Exception;
	/**
	 * 删除文档
	 * @author wangshuteng
	 *
	 * @param documentNo
	 * @throws Exception
	 */
	void deleteDocument(String documentNo)throws Exception;
	
	/**
	 * 查看文档属性信息
	 * @author wangshuteng
	 *
	 * @param documentNo
	 * @return
	 * @throws Exception
	 */
	DocumentEntity  loadDocumentMessage(String documentNo) throws Exception;
	
	/**
	 * 查看文档包含的所有附件
	 * @author wangshuteng
	 *
	 * @param documentNO
	 * @return
	 * @throws Exception
	 */
	List<DocumentAttachmentEntity> loadDocumentAttachment(String documentNo) throws Exception;
	
	/**
	 * 模糊查询文档  如果key为null则查询全部
	 * @author wangshuteng
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	List<DocumentEntity> queryDocument(String key) throws Exception;
	
	/**
	 * 查询当前文档状态  
	 * 0  回收站
	 * 1 正常
	 * 2 被占用
	 * 
	 * @author wangshuteng
	 *
	 * @param documentNo
	 * @return
	 * @throws Exception
	 */
	int queryDocumentFlag(String documentNo) throws Exception;
	
	
	
	
	
}





