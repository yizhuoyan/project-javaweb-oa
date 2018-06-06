/**
 * 
 */
package com.neusoft.oa.base.web.servlet.administrator.rolemodule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author Administrator
 *
 */
@WebServlet("/grantmoudels2role.ajax")
public class GrantModules2RoleServlet extends CommonServlet{
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String modulesIdsStr=req.getParameter("modules");
		String roleId=req.getParameter("roleId");
		String[] modulesIdsArr=modulesIdsStr.split(",");
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		
		fun.grantModules2Role(roleId, modulesIdsArr);
		return AjaxResponse.ok();
	}
}
