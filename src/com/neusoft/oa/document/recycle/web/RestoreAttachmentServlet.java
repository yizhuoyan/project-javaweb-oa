package com.neusoft.oa.document.recycle.web;

import static com.neusoft.oa.core.util.AssertThrowUtil.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.recycle.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.recycle.function.RecycleBinFunction;
/**
 * 附件还原
 * @author zhoujingmeng
 *
 */
@WebServlet("/document/recycle/attachmentmod.do")
public class RestoreAttachmentServlet extends CommonServlet{
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//1验证参数
		String id=$("获取附件编号",req.getParameter("id"));
		resp.setContentType("text/html");
		//2调用业务方法
		RecycleBinFunction rFun=FunctionFactory.getFunction(RecycleBinFunction.class);
		try {
		rFun.restoreAttachment(id);
		req.setAttribute("message", "数据已还原！");	
		}catch(OAException e){
			req.setAttribute("message", e.getMessage());
		}
		return "/jsp/base/document/recyclemanage/list.jsp";
	}
}
