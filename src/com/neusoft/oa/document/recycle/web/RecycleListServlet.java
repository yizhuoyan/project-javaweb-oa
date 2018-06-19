package com.neusoft.oa.document.recycle.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.document.recycle.dao.DocumentAttachmentDao;
import com.neusoft.oa.document.recycle.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.recycle.entity.DocumentEntity;
import com.neusoft.oa.document.recycle.entity.RecycleEntity;
import com.neusoft.oa.document.recycle.function.RecycleBinFunction;
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
		String userId =getCurrentUserId(req);
		String key = req.getParameter("key");
		String pageSize = req.getParameter("pageSize");
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize, -1);
		String pageNo = req.getParameter("pageNo");
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, -1);
		System.out.println("表现层:"+userId);
		// 2调用业务方法
		try {
			
			RecycleBinFunction fun = FunctionFactory.getFunction(RecycleBinFunction.class);
			PaginationQueryResult<DocumentAttachmentEntity> atmResult = fun.queryRecycleAttachment(userId, key, pageNoInt, pageSizeInt);
			PaginationQueryResult<DocumentEntity>	docResult =fun.queryRecycleDocument(userId, key, pageNoInt, pageSizeInt);
			
			System.out.println("附件:"+atmResult+"文档:"+docResult);
			//保存附件变量在request中
			req.setAttribute("docResult", docResult);
			//保存文档变量在request中
			req.setAttribute("atmResult", atmResult);
			// 3确定视图
			
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/document/recyclemanage/list.jsp";
	}
}
