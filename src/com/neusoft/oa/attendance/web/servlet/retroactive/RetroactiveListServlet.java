package com.neusoft.oa.attendance.web.servlet.retroactive;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.entity.RetroactiveEntity;

import com.neusoft.oa.attendance.function.employess.RetroactiveFunction;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
/**
 * @author 田梦源
 *
 */

@WebServlet("/attendance/employess/retroactive/list.do")
public class RetroactiveListServlet extends CommonServlet {
	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String id=this.getCurrentUserId(req);
		String key = req.getParameter("key");
		String pageSize=req.getParameter("pageSize");
		int pageSizeInt=ThisSystemUtil.parseInt(pageSize, -1);
		String pageNo=req.getParameter("pageNo");
		int pageNoInt=ThisSystemUtil.parseInt(pageNo, -1);
		// 2调用业务方法
		try {
			RetroactiveFunction fun=FunctionFactory.getFunction(RetroactiveFunction.class);
			PaginationQueryResult<RetroactiveEntity> result = fun.listRetroactive(id,key, pageNoInt, pageSizeInt);
			
			req.setAttribute("retroactiveresult", result);
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/attendance/employess/retroactiveRecord.jsp";
	}
}


