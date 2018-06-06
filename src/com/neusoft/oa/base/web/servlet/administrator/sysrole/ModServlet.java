package com.neusoft.oa.base.web.servlet.administrator.sysrole;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.base.web.servlet.administrator.sysrole.ao.SysRoleAo;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/sysrole/mod.do")
public class ModServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
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
			fun.modifySysRole(id, ao);
			req.setAttribute("message", "修改成功！请返回");
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		} 
		// 失败过后视图
		return "/jsp/base/rolemanage/check.jsp";
	}

}
