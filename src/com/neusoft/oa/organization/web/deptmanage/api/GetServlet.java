package com.neusoft.oa.organization.web.deptmanage.api;

import java.util.Optional;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.organization.dto.DepartmentDto;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/api/organization/department/get")
public class GetServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		// 2调用业务方法
		OrganizationFunction fun = FunctionFactory.getFunction(OrganizationFunction.class);
		DepartmentEntity d = fun.loadDepartment(id);
		return DepartmentDto.of(d);
	}

}
