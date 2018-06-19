/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.web.servlet.admin.setdate;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.dto.AdminSetDateDto;
import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.attendance.function.admin.AdminSetDateFunction;
import com.neusoft.oa.attendance.function.admin.impl.AdminSetDateFunctionImpl;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.servlet.CommonServlet;

@WebServlet("/attendance/admin/setdate/list.do")
public class ListServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		String pageSize = req.getParameter("pageSize");
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize, -1);
		String pageNo = req.getParameter("pageNo");
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, -1);
		// 2调用业务方法
		try {
			AdminSetDateFunction fun = new AdminSetDateFunctionImpl();
			PaginationQueryResult<AdminSetDateEntity> result = fun.listSetDate(key, pageNoInt, pageSizeInt);
			req.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/attendance/admin/setdate/list.jsp";
	}
}
