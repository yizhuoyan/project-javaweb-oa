package com.neusoft.oa.document.recycle.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.document.recycle.function.RecycleBinFunction;

/**
 *	清空回收站
 * @author zhoujingmeng
 *
 */
@WebServlet("/document/recycle/emptyrecycle.do")
public class EmptyRecycleServlet  extends CommonServlet{
		
		
		@Override
		protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
			//调用业务层方法
			RecycleBinFunction fun=FunctionFactory.getFunction(RecycleBinFunction.class);
			try {
				fun.emptyRecycle();
				req.setAttribute("message", "回收站已清空!");
			}catch(OAException e){
				req.setAttribute("message", e.getMessage());
			}
			req.setAttribute("nextUrl", "document/recycle/list.do");
			return "/jsp/message.jsp";
		}
}
