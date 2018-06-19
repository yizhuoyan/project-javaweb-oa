package com.neusoft.oa.organization.web.deptmanage.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.QueryResult;
import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.organization.dto.DepartmentDto;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/api/organization/department/list")
public class ListServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String type=req.getParameter("type");
		if(type==null) {
			type="detail";
		}
		// 2调用业务方法
		OrganizationFunction fun = FunctionFactory.getFunction(OrganizationFunction.class);
		List<DepartmentEntity> result = fun.loadAllDepartment();
		if("detail".equals(type)) {
			return result.stream().map(DepartmentDto::of).collect(Collectors.toList());
		}else {
			return result.stream().map(DepartmentDto::ofSimple).collect(Collectors.toList());
		}
	}
}
