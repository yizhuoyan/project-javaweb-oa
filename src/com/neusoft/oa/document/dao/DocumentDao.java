package com.neusoft.oa.document.dao;

import java.util.List;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.document.entity.DocumentEntity;

public interface DocumentDao extends TemplateDao<DocumentEntity>{
	List<DocumentEntity> selectsByKey(String key)throws Exception;
	int selectsByKey(String deptId,String key,int pageNo,int pageSize,List<DocumentEntity> pageData)throws Exception;
}
