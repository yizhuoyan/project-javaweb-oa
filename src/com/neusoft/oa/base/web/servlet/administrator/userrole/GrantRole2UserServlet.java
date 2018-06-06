/**
 * 
 */
package com.neusoft.oa.base.web.servlet.administrator.userrole;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysRoleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author Administrator
 *
 */
@WebServlet("/grantrole2user.ajax")
public class GrantRole2UserServlet extends CommonServlet{

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String userId=req.getParameter("userId");
		String roles=req.getParameter("roles");
		String[] roleIds=roles.split(",");
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		fun.grantRoles2User(userId, roleIds);
		return AjaxResponse.ok();
	}
}
