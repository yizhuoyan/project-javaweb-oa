package com.neusoft.oa.document.recycle.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.document.recycle.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.recycle.entity.RecycleEntity;

public interface DocumentAttachmentDao extends TemplateDao<DocumentAttachmentEntity> {
	int selectsByKey(String userId,String key, int pageNo, int pageSize, List<DocumentAttachmentEntity> pageData)throws Exception;
}
