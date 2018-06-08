package com.neusoft.oa.document.web.recycleservlet;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.function.RecycleBinFunction;
/**
 * 彻底删除文档
 * @author zhoujingmeng
 *
 */
@WebServlet("/document/recycle/docdelete.ajax")
public class DeleteDocumentServlet extends CommonServlet {
	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//1获取参数
		String id=$("获取文档编号",req.getParameter("id"));
		//调用业务方法
		RecycleBinFunction rFun=DaoFactory.getDao(RecycleBinFunction.class);
		try {
			rFun.deleteDocument(id);
			req.setAttribute("message", "删除成功！");
		}catch(OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.setAttribute("nextUrl", "document/recycle/list.do");
		return "/jsp/message.jsp";
	}
}
