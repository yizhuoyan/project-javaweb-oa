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


@WebServlet("/system/role/module/grant.do")
public class GrantModuleServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		return "/jsp/system/rolemanage/grant-module.jsp";
	}
}
