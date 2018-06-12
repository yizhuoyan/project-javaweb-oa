package com.neusoft.oa.message.web.email;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;
@WebServlet("/CheckUnreadEmailServlet.do")
public class CheckUnreadEmailServlet extends CommonServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   @Override
protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
	   String emailId=req.getParameter("id");
		EmailFunction emailFunction=FunctionFactory.getFunction(EmailFunction.class);
		EmailEntity email=emailFunction.loadUnreadEmail(emailId);
		req.setAttribute("result",email);
		return "/jsp/message/email/inbox/listEmail.jsp";
}
}
