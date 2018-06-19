package com.neusoft.oa.system.web.servlet.usermanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.ao.SysUserAo;
import com.neusoft.oa.system.entity.SysUserEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet({ "/api/system/user/add" })
public class AddServlet extends CommonServlet {
	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String account = req.getParameter("account");
		String name = req.getParameter("name");
		String remark = req.getParameter("remark");
		// 2调用业务方法
		SysUserAo ao = new SysUserAo();
		ao.setAccount(account);
		ao.setName(name);
		ao.setRemark(remark);

		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		SysUserEntity newUser = fun.addSysUser(ao);
		return newUser.getId();
	}

}
