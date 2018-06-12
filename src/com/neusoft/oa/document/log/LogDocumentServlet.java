package com.neusoft.oa.document.log;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.log.entity.DocumentLogEntity;
import com.neusoft.oa.document.log.function.DocumentLogAO;
import com.neusoft.oa.document.log.function.DocumentLogFunction;

@WebServlet("/logDocument.do")
public class LogDocumentServlet extends CommonServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3004170799569848827L;

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {

		int pageNo = 1;//Integer.parseInt(req.getParameter("pageNo"));
		int pageSize = 10;//Integer.parseInt(req.getParameter("pageSize"));

		DocumentLogAO ao = new DocumentLogAO();

		ao.setTarget(req.getParameter("target"));
		ao.setOperation(req.getParameter("operation"));
		ao.setBeginoperationTime(req.getParameter("beginoperationTime"));
		ao.setEndoperationTime(req.getParameter("endoperationTime"));
		ao.setOperatorId(req.getParameter("operatiorId"));

		DocumentLogFunction fun = FunctionFactory.getFunction(DocumentLogFunction.class);
		PaginationQueryResult<DocumentLogEntity> result = fun.queryLog(ao, pageNo, pageSize);
		req.setAttribute("result", result);

		return "/jsp/base/document/logManagment/logManagment.jsp";
	}
}
