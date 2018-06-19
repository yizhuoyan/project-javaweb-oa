package com.neusoft.oa.system.web.servlet.modulemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.ao.SysModuleAo;
import com.neusoft.oa.system.function.AdministratorFunction;
@WebServlet("/api/system/module/mod")
public class ModServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		String parentId=req.getParameter("parentId");
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String icon = req.getParameter("icon");
		String url = req.getParameter("url");
		String showOrder = req.getParameter("showOrder");
		String remark = req.getParameter("remark");
		// 2调用业务方法
		SysModuleAo ao = new SysModuleAo();
		ao.setCode(code);
		ao.setIcon(icon);
		ao.setName(name);
		ao.setShowOrder(showOrder);
		ao.setUrl(url);
		ao.setRemark(remark);
		ao.setParentId(parentId);

		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			fun.modifySysModule(id, ao);
			return AjaxResponse.ok();
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		}
	}

}
