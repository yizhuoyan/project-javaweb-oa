package com.neusoft.oa.message.web.email;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ArgumentObjectUtil;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.ao.EmailAo;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;
import com.neusoft.oa.message.function.impl.EmailFuntionImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

@WebServlet("/addEmail.ajax")
public class WriteEmailServlet extends CommonServlet{

	private static final long serialVersionUID = 510032630561642514L;
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//获取参数
		String recipient = req.getParameter("recipient");
		String save=req.getParameter("save");
		System.out.println(save);
		boolean InDraftBin = "true".equals(save);
		EmployeeEntity u = new EmployeeEntity();
		u.setId(recipient);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String attachment = req.getParameter("attachment");
		System.out.println(recipient);
		//boolean save = req.getParameter("save");
		EmailAo ao = new EmailAo();
		ao.setRecipient(u);
		ao.setTitle(title);
		ao.setContent(content);
		ao.setAttachment(attachment);
		ao.setInDraftBin(InDraftBin);
		//调用业务层方法
		
		EmailFunction fun = new EmailFuntionImpl();
		EmailEntity email = fun.addEmail(ao);
		
		return email;
	}
}
