/**
 * 
 */
package com.neusoft.oa.common.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.oa.common.function.CommonFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;

/**
 * @author yizhuoyan@hotmail.com
 *
 */
@WebServlet("/login.ajax")
public class UserLoginServlet extends CommonServlet {
	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		// 获取登陆ip地址
		String loginIP = req.getRemoteHost();
		// 2业务逻辑
		// 2.1 判断验证码
		String captchaMessage = (String) req.getAttribute("captchaError");
		if (captchaMessage != null) {
			// 验证码错误
			throw new OAException(captchaMessage);
		}
		CommonFunction fun = FunctionFactory.getFunction(CommonFunction.class);
		// 2.2 执行业务逻辑
		UserContext uc = fun.login(account, password, loginIP);
		// 2.3session控制
		HttpSession session = req.getSession(false);
		// 如果之前登陆过，则失效之前的session
		if (session != null) {
			session.invalidate();
		}
		// 新建session
		session = req.getSession();
		uc.setJsessionId(session.getId());
		session.setAttribute("loginUser", uc);

		return AjaxResponse.ok(uc.toVo());
	}

}
