package com.neusoft.oa.document.web.attachmentManage;

import javax.servlet.annotation.WebServlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/attachmentManage/list.do")
public class ListServlet extends CommonServlet{

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		String pageSize=req.getParameter("pageSize");
		int pageSizeInt=ThisSystemUtil.parseInt(pageSize, -1);
		String pageNo=req.getParameter("pageNo");
		int pageNoInt=ThisSystemUtil.parseInt(pageNo, -1);
		// 2调用业务方法
		try {
			OrganizationFunction fun=FunctionFactory.getFunction(OrganizationFunction.class);
			PaginationQueryResult<EmployeeEntity> result = fun.listEmployee(key, pageNoInt, pageSizeInt);
			
			req.setAttribute("result", result);
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/organization/empmanage/list.jsp";

	}
}
