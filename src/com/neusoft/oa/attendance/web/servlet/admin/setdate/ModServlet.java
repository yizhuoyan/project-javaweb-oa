/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.web.servlet.admin.setdate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.ao.AdminSetDateAo;
import com.neusoft.oa.attendance.function.admin.AdminSetDateFunction;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.attendance.function.admin.AdminSetDateFunction;

@WebServlet("/attendance/admin/setdate/mod.do")
public class ModServlet extends CommonServlet {
	@Override
	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String workDay = req.getParameter("workDay");
		String remark = req.getParameter("remark");
		
		AdminSetDateAo ao = new AdminSetDateAo();
		ao.setId(id);
		ao.setWorkDay(workDay);
		ao.setRemark(remark);
		if("是".equals(workDay)) {
			String onDuty = req.getParameter("onDuty");
			String offDuty = req.getParameter("offDuty");
			String signInStart = req.getParameter("signInStart");
			String signInEnd = req.getParameter("signInEnd");
			String signOutStart = req.getParameter("signOutStart");
			String signOutEnd = req.getParameter("signOutEnd");
			ao.setOnDuty(onDuty);
			ao.setOffDuty(offDuty);
			ao.setSignInStart(signInStart);
			ao.setSignInEnd(signInEnd);
			ao.setSignOutEnd(signOutEnd);
			ao.setSignOutStart(signOutStart);
		}
		try {
			AdminSetDateFunction fun = FunctionFactory.getFunction(AdminSetDateFunction.class);
			fun.modSetDate(ao);
			return AjaxResponse.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return AjaxResponse.fail(e);
		}
	}

}
