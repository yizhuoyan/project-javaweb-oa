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

@WebServlet("/system/user/role/grant.do")
public class GrantRoleServlet extends CommonServlet {
	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		return "/jsp/system/usermanage/grant-role.jsp";
	}
}
