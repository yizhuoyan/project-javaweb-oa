package com.neusoft.oa.document.web.documentManage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.base.web.servlet.administrator.sysuser.ao.SysUserAo;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.function.DocumentFunction;
@WebServlet("/documentManage/mod.ajax")
public class DocumentModServlet extends CommonServlet {

	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取参数
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String remark = req.getParameter("remark");
		String property = req.getParameter("property");
		// 2调用业务方法
		DocumentAo ao= new DocumentAo();
		ao.setName(name);
		ao.setProperty(property);
		ao.setRemark(remark);

		try {
			DocumentFunction fun=FunctionFactory.getFunction(DocumentFunction.class);
			fun.modifyDocument(id, ao);
			
			
			return AjaxResponse.ok();
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		} 
	}
}
