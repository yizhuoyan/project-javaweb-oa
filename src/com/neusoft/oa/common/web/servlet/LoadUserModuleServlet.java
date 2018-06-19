/**
 * 
 */
package com.neusoft.oa.common.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.common.function.CommonFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.TreeNode;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

/**
 * @author Administrator
 *
 */
@WebServlet("/loadmodules.ajax")
public class LoadUserModuleServlet extends CommonServlet {
	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String userId = req.getParameter("userId");
		if(userId==null) {
			userId=this.getCurrentUserId(req);
		}
		try {
			CommonFunction fun=FunctionFactory.getFunction(CommonFunction.class);
			List<SysModuleEntity> modules = fun.loadUserModule(userId);
			List<TreeNode> result=TreeNode.of(modules, m->{
				TreeNode item=new TreeNode();
				item.setCode(m.getCode());
				item.setId(m.getId());
				item.setName(m.getName());
				item.put("url", m.getUrl());
				return item;
			});
			return AjaxResponse.ok(result);
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		}
	}
	
	
}
