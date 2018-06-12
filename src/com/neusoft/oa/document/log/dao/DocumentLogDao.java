package com.neusoft.oa.document.log.dao;


import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.document.log.entity.DocumentLogEntity;
import com.neusoft.oa.document.log.function.DocumentLogAO;

public interface DocumentLogDao extends TemplateDao<DocumentLogEntity>{
	 void insert(DocumentLogEntity t) throws Exception ;

	int selectsByDocumentlog(DocumentLogAO key, int pageNo, int pageSize, List<DocumentLogEntity> pageData)
			throws Exception; 
	
}
