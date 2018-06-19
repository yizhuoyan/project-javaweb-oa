package com.neusoft.oa.attendance.web.servlet.retroactive;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.function.employess.RetroactiveFunction;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.web.servlet.CommonServlet;
@WebServlet("/attendance/employess/retroactive/apply.ajax")
public class RetroactiveApplyServlet extends CommonServlet{
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			String id=req.getParameter("id");
			String reason=req.getParameter("reason");
		try {		
			RetroactiveFunction fun=DaoFactory.getDao(RetroactiveFunction.class);
			fun.retroactive(id, reason);
			return AjaxResponse.ok();
		} catch (Exception e) {		
			e.printStackTrace();
			return AjaxResponse.fail(e);				
		}	
		
	}
}
