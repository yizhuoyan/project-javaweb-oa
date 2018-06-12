package com.neusoft.oa.document.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.entity.RecycleEntity;

public interface DocumentDao extends TemplateDao<DocumentEntity>{
	int selectsByKey(String userId,String key, int pageNo, int pageSize, List<DocumentEntity> pageData)throws Exception;
}
