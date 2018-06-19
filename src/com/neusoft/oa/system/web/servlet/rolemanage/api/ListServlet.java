/**
 * 
 */
package com.neusoft.oa.system.web.servlet.rolemanage.api;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

/**
 * @author Administrator
 *
 */
@WebServlet("/api/system/role/list")
public class ListServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		List<SysRoleEntity> roles = fun.loadAllRole();
		return roles.stream().map(SysRoleEntity::toVo).collect(Collectors.toList());
	}
}
