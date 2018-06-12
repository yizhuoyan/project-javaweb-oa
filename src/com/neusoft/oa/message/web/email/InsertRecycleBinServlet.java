package com.neusoft.oa.message.web.email;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.function.EmailFunction;
@WebServlet("/inRecycleBinServlet.do")
public class InsertRecycleBinServlet extends CommonServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1678965595745148141L;
  @Override
protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
	   //获取用户
	  String id=req.getParameter("id");
	  String pageId=req.getParameter("pageId");
	  EmailFunction emailFunction=FunctionFactory.getFunction(EmailFunction.class);
	  int c=emailFunction.deleteEmail(id);
	  switch (pageId) {
	case "1":
		return "email/ListSentAndReceivedEmailServlet.do";
	case "2":
		return "email/ListReceivedEmailServlet.do";	
	case "3":
		return "email/ListSentEmailServlet.do";	
	case "4":
		return "email/SelectsDraftBinsServlet.do";	
	default:
		 return "email/ListSentAndReceivedEmailServlet.do";
	}
	  	
}
}
