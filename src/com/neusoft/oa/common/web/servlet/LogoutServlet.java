/**
 * 
 */
package com.neusoft.oa.common.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.web.servlet.CommonServlet;

/**
 * @author Administrator
 *
 */
@WebServlet({"/logout.do","/logout.ajax"})
public class LogoutServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.getSession().invalidate();
		if(req.getRequestURI().endsWith(".ajax")) {
			return AjaxResponse.ok();
		}
		return "@/jsp/login.jsp";
	}
}
