package com.neusoft.oa.document.web.attachmentManage;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ArgumentObjectUtil;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.ao.DocumentAttachmentAo;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.AttachmentFunction;
import com.neusoft.oa.document.function.DocumentFunction;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.entity.MarriageState;
import com.neusoft.oa.organization.function.OrganizationFunction;

//@WebServlet("/attachmentManage/add.do")
public class AddServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String documentId = req.getParameter("documentId");
		String remark = req.getParameter("remark");
		String property = req.getParameter("property");
		try {
			// 文件域
			Part fiel1Part = req.getPart("file1");

			String file1Name = fiel1Part.getSubmittedFileName();
			long fileSize = fiel1Part.getSize();

			// 获取当前用户id
			String userId = getCurrentUserId(req);

			// 组装ao
			DocumentAttachmentAo ao = new DocumentAttachmentAo();
			ao.setCreateUserId(userId);
			ao.setRemark(remark);
			ao.setProperty(property);
			ao.setDocumentId(documentId);
			ao.setName(file1Name);
			ao.setSize(fileSize);
			// 2调用业务层方法
			AttachmentFunction fun = FunctionFactory.getFunction(AttachmentFunction.class);
			fun.addAttachment(ao, fiel1Part.getInputStream());
			// 3确认视图
			req.setAttribute("message", "添加成功！");
		} catch (OAException e) {
			e.printStackTrace();
			req.setAttribute("message", e);
		}
		req.setAttribute("nextUrl", "attachmentManage/list.do?documentId=" + documentId);
		return "/jsp/message.jsp";
	}

}
