package com.neusoft.oa.document.function.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Data;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
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
import com.neusoft.oa.system.dao.SysUserDao;
import com.neusoft.oa.system.entity.SysUserEntity;

public class DocumentFunctionImpl implements DocumentFunction {

	public static final String DEFULT_PATH = "D:\\document";
	public static final int DOCUMENT_FLAG_DELETED = 0, DOCUMENT_FLAG_NORMAL = 1, DOCUMENT_FLAG_LOCK = 2;

	@Override
	public DocumentEntity addDocument(String userId, DocumentAo ao) throws Exception {
		// 参数验证
		String name = $("文档名称", ao.getName());
		String property = ThisSystemUtil.trim(ao.getProperty());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		String path = ThisSystemUtil.trim(ao.getPath());
		if (userId == null) {
			OAException.throwWithMessage("用户id获取失败", userId);
		}
		// 获取员工实体
		EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
		EmployeeEntity user = edao.select("uid", userId);
		// 获取部门实体
		DepartmentDao deptdao = DaoFactory.getDao(DepartmentDao.class);
		DepartmentEntity dept = deptdao.select("id", user.getDepartment().getId());
		// System.out.println(dept);
		// 业务逻辑
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (ddao.exist("name", name)) {
			OAException.throwWithMessage("文档已存在", name);
		}
		// 判断默认路径是否存在,如果不存在则创建文件夹
		File defaultFile = new File(DEFULT_PATH);
		if (!defaultFile.exists()) {
			defaultFile.mkdir();
		}
		// 返回结果
		DocumentEntity d = new DocumentEntity();
		d.setCreateTime(Instant.now());
		d.setCreateUserId(user);
		d.setDeptId(dept);
		d.setFlag(1);
		d.setId(DBUtil.uuid());
		d.setName(name);
		if (path == null) {
			d.setPath(DEFULT_PATH);
		} else {
			d.setPath(DEFULT_PATH + "\\" + path);
		}
		d.setProperty(property);
		d.setRemark(remark);

		ddao.insert(d);
		// 创建文档到磁盘
		if (path == null) {
			File file = new File(DEFULT_PATH + "\\" + name);
			file.mkdir();
		} else {
			File file = new File(DEFULT_PATH + "\\" + path + "\\" + name);
			file.mkdirs();
		}

		return d;
	}

	@Override
	public void modifyDocument(String documentNo, DocumentAo ao) throws Exception {
		// 参数验证
		String name = $("文档名称", ao.getName());
		String property = ThisSystemUtil.trim(ao.getProperty());
		String id = ThisSystemUtil.trim(documentNo);// 文档编号
		String remark = ThisSystemUtil.trim(ao.getRemark());// 备注

		// 业务逻辑

		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (!ddao.exist("id", id)) {
			OAException.throwWithMessage("文档不存在", id);
		}

		int nowflag = queryDocumentFlag(documentNo);
		if (nowflag != DOCUMENT_FLAG_NORMAL) {
			OAException.throwWithMessage("当前文档不可修改", nowflag);
		}

		Map<String, Object> needUpdate = new HashMap<>();
		needUpdate.put("name", name);
		needUpdate.put("property", property);
		needUpdate.put("id", id);
		needUpdate.put("remark", remark);

		ddao.update(id, needUpdate);
	}

	@Override
	public void deleteDocument(String documentNo) throws Exception {
		// 参数验证
		int nowflag = queryDocumentFlag(documentNo);
		if (nowflag != DOCUMENT_FLAG_NORMAL) {
			OAException.throwWithMessage("当前文档不可删除", nowflag);
		}
		// 业务逻辑
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		ddao.update(documentNo, "flag", DOCUMENT_FLAG_DELETED);

	}

	@Override
	public DocumentEntity loadDocumentMessage(String documentNo) throws Exception {
		// 参数验证
		// 业务逻辑
		int flag = queryDocumentFlag(documentNo);
		if (flag != DOCUMENT_FLAG_NORMAL) {
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
		int flag = queryDocumentFlag(documentNo);

		if (flag != DOCUMENT_FLAG_NORMAL) {
			OAException.throwWithMessage("当前文档不可查看" + flag, flag);
		}

		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		List<DocumentAttachmentEntity> list = adao.selectAllByColumn("document_id", documentNo, "document_id");
		// 返回结果;
		return list;
	}

	@Override
	public List<DocumentEntity> queryDocument(String key) throws Exception {
		// 参数验证
		String keystring = ThisSystemUtil.trim(key);
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		if (keystring == null) {
			return ddao.selectAll("name");
		} else {
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

	@Override
	public PaginationQueryResult<DocumentEntity> listDocument(String deptId, String key, int pageNo, int pageSize)
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
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		int total = ddao.selectsByKey(deptId, key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<DocumentEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
	}

}
