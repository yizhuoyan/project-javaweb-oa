package com.neusoft.oa.document.recycle.function.impl;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.document.recycle.dao.DocumentAttachmentDao;
import com.neusoft.oa.document.recycle.dao.DocumentDao;
import com.neusoft.oa.document.recycle.dao.RecycleDao;
import com.neusoft.oa.document.recycle.dao.impl.DocumentAttachmentDaoImpl;
import com.neusoft.oa.document.recycle.dao.impl.DocumentDaoImpl;
import com.neusoft.oa.document.recycle.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.recycle.entity.DocumentEntity;
import com.neusoft.oa.document.recycle.entity.RecycleEntity;
import com.neusoft.oa.document.recycle.function.RecycleBinFunction;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.sun.xml.internal.messaging.saaj.soap.AttachmentPartImpl;
import com.sun.xml.internal.txw2.Document;
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
		
		DocumentAttachmentDao aDao=DaoFactory.getDao(DocumentAttachmentDao.class);
		DocumentAttachmentEntity ae=aDao.select("id", id);
		/*if(ae==null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}*/
		//2执行业务逻辑
		aDao.update(id, "flag", 1);
		
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
		File file= new File(""+ de.getPath() +""+ "");
		
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
		DocumentAttachmentDao aDao=DaoFactory.getDao(DocumentAttachmentDao.class);
		DocumentAttachmentEntity ae=aDao.select("id", id);
		System.out.println(id);
//		if(ae==null) {
//			OAException.throwWithMessage("数据【?】不存在或已删除", id);
//		}
		//删除指定id的附件
		aDao.delete("id", id);
		//删除指定路径附件
		System.out.println(ae.getPath().toString());
		File file= new File(""+ ae.getPath() + ae.getName()+"");
		
		deleat(file);
		
	}
	
	
	//从删除磁盘附件
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
	 public void emptyRecycle() throws Exception {
		//1.1验证回收站是否有文档
		 DocumentDao documentDao =DaoFactory.getDao(DocumentDao.class);
		 DocumentEntity de=documentDao.select("flag", DocumentEntity.FLAG_DELETED);
		 if(de==null) {
				OAException.throwWithMessage("回收站无此附件", DocumentAttachmentEntity.FLAG_DELETED);
		 }
		 //1.2验证回收站是否有附件
		 DocumentAttachmentDao attachmentDao=DaoFactory.getDao(DocumentAttachmentDao.class);
		 DocumentAttachmentEntity ae=attachmentDao.select("flag",DocumentAttachmentEntity.FLAG_DELETED);
		 if(ae==null) {
				OAException.throwWithMessage("回收站无此文档", DocumentAttachmentEntity.FLAG_DELETED);
		 }
		
		 //2.1执行Dao方法清空回收站所有文档
		
		 documentDao.delete("flag", DocumentEntity.FLAG_DELETED);
		//2.2从数据库清空回收站所有数据
		 attachmentDao.delete("flag", DocumentAttachmentEntity.FLAG_DELETED);
		 //3.3//从磁盘清空回收站
		 File file =new File("E:\\全部文件 - 副本 (3)");
		 emptyAll(file);
		 
	 }
	 
		//删除文档管理下回收站所有文档和附件
	 private static void emptyAll(File file) {
	        //检查附件是否存在，如果不存在直接返回，不进行下面的操作
		 int i=0;
		 int j=0;
	        if(!file.exists()){
	        	System.out.println("未找到该路径文档");
	            return;
	        }
	        //如果是附件删除，就删除附件，然后返回，不进行下面的操作
	        
	        if(file.isFile()){
	        	i++;
	            file.delete();
	            System.out.println("共删除附件数:" +i);
	            return;
	        }
	        //是文档
	        if(file.isDirectory()){
	        	
	        	
	            //循环所有文档里面的内容并删除
	            File[] files=file.listFiles();
	            if (files!=null) {
	            	i++;
	                for (File f : files) {
	                	
	                    deleat(f);
	                    f.delete();
	                } 
	                
	            }
	          
	        }
	    }
	 
	 /**
	  * 分页查询
	  * @param key
	  * @param pageNo
	  * @param pageSize
	  * @return
	  * @throws Exception
	  */
	 
	@Override
	public PaginationQueryResult<DocumentAttachmentEntity> queryRecycleAttachment(String userId, String key, int pageNo, int pageSize)
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
				DocumentAttachmentDao udao=DaoFactory.getDao(DocumentAttachmentDao.class);
				System.out.println("业务层:"+userId);
				int total = udao.selectsByKey(userId,key, pageNo, pageSize, pageData);
				// 3组装业务结果
				PaginationQueryResult<DocumentAttachmentEntity> result = new PaginationQueryResult<>();
				result.setTotalRows(total);
				result.setRows(pageData);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				return result;
	}
	@Override
	public PaginationQueryResult<DocumentEntity> queryRecycleDocument(String userId, String key, int pageNo, int pageSize)
			throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
		}

		List<DocumentEntity> pageData = new ArrayList<>(pageSize);
		// 2执行业务逻辑
		DocumentDao udao=DaoFactory.getDao(DocumentDao.class);
		int total = udao.selectsByKey(userId,key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<DocumentEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
	}
	
	 
	
	

}
