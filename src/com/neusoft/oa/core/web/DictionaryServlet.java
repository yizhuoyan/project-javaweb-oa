package com.neusoft.oa.core.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import com.neusoft.oa.core.dictionary.Dictionary;
@WebServlet("/dictionary/*")
public class DictionaryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
		String type=getDictionaryType(req);
		Dictionary dictionary=Dictionary.of(type);
		if(dictionary!=null) {
			
			String format=getDictionaryFormat(req);
			switch (format) {
			case "json":
				write2json(dictionary,resp);
				break;
			case "js":
				write2js(dictionary,resp);
				break;
			case "jsonp":
				write2jsonp(dictionary, req, resp);
				break;	
			default:
				break;
			}
		}
	}
	
	private void write2js(Dictionary dictionary,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/script;charset=utf-8");
		try(PrintWriter out = resp.getWriter()){
			out.write(";this[\""+dictionary.type+"\"]=");
			out.write(dictionary.toJsonString());
			out.write(";");
			out.flush();
		}
	}
	private void write2json(Dictionary dictionary,HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		try(PrintWriter out = resp.getWriter()){
			out.write(dictionary.toJsonString());
			out.flush();
		}
	}
	private void write2jsonp(Dictionary dictionary,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/script;charset=utf-8");
		String callback=req.getParameter("callback");
		if(callback==null) {
			callback=dictionary.type;
		}
		try(PrintWriter out = resp.getWriter()){
			out.write(";"+callback+"(");
			out.write(dictionary.toJsonString());
			out.write(");");
			out.flush();
		}
	}
	private String getDictionaryFormat(HttpServletRequest req) {
		String url = req.getRequestURI();
		url=url.substring((req.getContextPath()+"/dictionary/").length());
		return url.substring(url.indexOf(".")+1);
	}
	private String getDictionaryType(HttpServletRequest req) {
		String url = req.getRequestURI();
		url=url.substring((req.getContextPath()+"/dictionary/").length());
		
		return url.substring(0, url.indexOf("."));
	}
	
	
}
