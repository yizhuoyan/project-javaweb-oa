/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.web.servlet.admin.setdate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.attendance.function.admin.AdminSetDateFunction;
import com.neusoft.oa.attendance.function.admin.impl.AdminSetDateFunctionImpl;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/attendance/admin/setdate/check.do")
public class CheckServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// TODO Auto-generated method stub
		// 2调用业务方法
		String id = req.getParameter("id");
		try {
			AdminSetDateFunction fun = new AdminSetDateFunctionImpl();
			req.setAttribute("result", fun.loadSetDate(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/attendance/admin/setdate/check.jsp";
	}
}
