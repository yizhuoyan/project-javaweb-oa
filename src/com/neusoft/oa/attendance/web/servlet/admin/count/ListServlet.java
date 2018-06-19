/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.web.servlet.admin.count;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.function.admin.AdminCountFunction;
import com.neusoft.oa.attendance.function.admin.impl.AdminCountFunctionImpl;
import com.neusoft.oa.core.web.servlet.CommonServlet;
@WebServlet("/attendance/admin/count/list.do")
public class ListServlet extends CommonServlet{
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		AdminCountFunction fun = new AdminCountFunctionImpl();
		try {
			req.setAttribute("result", fun.loadAllCount());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "/jsp/attendance/admin/count/list.jsp";
	}
}
