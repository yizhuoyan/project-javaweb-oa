package com.neusoft.oa.attendance.web.servlet.clockin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neusoft.oa.attendance.function.employess.ClockInFunction;
import com.neusoft.oa.attendance.function.employess.impl.ClockInFunctionImpl;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.web.CommonServlet;

/**
 * @author 田梦源
 *
 */
@WebServlet("/attendance/employess/clock-in.ajax")
public class ClockInServlet  extends CommonServlet{	
	
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id=this.getCurrentUserId(req);
			Date signInDate=new Date();
		
		try {		
			ClockInFunction u=new ClockInFunctionImpl();
			u.clockIn(id, signInDate);	
			
		
			return AjaxResponse.ok();
		} catch (Exception e) {		
				e.printStackTrace();
				return AjaxResponse.fail(e);
		}	
		
	}
	
}


