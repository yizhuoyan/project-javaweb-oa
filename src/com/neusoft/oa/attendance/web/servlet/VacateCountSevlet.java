package com.neusoft.oa.attendance.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.entity.AtteVacateEntity;
import com.neusoft.oa.attendance.function.DeptManagerFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/attendance/manager/allowvacate/list.do")
public class VacateCountSevlet extends CommonServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251160309021056795L;
protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		
		//1获取参数
		String managerid=this.getCurrentUserId(req);
		
		String key=req.getParameter("key");
		String pageNo=req.getParameter("pageNo");
		String pageSize=req.getParameter("pageSize");
		
		int pageNoInt=ThisSystemUtil.parseInt(pageNo, -1);
		int pageSizeInt=ThisSystemUtil.parseInt(pageSize, -1);
	
		//2调用业务层方法获取业务结果
		DeptManagerFunction fun=FunctionFactory.getFunction(DeptManagerFunction.class);
		try {
		
			PaginationQueryResult<AtteVacateEntity> result = fun.queryVacate(managerid,key,pageNoInt,pageSizeInt);
			
			req.setAttribute("result", result);

		}catch(OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/attendance/atteVacate.jsp";
	}

}
