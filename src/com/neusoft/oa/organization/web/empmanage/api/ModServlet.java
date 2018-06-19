package com.neusoft.oa.organization.web.empmanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ArgumentObjectUtil;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/api/organization/employee/mod")
public class ModServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {

		// 1获取参数
		String id=req.getParameter("id");
		EmployeeAo ao=ArgumentObjectUtil.parse(req, EmployeeAo.class);
		
		// 2调用业务层方法
		OrganizationFunction fun=FunctionFactory.getFunction(OrganizationFunction.class);
		fun.modifyEmployee(id, ao);
		// 3返回业务结果
		return AjaxResponse.ok();
	}
}
