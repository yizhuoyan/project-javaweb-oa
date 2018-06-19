package com.neusoft.oa.system.web.servlet.modulemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.function.AdministratorFunction;
@WebServlet("/api/system/module/del")
public class DelServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String id = req.getParameter("id");
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		fun.deleteSysmodule(id);
		return AjaxResponse.ok();
	}
}
