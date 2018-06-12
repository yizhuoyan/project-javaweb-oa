package com.neusoft.oa.message.web.email;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;
@WebServlet("/searchRecycleBinServlet.do")
public class SelectsRecycleBinServlet extends CommonServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4849970371141856692L;
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
	  UserContext uc = this.getCurrentUser(req);
	  String userEmailId=uc.getName();
	  //执行业务逻辑方法
	  EmailFunction emailFunction=FunctionFactory.getFunction(EmailFunction.class);
	  List<EmailEntity> result=new ArrayList<>();
	  int total=emailFunction.selectRecyeBinServletByUserEmailTotal(userEmailId,key, pageNo, pageSize, result);
	    totalPages=total/pageSize+1;
		req.setAttribute("result", result);
		req.setAttribute("key",key);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("total", total);
	   req.setAttribute("result", result);
	  return "/jsp/message/email/wasteBox/wasteBox.jsp";
}
}
