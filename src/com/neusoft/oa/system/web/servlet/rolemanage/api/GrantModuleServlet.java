/**
 * 
 */
package com.neusoft.oa.system.web.servlet.rolemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.function.AdministratorFunction;

/**
 * @author Administrator
 *
 */
@WebServlet("/api/system/role/module/grant")
public class GrantModuleServlet extends CommonServlet{
	
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
