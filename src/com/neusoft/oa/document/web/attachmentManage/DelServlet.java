package com.neusoft.oa.document.web.attachmentManage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.function.AttachmentFunction;
import com.neusoft.oa.document.function.DocumentFunction;

@WebServlet("/attachmentManage/del.do")
public class DelServlet extends CommonServlet {

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
	
		// 1获取
		String id = req.getParameter("id");
		// 2调用业务方法
		try {
			AttachmentFunction fun=FunctionFactory.getFunction(AttachmentFunction.class);
			fun.deleteAttachment(id);
			
			req.setAttribute("message", "删除成功！");
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		// 3确认视图
		req.setAttribute("nextUrl", "documentManage/list.do");
		return "/jsp/message.jsp";

	}

}
