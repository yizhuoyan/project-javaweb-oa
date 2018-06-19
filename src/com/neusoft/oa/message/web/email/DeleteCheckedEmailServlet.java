package com.neusoft.oa.message.web.email;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.message.function.EmailFunction;

@WebServlet("/email/DeleteCheckedEmailServlet.do")
public class DeleteCheckedEmailServlet extends CommonServlet {

	private static final long serialVersionUID = 6088304964518141669L;

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String flag = req.getParameter("flag");
		String id = req.getParameter("id");
		String [] arr = id.split(",");
		EmailFunction function = FunctionFactory.getFunction(EmailFunction.class);
		function.deleteEmail(arr);
		switch (flag) {
			case "1":
				return "/email/ListSentAndReceivedEmailServlet.do";
			case "2":
				return "/email/ListReceivedEmailServlet.do";
			case "3":
				return "/email/ListSentEmailServlet.do";
			case "4":
				return "/email/SelectsDraftBinsServlet.do";
			default:
				return "/email/ListSentAndReceivedEmailServlet.do";
		}		
	}
}
