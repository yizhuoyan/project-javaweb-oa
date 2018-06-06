/**
 * 
 */
package com.neusoft.oa.base.web.servlet.administrator.userrole;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysRoleEntity;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author Administrator
 *
 */
@WebServlet("/userrole/list.do")
public class ListServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		String pageSize = req.getParameter("pageSize");
		String pageNo = req.getParameter("pageNo");
		// 2调用业务方法
		try {
			AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
			PaginationQueryResult<SysUserEntity> result = fun.querySysUserByKey(key, pageNo, pageSize);
			req.setAttribute("result", result);
			List<SysRoleEntity> roles=fun.loadAllRole();
			req.setAttribute("roles", roles);
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}

		return "/jsp/base/userrole/grant.jsp";
	}
}