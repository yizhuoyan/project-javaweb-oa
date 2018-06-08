package com.neusoft.oa.document.function;

import java.io.File;
import java.util.List;

import com.neusoft.oa.document.ao.DocumentAttachmentAo;
import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;

public interface AttachmentFunction {
	/**
	 * 添加附件  上传附件
	 * @author wangshuteng
	 *
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	void addAttachment(DocumentAttachmentAo ao,File file)throws Exception;
	
	/**
	 * 删除附件
	 * @author wangshuteng
	 *
	 * @param AttachmentNo
	 * @throws Exception
	 */
	void deleteAttachment(String AttachmentNo)throws Exception;
	
	/**
	 * 下载附件
	 * @author wangshuteng
	 *
	 * @param AttachmentNo
	 * @throws Exception
	 */
	File downloadAttachment(String AttachmentNo)throws Exception;
	
	
	
}
