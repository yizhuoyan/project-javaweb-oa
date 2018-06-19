package com.neusoft.oa.message.web.email;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;

@WebServlet("/email/SelectsDraftBinsServlet.do")
public class SelectsDraftBinsServlet extends CommonServlet{

	private static final long serialVersionUID = 8311584897670659144L;
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//获取参数
		UserContext uc = this.getCurrentUser(req);
		String name = uc.getName();
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
		EmailFunction fun = FunctionFactory.getFunction(EmailFunction.class);
		List<EmailEntity> emails=new ArrayList<>();
		//返回组装结果
		int total = fun.queryDraftBins(name,key, pageNo, pageSize, emails);
		totalPages=total/pageSize+1;
		req.setAttribute("result", emails);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("total", total);
		return "/jsp/message/email/drafts/drafts.jsp";
	}
}
