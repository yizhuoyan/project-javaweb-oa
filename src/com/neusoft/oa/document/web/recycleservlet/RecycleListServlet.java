package com.neusoft.oa.document.web.recycleservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.RecycleEntity;
import com.neusoft.oa.document.function.RecycleBinFunction;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/document/recycle/list.do")
public class RecycleListServlet extends CommonServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5463079649725345873L;

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取查询
		String key = req.getParameter("key");
		String pageSize = req.getParameter("pageSize");
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize, -1);
		String pageNo = req.getParameter("pageNo");
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, -1);
		// 2调用业务方法
		try {
			
			RecycleBinFunction fun = FunctionFactory.getFunction(RecycleBinFunction.class);
			PaginationQueryResult<DocumentAttachmentEntity> result = fun.queryRecycleAttachment(key, pageNoInt, pageSizeInt);
			
			req.setAttribute("result", result);
			
			// 3确定视图
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/document/recyclemanage/list.jsp";
	}
}
