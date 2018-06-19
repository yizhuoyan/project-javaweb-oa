package com.neusoft.oa.system.web.servlet.modulemanage.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.ao.SysModuleAo;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;
@WebServlet({"/api/system/module/add"})
public class AddServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//1获取参数
		String code=req.getParameter("code");
		String name=req.getParameter("name");
		String icon=req.getParameter("icon");
		String url=req.getParameter("url");
		String parentId=req.getParameter("parentId");
		String showOrder=req.getParameter("showOrder");
		String remark=req.getParameter("remark");
		String createUserId=this.getCurrentUserId(req);
		//2调用业务方法
		
		SysModuleAo ao=new SysModuleAo();
		ao.setCode(code);
		ao.setIcon(icon);
		ao.setName(name);
		ao.setParentId(parentId);
		ao.setShowOrder(showOrder);
		ao.setUrl(url);
		ao.setRemark(remark);
		ao.setCreateUserId(createUserId);
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		SysModuleEntity m=fun.addSysModule(ao);
		return m.getId();
	}
	
}
