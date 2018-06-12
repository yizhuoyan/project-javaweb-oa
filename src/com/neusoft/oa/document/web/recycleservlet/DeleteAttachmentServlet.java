package com.neusoft.oa.document.web.recycleservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.document.function.RecycleBinFunction;
import static com.neusoft.oa.core.util.AssertThrowUtil.*;

/**
 * 彻底删除附件
 * @author zhoujingmeng
 *
 */
@WebServlet("/document/recycle/attachmentdelete.do")
public class DeleteAttachmentServlet extends CommonServlet {
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//1验证参数
		String id= $("附件编号",req.getParameter("id"));
		//设置响应头
		resp.setContentType("text/html");
		//2调用业务方法
		RecycleBinFunction rFun=DaoFactory.getDao(RecycleBinFunction.class);
		try {
			rFun.deleteAttachment(id);
			req.setAttribute("message", "删除成功！");
		}catch(OAException e) {
			req.setAttribute("message", e.getMessage());	
		}
		req.setAttribute("nextUrl", "document/recycle/list.do");
		return "/jsp/message.jsp";
	}
}
