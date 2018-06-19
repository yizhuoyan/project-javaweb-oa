package com.neusoft.oa.organization.web.deptmanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.organization.ao.DepartmentAo;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;
@WebServlet({"/api/organization/department/mod"})
public class ModServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//1获取参数
		String id=req.getParameter("id");
		String parentId=req.getParameter("parentId");
		String code=req.getParameter("code");
		String name=req.getParameter("name");
		String managerId=req.getParameter("managerId");
		String remark=req.getParameter("remark");
		//2调用业务方法
		DepartmentAo ao=new DepartmentAo();
		ao.setCode(code);
		ao.setManagerId(managerId);
		ao.setName(name);
		ao.setParentId(parentId);
		ao.setRemark(remark);
		OrganizationFunction fun=FunctionFactory.getFunction(OrganizationFunction.class);
		fun.modifyDepartment(id, ao);
		return AjaxResponse.ok();
	}
	
}
