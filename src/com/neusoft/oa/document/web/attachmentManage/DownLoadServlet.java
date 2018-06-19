package com.neusoft.oa.document.web.attachmentManage;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.function.AttachmentFunction;
import com.neusoft.oa.document.function.DocumentFunction;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/attachmentManage/download.do")
public class DownLoadServlet extends CommonServlet {

	@Override
	protected String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 1获取
		String id = req.getParameter("id");
		// 2调用业务方法
		try {
			AttachmentFunction fun=FunctionFactory.getFunction(AttachmentFunction.class);
			DocumentAttachmentEntity dae=fun.downloadAttachment(id);
			String path=dae.getPath();
			System.out.println(path);
			String name=path.substring(path.lastIndexOf("\\")+1);
			
			// 1设置conntenttype
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(name,"utf-8"));


			resp.setHeader("content-length", String.valueOf(dae.getSize()));
			
			ServletOutputStream out = resp.getOutputStream();
			
			// 读取本地磁盘的文件
			FileInputStream in = new FileInputStream(path);

			byte[] data = new byte[in.available()];
			// 读进来
			in.read(data);

			out.write(data);

			
			out.close();
			
			req.setAttribute("message", "下载成功！");
		} catch (OAException e) {
			req.setAttribute("message", e.getMessage());
		}
		// 3确认视图
//		req.setAttribute("nextUrl", "documentManage/list.do");
//		return "/jsp/message.jsp";
		return null;
	}

}
