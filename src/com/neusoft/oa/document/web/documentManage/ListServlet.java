package com.neusoft.oa.document.web.documentManage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.DocumentFunction;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;


@WebServlet("/documentManage/list.do")
public class ListServlet extends CommonServlet{

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		String pageSize=req.getParameter("pageSize");
		int pageSizeInt=ThisSystemUtil.parseInt(pageSize, -1);
		String pageNo=req.getParameter("pageNo");
		int pageNoInt=ThisSystemUtil.parseInt(pageNo, -1);
		//获取部门id
		String userId=getCurrentUserId(req);
		EmployeeDao edao=DaoFactory.getDao(EmployeeDao.class);
		EmployeeEntity emp=edao.select("id", userId);
		String deptId=emp.getDepartment().getId();
		if (deptId==null) {
			OAException.throwWithMessage("部门id为空", deptId);
		}
		// 2调用业务方法
		try {
			DocumentFunction fun=FunctionFactory.getFunction(DocumentFunction.class);
			PaginationQueryResult<DocumentEntity> result = fun.listDocument(deptId,key, pageNoInt, pageSizeInt);
			
			req.setAttribute("result", result);
			System.out.println(result);
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/document/documentManager/list.jsp";

	}
}
