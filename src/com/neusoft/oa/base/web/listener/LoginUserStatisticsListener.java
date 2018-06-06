package com.neusoft.oa.base.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LoginUserStatisticsListener implements HttpSessionListener,HttpSessionAttributeListener {
	private static final String APPLICATION_KEY_LOGIN_USER_COUNT = "loginUserCount";

	@Override
	public void attributeAdded(HttpSessionBindingEvent evt) {
		//有用户登陆
		if("loginUser".equals(evt.getName())) {
			ServletContext servletContext=evt.getSession().getServletContext();
			Integer loginUserCount=(Integer)servletContext.getAttribute(APPLICATION_KEY_LOGIN_USER_COUNT);
			if(loginUserCount==null) {
				servletContext.setAttribute(APPLICATION_KEY_LOGIN_USER_COUNT, 1);
			}else {
				servletContext.setAttribute(APPLICATION_KEY_LOGIN_USER_COUNT, loginUserCount+1);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent evt) {
		//有用户登陆
		if("loginUser".equals(evt.getName())) {
			ServletContext servletContext=evt.getSession().getServletContext();
			//有用户注销
			Integer count=(Integer)servletContext.getAttribute(APPLICATION_KEY_LOGIN_USER_COUNT);
			servletContext.setAttribute(APPLICATION_KEY_LOGIN_USER_COUNT, count-1);
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent evt) {
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		
	}

}
