package com.neusoft.oa.system.web.servlet.rolemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/api/system/role/del")
public class DelServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		fun.deleteSysRole(id);
		return AjaxResponse.ok();

	}

}
