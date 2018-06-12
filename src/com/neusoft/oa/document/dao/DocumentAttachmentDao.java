package com.neusoft.oa.document.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.RecycleEntity;

public interface DocumentAttachmentDao extends TemplateDao<DocumentAttachmentEntity> {
	int selectsByKey(String userId,String key, int pageNo, int pageSize, List<DocumentAttachmentEntity> pageData)throws Exception;
}
