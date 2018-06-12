package com.neusoft.oa.attendance.web.servlet.retroactive;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neusoft.oa.attendance.function.employess.RetroactiveFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;

import com.neusoft.oa.core.web.CommonServlet;


/**
 * @author 田梦源
 *
 */
@WebServlet("/attendance/employess/retroactive/apply.do")
public class RetroactiveServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {			
			String empId=this.getCurrentUserId(req);
		try {		
			RetroactiveFunction fun=DaoFactory.getDao(RetroactiveFunction.class);
			req.setAttribute("singleRetroactiveresult",fun.singleRetroactive(empId));
			
		} catch (OAException e) {		
			req.setAttribute("message", e.getMessage());
				
		}	
		return "/jsp/attendance/employess/retroactive.jsp";
	}
}
