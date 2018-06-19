package com.neusoft.oa.attendance.web.servlet.clockin;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neusoft.oa.attendance.function.employess.ClockInFunction;
import com.neusoft.oa.attendance.function.employess.impl.ClockInFunctionImpl;
import com.neusoft.oa.core.web.servlet.CommonServlet;
/**
 * @author 田梦源
 *
 */
@WebServlet("/attendance/employess/clock-in/list.do")
public class ClockInListServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id=this.getCurrentUserId(req);
			ClockInFunction u=new ClockInFunctionImpl();
				
		try {		
			req.setAttribute("clockinresult",u.select2data(id));
		
		} catch (Exception e) {		
				e.printStackTrace();
				
		}	
		return "/jsp/attendance/employess/clockin.jsp";
	}
}
