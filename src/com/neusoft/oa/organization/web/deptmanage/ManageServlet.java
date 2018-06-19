package com.neusoft.oa.organization.web.deptmanage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.web.servlet.CommonServlet;

@WebServlet("/organization/department/manage.do")
public class ManageServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		return "/jsp/organization/deptmanage/list.jsp";
	}
}
