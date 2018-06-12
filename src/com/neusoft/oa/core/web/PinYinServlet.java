package com.neusoft.oa.core.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.dao.DBUtil;
<<<<<<< HEAD
=======
import com.neusoft.oa.core.service.PinYinService;
>>>>>>> 9e9b7621c8d4a7813daf057771910caeed2250ed
import com.neusoft.oa.core.util.JsonUtil;

@WebServlet("/api/pinyin")
public class PinYinServlet extends HttpServlet {

	private static Map<Integer,String> CACHE_MAP=new   WeakHashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String word = req.getParameter("w");
		if(word!=null&&(word=word.trim()).length()>0) {

			StringBuilder result=new StringBuilder();
			for(int i=0;i<word.length();i++) {
			 String one=pinyin(Character.codePointAt(word, i));
			 if(one!=null) {
				 result.append(one.replaceAll("\\d", ""));
			 }
			}
			resp.setContentType("application/json");

			
			resp.setContentType("application/json");
			String result=PinYinService.pinyin(word);
			try(PrintWriter out = resp.getWriter()){
				out.write(JsonUtil.toJsonString(result.toString()));
				out.flush();
			}
		}
	}

	private String pinyin(int unicode) {
		try {
		Integer key=Integer.valueOf(unicode);
		String result=CACHE_MAP.get(key);
		if(result==null) {
			StringBuilder sql=new StringBuilder("select spelling from dic_pinyin where unicode=?");
			result=DBUtil.selectOneRowOneColumn(sql, unicode);
			CACHE_MAP.put(key, result);
		}
		return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		
}
