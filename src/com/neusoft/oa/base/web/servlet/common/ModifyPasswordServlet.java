package com.neusoft.oa.base.web.servlet.common;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.oa.base.function.CommonFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

import com.neusoft.oa.document.function.RecycleBinFunction;

@WebServlet("/user/modifyPassword.ajax")
public class ModifyPasswordServlet extends CommonServlet {

	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String newPasswordConfirm = req.getParameter("newPasswordConfirm");
		// 1.1 获取当前用户
		UserContext uc =this.getCurrentUser(req);
		// 2执行业务方法
		CommonFunction fun =FunctionFactory.getFunction(CommonFunction.class);
		try {
			fun.modifyPassword(uc.getId(), oldPassword, newPassword, newPasswordConfirm);
			return AjaxResponse.ok();
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		}
		
		
		
	}
	
}
