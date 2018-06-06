package com.neusoft.oa.base.web.servlet.administrator.sysrole;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.base.web.servlet.administrator.sysrole.ao.SysRoleAo;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

@WebServlet({ "/sysrole/add.do" })
public class AddServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		if ("GET".equals(req.getMethod())) {
			return "/jsp/base/rolemanage/add.jsp";
		}
		// 1获取参数
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String remark = req.getParameter("remark");
		// 2调用业务方法
		SysRoleAo ao = new SysRoleAo();
		ao.setCode(code);
		ao.setName(name);
		ao.setRemark(remark);

		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			fun.addSysRole(ao);
			req.setAttribute("message", "新增成功！请返回");
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/rolemanage/add.jsp";

	}

}
