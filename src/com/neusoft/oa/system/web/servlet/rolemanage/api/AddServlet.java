package com.neusoft.oa.system.web.servlet.rolemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.ao.SysRoleAo;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet({ "/api/system/role/add" })
public class AddServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String remark = req.getParameter("remark");
		// 2调用业务方法
		SysRoleAo ao = new SysRoleAo();
		ao.setCode(code);
		ao.setName(name);
		ao.setRemark(remark);

		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		SysRoleEntity newRole = fun.addSysRole(ao);
		return newRole.getId();

	}

}
