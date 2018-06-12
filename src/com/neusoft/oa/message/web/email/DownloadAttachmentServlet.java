package com.neusoft.oa.message.web.email;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.function.EmailFunction;

@WebServlet("/downloadAttachment.do")
public class DownloadAttachmentServlet extends CommonServlet{

	private static final long serialVersionUID = -2054489375724797858L;
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String emailId = req.getParameter("id");
		System.out.println(emailId);
		EmailFunction fun = FunctionFactory.getFunction(EmailFunction.class);
		String emailSavePath = fun.loadFilePath(emailId);
		
		String path = emailSavePath.substring(10);

		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(path,"utf-8"));
		FileInputStream in = new FileInputStream(emailSavePath);
		ServletOutputStream out = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int total=0;
		int len;
		while((len=in.read(buffer))!=-1) {
			out.write(buffer);
			total += len;
		}
		resp.setHeader("content-length", String.valueOf(total));
		out.close();
		in.close();
		
		return "CheckUnreadEmailServlet.do";
	}
}
