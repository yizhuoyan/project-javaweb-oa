package com.neusoft.oa.organization.web.empmanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.organization.dto.EmployeeDto;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/api/organization/employee/get")
public class GetServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		// 2调用业务方法
		OrganizationFunction fun = FunctionFactory.getFunction(OrganizationFunction.class);
		EmployeeEntity d = fun.loadEmployee(id);
		return EmployeeDto.of(d);
	}

}
