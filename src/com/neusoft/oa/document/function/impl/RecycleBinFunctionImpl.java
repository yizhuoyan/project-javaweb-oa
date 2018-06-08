package com.neusoft.oa.document.function.impl;

import com.neusoft.oa.core.dto.PaginationQueryResult;

import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.dao.RecycleDao;
import com.neusoft.oa.document.dao.impl.AttachmentDaoImpl;
import com.neusoft.oa.document.dao.impl.DocumentDaoImpl;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.entity.RecycleEntity;
import com.neusoft.oa.document.function.RecycleBinFunction;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.sun.xml.internal.messaging.saaj.soap.AttachmentPartImpl;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import static com.neusoft.oa.core.util.AssertThrowUtil.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhoujingmeng
 *
 */
public class RecycleBinFunctionImpl implements RecycleBinFunction {
	
	/**
	 * 回收还原文档
	 */
	@Override
	public void restoreDocument(String id) throws Exception {
		//1验证参数
		id = $("文档编号",id);
		//2执行业务逻辑
		DocumentDao dDao=DaoFactory.getDao(DocumentDao.class);
		DocumentEntity de=dDao.select("id", id);
		if(de==null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		dDao.update(id, "flag", 1);
	}
	/**
	 * 回收还原附件
	 */
	@Override
	public void restoreAttachment(String id) throws Exception {
		//1验证参数
		id=$("id",id);
		
		AttachmentDao aDao=DaoFactory.getDao(AttachmentDao.class);
		DocumentAttachmentEntity ae=aDao.select("id", id);
		/*if(ae==null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}*/
		//2执行业务逻辑
		aDao.update(id, "flag", "1");	
	}
	/**
	 * 彻底删除文档
	 */
	@Override
	public void deleteDocument(String id) throws Exception {
		//1验证参数
		id=$("文档编号",id);
		
		//2执行业务逻辑
		DocumentDao dDao=DaoFactory.getDao(DocumentDao.class);
		DocumentEntity de=dDao.select("id", id);
		
//		if(de==null) {
//			OAException.throwWithMessage("数据【?】不存在或已删除", id);
//		}
		//删除指定id的文档
		dDao.delete("id", id);
		//删除指定路径文档及所有附件
		File file= new File(""+ de.getPath() +"");
		
		deleat(file);
	}
	/**
	 * 彻底删除附件
	 */
	@Override
	public void deleteAttachment(String id) throws Exception {
		//1验证参数
		id=$("附件编号",id);
		
		//2执行业务逻辑
		AttachmentDao aDao=DaoFactory.getDao(AttachmentDao.class);
		DocumentAttachmentEntity ae=aDao.select("id", id);
		System.out.println(id);
//		if(ae==null) {
//			OAException.throwWithMessage("数据【?】不存在或已删除", id);
//		}
		//删除指定id的附件
		aDao.delete("id", id);
		//删除指定路径附件
		File file= new File(""+ ae.getPath() +"");
		
		deleat(file);
		
	}
 /**
  * 分页查询
  * @param key
  * @param pageNo
  * @param pageSize
  * @return
  * @throws Exception
  */
	
	
	//从删除磁盘文档或附件
	 private static void deleat(File file) {
	        //检查附件是否存在，如果不存在直接返回，不进行下面的操作
	        if(!file.exists()){
	            return;
	        }
	        //如果是附件删除，就删除附件，然后返回，不进行下面的操作
	        if(file.isFile()){
	            file.delete();
	            return;
	        }
	        //是文档
	        if(file.isDirectory()){
	            //循环所有文档里面的内容并删除
	            File[] files=file.listFiles();
	            if (files!=null) {
	                for (File f : files) {
	                    deleat(f);
	                } 
	            }
	            //删除自己
	            file.delete();
	        }
	    }
	@Override
	public PaginationQueryResult<DocumentAttachmentEntity> queryRecycleAttachment(String key, int pageNo, int pageSize)
			throws Exception {
		// 1清理验证参数
				key = ThisSystemUtil.trim(key);
				if (pageNo <= 0) {
					pageNo = 1;
				}
				if (pageSize <= 0) {
					pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
				}

				List<DocumentAttachmentEntity> pageData = new ArrayList<>(pageSize);
				// 2执行业务逻辑
				AttachmentDao udao=DaoFactory.getDao(AttachmentDao.class);
				int total = udao.selectsByKey(key, pageNo, pageSize, pageData);
				// 3组装业务结果
				PaginationQueryResult<DocumentAttachmentEntity> result = new PaginationQueryResult<>();
				result.setTotalRows(total);
				result.setRows(pageData);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				return result;
	}
	@Override
	public PaginationQueryResult<DocumentEntity> queryRecycleDocument(String key, int pageNo, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	
	

}
