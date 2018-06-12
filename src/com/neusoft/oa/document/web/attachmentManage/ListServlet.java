package com.neusoft.oa.document.web.attachmentManage;

import java.util.List;

import javax.servlet.annotation.WebServlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.function.DocumentFunction;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/attachmentManage/list.do")
public class ListServlet extends CommonServlet{

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String documentId = req.getParameter("documentId");
		// 2调用业务方法
		try {
			DocumentFunction fun=FunctionFactory.getFunction(DocumentFunction.class);
			List<DocumentAttachmentEntity> result=fun.loadDocumentAttachment(documentId);
			req.setAttribute("result", result);
			req.setAttribute("documentId", documentId);
			
			// 3确定视图
		} catch (OAException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/document/documentManager/listFile.jsp";

	}
}
