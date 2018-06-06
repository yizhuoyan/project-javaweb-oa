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
@WebServlet("/loadUserRoles.ajax")
public class LoadUseRolesServlet extends CommonServlet{

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String userId=req.getParameter("userId");
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		List<SysRoleEntity> roles=fun.loadRolesOfUser(userId);
		return AjaxResponse.ok(roles.stream().map(r->r.getId()).toArray());
	}
}
