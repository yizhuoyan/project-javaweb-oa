package com.neusoft.oa.organization.web.deptmanage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.QueryResult;
import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.organization.dto.DepartmentDto;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/deptmanage/list.ajax")
public class ListServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		// 2调用业务方法
		OrganizationFunction fun = FunctionFactory.getFunction(OrganizationFunction.class);
		List<DepartmentEntity> result = fun.loadAllDepartment();
		return result.stream().map(DepartmentDto::of).collect(Collectors.toList());
	}
}
