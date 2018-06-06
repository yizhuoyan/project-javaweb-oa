package com.neusoft.oa.base.web.servlet.common;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.CommonFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

@WebServlet("/user/modifySecurityEmail/sendEmail.ajax")
public class SendEmailForModifySecurityEmailAdressServlet extends CommonServlet {

	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		try {
			// 1获取参数
			String email = req.getParameter("email");
			String captchaError = (String) req.getAttribute("captchaError");
			if (captchaError != null) {
				throw new OAException(captchaError);
			}
			// 1.1 获取当前用户
			UserContext uc = this.getCurrentUser(req);
			// 2执行业务方法
			CommonFunction fun = FunctionFactory.getFunction(CommonFunction.class);

			String code = fun.sendModifySecurityEmailValidteCodeEmail(uc.getId(), email);
			this.putSessionAttribute(req,"MODIFY-SECURITY-EMAIL-CONFIRM-CODE", code);
			return AjaxResponse.ok();
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		}
	}
}
