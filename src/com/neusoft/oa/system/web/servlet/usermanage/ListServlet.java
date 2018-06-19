package com.neusoft.oa.system.web.servlet.usermanage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysUserEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/system/user/list.do")
public class ListServlet extends CommonServlet {
	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		String pageSize=req.getParameter("pageSize");
		String pageNo=req.getParameter("pageNo");
		// 2调用业务方法
		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			PaginationQueryResult<SysUserEntity> result = fun.querySysUserByKey(key,pageNo,pageSize);
			req.setAttribute("result", result);
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/system/usermanage/list.jsp";

	}
}
