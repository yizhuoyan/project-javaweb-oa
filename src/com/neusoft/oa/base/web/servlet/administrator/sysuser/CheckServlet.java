package com.neusoft.oa.base.web.servlet.administrator.sysuser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

@WebServlet("/sysuser/check.do")
public class CheckServlet extends CommonServlet {

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		// 2调用业务方法
		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			SysUserEntity e = fun.checkSysUser(id);
			req.setAttribute("vo", e);
			return "/jsp/base/usermanage/check.jsp";
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		// 3确认视图
		return "/jsp/message.jsp";

	}

}
