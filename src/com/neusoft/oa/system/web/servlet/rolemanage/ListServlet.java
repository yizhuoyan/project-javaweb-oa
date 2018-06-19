package com.neusoft.oa.system.web.servlet.rolemanage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.QueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;


@WebServlet("/system/role/list.do")
public class ListServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		// 2调用业务方法
		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			QueryResult<SysRoleEntity> result = fun.querySysRoleByKey(key);
			req.setAttribute("result", result);
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/system/rolemanage/list.jsp";

	}
}
