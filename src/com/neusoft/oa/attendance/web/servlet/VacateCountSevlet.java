package com.neusoft.oa.attendance.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.entity.AtteVacateEntity;
import com.neusoft.oa.attendance.function.QueryCountFunction;
import com.neusoft.oa.attendance.function.impl.QueryCountFunctionImpl;
import com.neusoft.oa.core.web.CommonServlet;

public class VacateCountSevlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251160309021056795L;

	protected Object dopost(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//获取参数
		String departmentId=req.getParameter("departmentId");
		String key=req.getParameter("key");
		String pageNo=req.getParameter("pageNo");
		String pageSize=req.getParameter("pageSize");
		//调用业务层方法
		QueryCountFunction qa=new QueryCountFunctionImpl();
		List<AtteVacateEntity> result=qa.QueryVacate(departmentId, key, pageNo, pageSize);
		//获取返回业务层结果		
		return result;
	}
}
