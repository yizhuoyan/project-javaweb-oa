/**
 * 
 */
package com.neusoft.oa.system.web.servlet.usermanage.api;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

/**
 * @author Administrator
 *
 */
@WebServlet("/api/system/user/role/list")
public class ListGrantedRoleServlet extends CommonServlet{

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String userId=req.getParameter("id");
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		List<SysRoleEntity> roles=fun.loadRolesOfUser(userId);
		return roles.stream().map(SysRoleEntity::toVo).toArray();
	}
}
