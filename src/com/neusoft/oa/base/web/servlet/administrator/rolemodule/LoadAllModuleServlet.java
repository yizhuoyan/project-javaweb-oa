/**
 * 
 */
package com.neusoft.oa.base.web.servlet.administrator.rolemodule;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.TreeNode;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author Administrator
 *
 */
@WebServlet("/loadallmodules.ajax")
public class LoadAllModuleServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
		try {
			List<SysModuleEntity> modules = fun.loadAllModules();
			List<TreeNode> result=TreeNode.of(modules, m->{
				//for fancyTree
				TreeNode item=new TreeNode();
				item.put("icon", m.getIcon());
				item.put("key", m.getId());
				item.put("title", m.getName());
				item.put("icon", m.getIcon());
				return item;
			});
			return AjaxResponse.ok(result);
		} catch (OAException e) {
			return AjaxResponse.fail(e);
		}
	}
	
	

}
