package com.neusoft.oa.message.web.email;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.message.function.EmailFunction;
@WebServlet("/CompletelyDeleteServlet.do")
public class CompletelyDeleteServlet extends CommonServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5318861628216258968L;
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//获取参数
		String emailId=req.getParameter("emailId");
		EmailFunction emailFunction=FunctionFactory.getFunction(EmailFunction.class);
		int e=emailFunction.completelyDelete(emailId);
		return "searchRecycleBinServlet.do";
	}
}
