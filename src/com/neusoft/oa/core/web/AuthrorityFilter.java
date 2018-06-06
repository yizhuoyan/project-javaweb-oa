package com.neusoft.oa.core.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class AuthrorityFilter implements Filter {
	private static final Logger LOG=Logger.getLogger(AuthrorityFilter.class);
	private final Set<String> whiteListSet=new HashSet<>(16,1); 
	
	/**
	 * 判断uri是否在白名单中
	 * @param uri
	 * @return
	 */
	private boolean isInWhiteList(String uri) {
		return this.whiteListSet.contains(uri);
	}
	@Override
	public void doFilter(ServletRequest servletRequest, 
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		StringBuilder message=new StringBuilder();
		try {
		HttpServletRequest req=(HttpServletRequest)servletRequest;
		HttpServletResponse resp=(HttpServletResponse) servletResponse;
		//1首先获取到请求的url
		 String uri=req.getRequestURI();
		 message.append("->").append(uri);
		 //2去掉应用名称
		 uri=uri.substring(req.getContextPath().length());
		//3 判断uri是否是白名单
		 if(isInWhiteList(uri)) {
			//3.1如果是直接通过
				filterChain.doFilter(req, resp);
				message.append("白名单直接通过");
				return;
		 }
		//3.2如果不是，判断是否登陆
		HttpSession session=req.getSession();
		Object loginInfo=session.getAttribute("loginUser");
		//3.2.1说明用户已登陆
		if(loginInfo!=null) {
			//直接通过
			filterChain.doFilter(req, resp);
			message.append("已授权通过");
			return;
		}
		//3.2.2没登陆 ，要求重新登陆
		session.setAttribute("message", "请先登陆！");
		message.append("无授权，重定向到登陆界面");
		resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
		}catch(ServletException e) {
			throw e;
		}finally {
			LOG.trace(message);
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		String whiteListStr=cfg.getInitParameter("white-list");
		String[] whiteListArray = whiteListStr.split("\\s+");
		whiteListSet.addAll(Arrays.asList(whiteListArray));
		
		LOG.debug("加载白名单："+whiteListSet.toString());
	}
	@Override
	public void destroy() {
		
	}
}
