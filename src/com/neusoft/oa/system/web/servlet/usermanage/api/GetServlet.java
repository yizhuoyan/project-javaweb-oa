package com.neusoft.oa.system.web.servlet.usermanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysUserEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/api/system/user/get")
public class GetServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		String type=req.getParameter("type");
		
		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		SysUserEntity e = fun.loadSysUser(id);
		// 3确认视图
		if("simple".equals(type)) {
			return e.toSimpleVo();
		}
		return e.toVo();

	}

}
