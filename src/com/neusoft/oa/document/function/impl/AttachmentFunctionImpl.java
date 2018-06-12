package com.neusoft.oa.document.function.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.document.ao.DocumentAttachmentAo;
import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.AttachmentFunction;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.core.util.AssertThrowUtil;
import com.neusoft.oa.core.util.ThisSystemUtil;

public class AttachmentFunctionImpl implements AttachmentFunction {

	@Override
	public void deleteAttachment(String AttachmentNo) throws Exception {
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		// 参数验证
		String id = ThisSystemUtil.trim(AttachmentNo);
		AssertThrowUtil util = new AssertThrowUtil();
		util.assertNotNull("附件编号为空", id, id);

		if (!adao.exist("id", id)) {
			OAException.throwWithMessage("附件不存在", id);
		}
		// 业务逻辑
		adao.update(AttachmentNo, "flag", "0");

	}

	@Override
	public DocumentAttachmentEntity downloadAttachment(String AttachmentNo) throws Exception {
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		// 参数验证
		String id = ThisSystemUtil.trim(AttachmentNo);
		AssertThrowUtil.assertNotNull("附件编号为空", id, id);

		if (!adao.exist("id", id)) {
			OAException.throwWithMessage("附件不存在", id);
		}
		// 业务逻辑
		DocumentAttachmentEntity am = adao.select("id", id);
		
		
		// 返回结果
		return am;

	}

	/**
	 * 文件存储
	 * 
	 * @author wangshuteng
	 *
	 * @param in
	 * @param target
	 * @throws IOException
	 */
	public static void saveFile(InputStream in, String target) throws IOException {
		try (FileOutputStream out = new FileOutputStream(target)) {
			byte[] buffer = new byte[10240];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		}

	}

	@Override
	public DocumentAttachmentEntity addAttachment(DocumentAttachmentAo ao, InputStream in) throws Exception {
		// 参数验证
		String name = $("附件名称", ao.getName());
		String documentId = $("附件所属文档", ao.getDocumentId());
		String userId = $("附件创建者", ao.getCreateUserId());
		String property = ThisSystemUtil.trim(ao.getProperty());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		String type = name.substring(name.lastIndexOf("."), name.length());
		if (in == null) {
			OAException.throwWithMessage("附件不能为空", in);
		}
		
		// 获取附件所属文档实体
		DocumentDao ddao = DaoFactory.getDao(DocumentDao.class);
		DocumentEntity doc = ddao.select("id", documentId);
		// 获取员工实体
		EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
		EmployeeEntity user = edao.select("uid", userId);
		// 获取部门实体
		DepartmentDao deptdao = DaoFactory.getDao(DepartmentDao.class);
		DepartmentEntity dept = deptdao.select("id", user.getDepartment().getId());
		// System.out.println(dept);
		// 业务逻辑
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
	

		// 存储附件到磁盘
	
		String path = doc.getPath() + "\\" + name;

		saveFile(in, path);

		// 返回结果
		DocumentAttachmentEntity d = new DocumentAttachmentEntity();
		d.setCreateTime(Instant.now());
		d.setCreateUserId(user);
		d.setDeptId(dept);
		d.setDocumentId(doc);
		d.setFlag(1);
		d.setId(DBUtil.uuid());
		d.setName(name);
		d.setPath(path);
		d.setProperty(property);
		d.setRemark(remark);
		d.setSize(ao.getSize());
		d.setType(ao.getType());
		d.setType(type);

		adao.insert(d);

		return d;
	}

}
