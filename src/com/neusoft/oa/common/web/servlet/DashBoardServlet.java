/**
 * 
 */
package com.neusoft.oa.common.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.web.servlet.CommonServlet;

/**
 * @author 易拙言
 *
 */
@WebServlet("/dashboard.do")
public class DashBoardServlet extends CommonServlet{

	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		return "/jsp/common/dashboard.jsp";
	}
}
