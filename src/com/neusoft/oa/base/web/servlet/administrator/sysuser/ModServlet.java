package com.neusoft.oa.base.web.servlet.administrator.sysuser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.base.web.servlet.administrator.sysuser.ao.SysUserAo;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/sysuser/mod.ajax")
public class ModServlet extends CommonServlet {

	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		String account = req.getParameter("account");
		String name = req.getParameter("name");
		String remark = req.getParameter("remark");
		String flag = req.getParameter("flag");
		// 2调用业务方法
		SysUserAo ao = new SysUserAo();
		ao.setAccount(account);
		ao.setName(name);
		ao.setRemark(remark);
		ao.setFlag(flag);

		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			fun.modifySysUser(id, ao);
			return AjaxResponse.ok();
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		} 
	}
}
