package com.neusoft.oa.system.web.servlet.modulemanage;

import javax.servlet.annotation.WebServlet;

import com.neusoft.oa.core.web.servlet.CommonServlet;

@WebServlet("/system/module/manage.do")
public class ManageServlet extends CommonServlet {

	protected Object handleRequest(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws Throwable {
		return "/jsp/system/modulemanage/list.jsp";
	};
}
