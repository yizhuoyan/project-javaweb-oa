package com.neusoft.oa.document.log.function;

import java.util.List;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.document.log.entity.DocumentLogEntity;

public interface DocumentLogFunction{

	void addNewLog(DocumentLogEntity de) throws Exception;

	PaginationQueryResult<DocumentLogEntity> queryLog(DocumentLogAO de,int pageSize,int pageNo) throws Exception;

}
