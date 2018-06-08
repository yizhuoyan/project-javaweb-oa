package com.neusoft.oa.message.web.email;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.function.EmailFunction;
import com.neusoft.oa.message.function.impl.EmailFuntionImpl;
@WebServlet("/emailAddressBookServlet.do")
public class EmailAddressBookServlet extends CommonServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = 5161549390646219247L;

@Override
protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
	//获取参数
	String key=req.getParameter("key");
	String pageNo=req.getParameter("pageNo");
	String pageSize=req.getParameter("pageSize");
	System.out.println(key);
	//调用业务逻辑
	EmailFunction fun = new EmailFuntionImpl();
	List<AddressBookEntity>  EmailAddressBooks=fun.SearchEmailAddressBookByKey(key);
	//返回组装结果
	req.setAttribute("result", EmailAddressBooks);
	System.out.println(EmailAddressBooks);
	return "/jsp/message/email/outbox/emailAddressList.jsp";
}
}
