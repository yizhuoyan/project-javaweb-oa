package com.neusoft.oa.document.function.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.document.ao.DocumentAttachmentAo;
import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.function.AttachmentFunction;
import com.neusoft.oa.core.util.AssertThrowUtil;
import com.neusoft.oa.core.util.ThisSystemUtil;

public class AttachmentFunctionImpl implements AttachmentFunction {

	@Override
	public void addAttachment(DocumentAttachmentAo ao, File file) throws Exception {
		// 1验证参数
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		if (adao.exist("id", ao.getId())) {
			OAException.throwWithMessage("附件已存在", ao.getId());
		}
		if (file == null) {
			throw new OAException("附件不能为空");
		}

		// 2业务逻辑
		// 创建文档输入流
		FileInputStream in = new FileInputStream(file);
		// 将文档存储到指定路径
		saveFile(in, ao.getPath() + ao.getName());

	}

	@Override
	public void deleteAttachment(String AttachmentNo) throws Exception {
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		//参数验证
		String id=ThisSystemUtil.trim(AttachmentNo);
		AssertThrowUtil util=new AssertThrowUtil();
		util.assertNotNull("附件编号为空", id, id);
		
		if (!adao.exist("id", id)) {
			OAException.throwWithMessage("附件不存在", id);
		}
		//业务逻辑
		adao.update(AttachmentNo, "flag","0");

	}

	@Override
	public File downloadAttachment(String AttachmentNo) throws Exception {
		AttachmentDao adao = DaoFactory.getDao(AttachmentDao.class);
		//参数验证
		String id=ThisSystemUtil.trim(AttachmentNo);
		AssertThrowUtil util=new AssertThrowUtil();
		util.assertNotNull("附件编号为空", id, id);
		
		if (!adao.exist("id", id)) {
			OAException.throwWithMessage("附件不存在", id);
		}
		//业务逻辑
		DocumentAttachmentEntity am = adao.select("id", id);

		File file = new File(am.getPath());
		//返回结果
		return file;

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

}
