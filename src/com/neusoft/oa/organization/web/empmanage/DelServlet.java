package com.neusoft.oa.organization.web.empmanage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.organization.function.OrganizationFunction;
@WebServlet("/empmanage/del.ajax")
public class DelServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		// 2调用业务方法
		OrganizationFunction fun=FunctionFactory.getFunction(OrganizationFunction.class);
		fun.deleteEmployee(id);
		return AjaxResponse.ok();
	}

}
