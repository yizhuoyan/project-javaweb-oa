/**
 * 
 */
package com.neusoft.oa.base.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author 易拙言
 *
 */
@WebServlet("/welcome.do")
public class WelcomeServlet extends CommonServlet {
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		if(this.isLogin(req)) {
			return "/index.do";
		}else {
			return "/jsp/login.jsp";
		}
	}

}
