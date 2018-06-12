package com.neusoft.oa.message.web.email;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.web.CommonServlet;
import com.neusoft.oa.message.ao.EmailAo;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;
import com.neusoft.oa.message.function.impl.EmailFunctionImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

//@WebServlet("/addEmail.do")
public class WriteEmailServlet extends CommonServlet{

	private static final long serialVersionUID = 510032630561642514L;
	
	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//获取用户
		UserContext uc = this.getCurrentUser(req);
		String name = uc.getName();
		EmployeeEntity sender = new EmployeeEntity();
		sender.setName(name);
		//获取参数
		String flag = req.getParameter("submit");
		boolean InDraftBin;
		if(flag.equals("1")) {
			InDraftBin = true;	
			req.setAttribute("message", "保存成功");
		}else {
			InDraftBin = false;
			req.setAttribute("message", "发送成功");
		}
		Part recipient = req.getPart("recipient");
		String recipientName= inputstream2string(recipient.getInputStream());
		
		EmployeeEntity recipienter = new EmployeeEntity();
		recipienter.setName(recipientName);
		
		Part tit = req.getPart("title");
		String title = inputstream2string(tit.getInputStream());
		
		Part con = req.getPart("content");
		String content = inputstream2string(con.getInputStream());
		
		Part file = req.getPart("attachment");
		String fileName = file.getSubmittedFileName();
		System.out.println(fileName+"133156");
		EmailAo ao = new EmailAo();
		if(fileName.length()!=0) {
			long fileSize = file.getSize();
			InputStream fileData = file.getInputStream();
			
			File savePath = new File("F:/upload/");
			
			if(!savePath.exists()) {
				savePath.mkdirs();
			}
			ao.setAttachment(savePath+fileName);
			saveFile(fileData,savePath + fileName);		
		}
		
		ao.setRecipient(recipienter);
		ao.setTitle(title);
		ao.setContent(content);
		ao.setInDraftBin(InDraftBin);
		ao.setSender(sender);
		 //调用业务层方法
			EmailFunction fun = new EmailFunctionImpl();
			EmailEntity email = fun.addEmail(ao);	
			req.setAttribute("result", email);
			
		return "/jsp/message/email/outbox/writeEmail.jsp";
	}
	
	private static void saveFile(InputStream in, String target) throws IOException{
		try(FileOutputStream out = new FileOutputStream(target)){
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len=in.read(buffer))!=-1) {
			out.write(buffer,0,len);
			}
		}
	}	
	public static String inputstream2string(InputStream in) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len=in.read(buffer))!=-1) {
			out.write(buffer,0,len);
		}
		byte[] data = out.toByteArray();
		return new String(data,"utf-8");
	}
}
