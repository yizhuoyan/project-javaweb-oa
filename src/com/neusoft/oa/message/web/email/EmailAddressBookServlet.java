package com.neusoft.oa.message.web.email;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.function.AddressBookFuntion;
import com.neusoft.oa.message.function.EmailFunction;
import com.neusoft.oa.message.function.impl.AddressBookFuntionImpl;
import com.neusoft.oa.message.function.impl.EmailFunctionImpl;
@WebServlet("/emailAddressBookServlet.do")
public class EmailAddressBookServlet extends CommonServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = 5161549390646219247L;

@Override
protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
	//获取参数
	int pageNo;
	int totalPages;
	String key=req.getParameter("key");
	if(req.getParameter("pageNo")==null) {
		 pageNo=1;
	}else {
		pageNo=Integer.parseInt(req.getParameter("pageNo"));
	}
	int pageSize=10;
	String pageData=req.getParameter("pageSize");
	//调用业务逻辑
	AddressBookFuntion fun =FunctionFactory.getFunction(AddressBookFuntion.class);
	List<AddressBookEntity>  EmailAddressBooks=fun.searchEmailAddressBookByKey(key);
	EmailAddressBooks.clear();
	//返回组装结果
	int total=fun.searchTotal(key, pageNo, pageSize, EmailAddressBooks);
	totalPages=total/pageSize+1;
	req.setAttribute("result", EmailAddressBooks);
	req.setAttribute("key",key);
	req.setAttribute("pageNo", pageNo);
	req.setAttribute("totalPages", totalPages);
	req.setAttribute("total", total);
	return "/jsp/message/email/outbox/emailAddressList.jsp";
}
}
