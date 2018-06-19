package com.neusoft.oa.message.web.email;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;
@WebServlet("/selectSendEmail.do")
public class SelectSendEmailServlet extends CommonServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939844972972204890L;
@Override
protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
	 String id=req.getParameter("id");
	  EmailFunction emailFunction=FunctionFactory.getFunction(EmailFunction.class);
	EmailEntity e=emailFunction.loadSendEmailById(id);
	req.setAttribute("result", e);
	return "jsp/message/email/outbox/writeEmail.jsp";
}
}
