package com.neusoft.oa.attendance.web.servlet.vacate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.ao.VacateAo;
import com.neusoft.oa.attendance.function.employess.VacateFunction;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.web.CommonServlet;


/**
 * @author 田梦源
 *
 */


@WebServlet("/attendance/employess/vacate/apply.ajax")
public class VacateServlet extends CommonServlet{
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=this.getCurrentUserId(req);
		String startTime=req.getParameter("startTime");
		String startM=req.getParameter("startM");
		String endTime=req.getParameter("endTime");
		String endM=req.getParameter("endM");
		String vacateType=req.getParameter("vacateType");
		String reason=req.getParameter("reason");
		VacateAo ao=new VacateAo();
		ao.setEmpId(id);
		ao.setStarttime(startTime);
		ao.setStartM(startM);
		ao.setEndtime(endTime);
		ao.setEndM(endM);
		ao.setType(vacateType);
		ao.setReason(reason);
		try {		
			VacateFunction u=DaoFactory.getDao(VacateFunction.class);
			u.vacate(ao);	
					
			return AjaxResponse.ok();
		} catch (Exception e) {		
				e.printStackTrace();
			return AjaxResponse.fail(e);
		}	
	}
}
