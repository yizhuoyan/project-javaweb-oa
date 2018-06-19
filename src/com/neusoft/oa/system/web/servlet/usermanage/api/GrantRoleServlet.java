/**
 * 
 */
package com.neusoft.oa.system.web.servlet.usermanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.function.AdministratorFunction;

/**
 * @author Administrator
 *
 */
@WebServlet("/api/system/user/role/grant")
public class GrantRoleServlet extends CommonServlet{

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String userId=req.getParameter("userId");
		String roles=trim(req.getParameter("roles"));
		String[] roleIds=new String[0];
		if(roles!=null) {
			roleIds=roles.split(",");
		}
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		fun.grantRoles2User(userId, roleIds);
		return AjaxResponse.ok();
	}
}
