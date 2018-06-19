package com.neusoft.oa.system.web.servlet.rolemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/api/system/role/get")
public class LoadServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		SysRoleEntity e = fun.loadSysRole(id);
		return e.toVo();
	}

}
