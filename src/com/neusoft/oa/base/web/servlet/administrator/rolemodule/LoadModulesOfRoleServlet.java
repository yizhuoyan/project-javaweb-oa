/**
 * 
 */
package com.neusoft.oa.base.web.servlet.administrator.rolemodule;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.entity.SysRoleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author Administrator
 *
 */
@WebServlet("/loadmodulesofrole.ajax")
public class LoadModulesOfRoleServlet extends CommonServlet{

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		try {
			String roleId=req.getParameter("rid");
			List<SysModuleEntity> modules=fun.loadModulesOfRole(roleId);
			return AjaxResponse.ok(modules.stream().map(m->m.getId()).toArray());
		}catch(OAException e) {
			return AjaxResponse.fail(e);
		}
	}
}
