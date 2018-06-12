package com.neusoft.oa.document.web.documentManage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.DocumentFunction;

@WebServlet("/documentManage/check.do")
public class CheckServlet extends CommonServlet {

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		// 2调用业务方法
		try {
			DocumentFunction fun=FunctionFactory.getFunction(DocumentFunction.class);
			DocumentEntity e = fun.loadDocumentMessage(id);
			req.setAttribute("vo", e);
			return "/jsp/base/document/documentManager/check.jsp";
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		// 3确认视图
		return "/jsp/message.jsp";

	}

}
