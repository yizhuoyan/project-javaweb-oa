package com.neusoft.oa.base.web.servlet.administrator.sysmodule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.base.web.servlet.administrator.sysmodule.ao.SysModuleAo;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet({"/sysmodule/add.ajax"})
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
		//2调用业务方法
		
		SysModuleAo ao=new SysModuleAo();
		ao.setCode(code);
		ao.setIcon(icon);
		ao.setName(name);
		ao.setParentId(parentId);
		ao.setShowOrder(showOrder);
		ao.setUrl(url);
		ao.setRemark(remark);
		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			SysModuleEntity m=fun.addSysModule(ao);
			return AjaxResponse.ok(m.getId());
		}catch(OAException e) {
			return AjaxResponse.fail(e);
		}
	}
	
}
