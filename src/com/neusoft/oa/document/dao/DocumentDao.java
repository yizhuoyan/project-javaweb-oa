package com.neusoft.oa.document.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.system.entity.SysModuleEntity;

public interface DocumentDao extends TemplateDao<DocumentEntity>{
	List<DocumentEntity> selectsByKey(String key)throws Exception;
	int selectsByKey(String deptId,String key,int pageNo,int pageSize,List<DocumentEntity> pageData)throws Exception;
}
