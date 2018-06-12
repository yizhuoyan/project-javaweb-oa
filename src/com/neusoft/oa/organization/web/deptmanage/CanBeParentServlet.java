package com.neusoft.oa.organization.web.deptmanage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.organization.dto.DepartmentDto;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/deptmanage/canBeParent.ajax")
public class CanBeParentServlet extends CommonServlet{
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String id=req.getParameter("id");
		
		OrganizationFunction fun=FunctionFactory.getFunction(OrganizationFunction.class);
		List<DepartmentEntity> depts=fun.loadCanBeParentList(id);
		
		return depts.stream().map(DepartmentDto::ofSimple).collect(Collectors.toList());
		}
	
	
}
