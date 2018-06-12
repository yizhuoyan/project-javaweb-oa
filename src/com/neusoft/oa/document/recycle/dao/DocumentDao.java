package com.neusoft.oa.document.recycle.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.document.recycle.entity.DocumentEntity;
import com.neusoft.oa.document.recycle.entity.RecycleEntity;

public interface DocumentDao extends TemplateDao<DocumentEntity>{
	int selectsByKey(String userId,String key, int pageNo, int pageSize, List<DocumentEntity> pageData)throws Exception;
}
