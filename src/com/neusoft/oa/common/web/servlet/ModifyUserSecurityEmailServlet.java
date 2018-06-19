/**
 * 
 */
package com.neusoft.oa.common.web.servlet;

import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.common.function.CommonFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.servlet.CommonServlet;

/**
 * @author 易拙言
 *
 */
@WebServlet("/user/modifySecurityEmail.do")
public class ModifyUserSecurityEmailServlet extends CommonServlet{

	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
				String code = req.getParameter("code");
				if(ThisSystemUtil.isBlank(code)) {
					throw new OAException("验证码必须填写");
				}
				//验证code
				String saveCode=this.getSessionAttribute(req, "MODIFY-SECURITY-EMAIL-CONFIRM-CODE");
				
				if(!Objects.equals(code, saveCode)) {
					throw new OAException("验证不正确");
				}
				String newEmail = req.getParameter("email");
				// 1.1 获取当前用户
				UserContext uc = this.getCurrentUser(req);
				// 2执行业务方法
				CommonFunction fun =FunctionFactory.getFunction(CommonFunction.class);
				try {
					fun.modifySecurityEmail(uc.getId(), newEmail);
					return AjaxResponse.ok();
				} catch (OAException e) {
					return AjaxResponse.fail(e);
				}
	}
}
