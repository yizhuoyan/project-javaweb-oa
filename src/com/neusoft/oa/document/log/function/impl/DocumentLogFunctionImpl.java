package com.neusoft.oa.document.log.function.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.document.log.dao.AttachmentDao;
import com.neusoft.oa.document.log.dao.DocumentLogDao;
import com.neusoft.oa.document.log.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.log.entity.DocumentLogEntity;
import com.neusoft.oa.document.log.function.DocumentLogAO;
import com.neusoft.oa.document.log.function.DocumentLogFunction;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;

import static com.neusoft.oa.core.util.ThisSystemUtil.*;


public class DocumentLogFunctionImpl implements DocumentLogFunction {

	public void addNewLog(DocumentLogEntity de) throws Exception {

		String title = de.getOperationTime() + "," + de.getOperator().getName() + "将" + de.getTarget();

		String putInRecycle = title + "放入回收站";
		String create = title + "创建";
		String download = title + "下载";
		String reduction = title + "从回收站还原";
		String delete = title + "从回收站彻底删除";
		
		String content = " ";
		
		switch(de.getOperation()) {
		case 1:
			 content = putInRecycle;
			 break; 
		case 2:
			 content = create;
			 break; 
		case 3:
			 content = download;
			 break; 
		case 4:
			 content = reduction;
			 break; 
		case 5:
			 content = delete;
			 break; 
		}
		
		DocumentLogDao dao = DaoFactory.getDao(DocumentLogDao.class);

		de.setId(DBUtil.uuid());
		de.setContent(content);

		dao.insert(de);
	}

	@Override
	public PaginationQueryResult<DocumentLogEntity> queryLog(DocumentLogAO ao,int pageNo,int pageSize) throws Exception {
        ao.setBeginoperationTime(trim(ao.getBeginoperationTime()));
        ao.setEndoperationTime(trim(ao.getEndoperationTime()));
        ao.setOperation(trim(ao.getOperation()));
        ao.setOperatorId(trim(ao.getOperatorId()));
        ao.setTarget(trim(ao.getTarget()));
        
		DocumentLogDao dao = DaoFactory.getDao(DocumentLogDao.class);
		
		List<DocumentLogEntity> pageData = new ArrayList<>(pageSize);
		int total = dao.selectsByDocumentlog(ao, pageNo, pageSize, pageData);
		EmployeeDao epdao = DaoFactory.getDao(EmployeeDao.class);
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		for(DocumentLogEntity log:pageData) {
			EmployeeEntity employee = epdao.select("id",log.getOperator().getId());
			log.setOperator(employee);
			DocumentAttachmentEntity target = adao.select("id",log.getTarget().getId());
			log.setTarget(target);
		}
		
		
		PaginationQueryResult<DocumentLogEntity> re = new PaginationQueryResult<>();
				
		re.setPageNo(pageNo);
		re.setPageSize(pageSize);
		re.setRows(pageData);
		re.setTotalRows(total);
		
		return re;

	}


	
		
	}


