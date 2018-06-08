package com.neusoft.oa.document.function.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;

import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Data;
import com.neusoft.oa.base.dao.SysUserDao;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.DocumentFunction;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;



public class DocumentFunctionImpl implements DocumentFunction {
	

	public static final String DEFULT_PATH = "D:/document";
	public static final int DOCUMENT_FLAG_DELETED = 0, DOCUMENT_FLAG_NORMAL = 1, DOCUMENT_FLAG_LOCK = 2;

	@Override
	public DocumentEntity addDocument(String userId, DocumentAo ao) throws Exception {
		// 参数验证
		String name = $("文档名称", ao.getName());
		String property = ThisSystemUtil.trim(ao.getProperty());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		String path = ThisSystemUtil.trim(ao.getPath());
		// 获取员工实体
		EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
		EmployeeEntity user = edao.select("uid", userId);
		// 获取部门实体
		DepartmentDao deptdao = DaoFactory.getDao(DepartmentDao.class);
		DepartmentEntity dept = deptdao.select("id", user.getDepartment().getId());
		// 业务逻辑
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (ddao.exist("name", name)) {
			OAException.throwWithMessage("文档已存在", name);
		}
		//在本地创建文档
		File file=new File(DEFULT_PATH+path);
		file.mkdirs();
		// 返回结果
		DocumentEntity d = new DocumentEntity();
		d.setCreateTime(Instant.now());
		d.setCreateUserId(user);
		d.setDeptId(dept);
		d.setFlag(1);
		d.setId(DBUtil.uuid());
		d.setName(name);
		d.setPath(DEFULT_PATH);
		d.setProperty(property);
		d.setRemark(remark);

		ddao.insert(d);

		return d;
	}

	@Override
	public void modifyDocument(String documentNo, DocumentAo ao) throws Exception {
		// 参数验证
		String name = $("文档名称", ao.getName());
		String property = ThisSystemUtil.trim(ao.getProperty());
		String id=ThisSystemUtil.trim(documentNo);// 文档编号
		String path=ThisSystemUtil.trim(ao.getPath());// 文档位置
		EmployeeEntity createUser=ao.getCreateUserId();// 文档创建者
		Instant createTime=ao.getCreateTime();// 文档创建时间
		String remark=ThisSystemUtil.trim(ao.getRemark());// 备注
		int flag=ao.getFlag();// 状态标志 0在回收站1正常2被占用
		DepartmentEntity dept=ao.getDeptId();// 所属部门id

		// 业务逻辑
		
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (!ddao.exist("id", id)) {
			OAException.throwWithMessage("文档不存在", id);
		}
		if (ddao.exist("name", name)) {
			OAException.throwWithMessage("文档名已存在", name);
		}
		int nowflag=queryDocumentFlag(documentNo);
		if (nowflag!=DOCUMENT_FLAG_NORMAL) {
			OAException.throwWithMessage("当前文档不可修改", flag);
		}
		
		Map<String, Object> needUpdate = new HashMap<>();
		needUpdate.put("name", name);
		needUpdate.put("property", property);
		needUpdate.put("id", id);
		needUpdate.put("path", path);
		needUpdate.put("createUser_id", createUser.getId());
		needUpdate.put("createTime", createTime);
		needUpdate.put("remark", remark);
		needUpdate.put("flag", flag);
		needUpdate.put("dept_id", dept.getId());
		
		ddao.update(id, needUpdate);
	}

	@Override
	public void deleteDocument(String documentNo) throws Exception {
		// 参数验证
		int flag=queryDocumentFlag(documentNo);
		// 业务逻辑
		if (flag!=DOCUMENT_FLAG_DELETED) {
			DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
			ddao.update(documentNo, "flag",DOCUMENT_FLAG_DELETED );
			
		}

	}

	@Override
	public DocumentEntity loadDocumentMessage(String documentNo) throws Exception {
		// 参数验证
		// 业务逻辑
		int flag=queryDocumentFlag(documentNo);
		if (flag!=DOCUMENT_FLAG_DELETED) {
			OAException.throwWithMessage("当前文档不可查看", flag);
		}
		
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		// 返回结果
		return ddao.select("id", documentNo);
	}

	@Override
	public List<DocumentAttachmentEntity> loadDocumentAttachment(String documentNo) throws Exception {
		// 参数验证
		// 业务逻辑
		int flag=queryDocumentFlag(documentNo);
		if (flag!=DOCUMENT_FLAG_DELETED) {
			OAException.throwWithMessage("当前文档不可查看", flag);
		}
		
		AttachmentDao adao=DaoFactory.getDao(AttachmentDao.class);
		List<DocumentAttachmentEntity> list =adao.selectAllByColumn("document_id", documentNo, documentNo);
		// 返回结果;
		return list;
	}

	@Override
	public List<DocumentEntity> queryDocument(String key) throws Exception {
		// 参数验证
		String keystring = ThisSystemUtil.trim(key);
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (keystring==null) {
			return ddao.selectAll("name");
		}else {
			return ddao.selectsByKey(keystring);
		}
		
		// 业务逻辑
		// 返回结果
	}

	@Override
	public int queryDocumentFlag(String documentNo) throws Exception {
		// 参数验证
		String id = $("文档编号", documentNo);
		// 业务逻辑
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (!ddao.exist("id", id)) {
			OAException.throwWithMessage("文档不存在", id);
		}
		DocumentEntity de = ddao.select("id", id);
		// 返回结果
		return de.getFlag();
	}

}
