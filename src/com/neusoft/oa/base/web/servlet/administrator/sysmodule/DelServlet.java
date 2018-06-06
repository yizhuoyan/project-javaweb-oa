package com.neusoft.oa.base.web.servlet.administrator.sysmodule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/sysmodule/del.ajax")
public class DelServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		// 2调用业务方法
		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			fun.deleteSysmodule(id);
			return AjaxResponse.ok();
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		} 
	}

}
