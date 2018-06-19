package com.neusoft.oa.core.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.PinYinService;
import com.neusoft.oa.core.util.JsonUtil;

@WebServlet("/api/pinyin")
public class PinYinServlet extends HttpServlet {
	private static Map<Integer,String> CACHE_MAP=new   WeakHashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String word = req.getParameter("w");
		if(word!=null&&(word=word.trim()).length()>0) {
			String result=PinYinService.pinyin(word);
			try(PrintWriter out = resp.getWriter()){
				out.write(JsonUtil.toJsonString(result.toString()));
				out.flush();
			}
		}
	}
	
		
}
