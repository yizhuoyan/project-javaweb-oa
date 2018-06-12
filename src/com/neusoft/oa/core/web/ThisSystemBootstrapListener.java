package com.neusoft.oa.core.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.neusoft.oa.core.AppConfig;

public class ThisSystemBootstrapListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent evt) {

	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		ServletContext sc = evt.getServletContext();
		this.loadConfigFile(sc);
	}

	private void loadConfigFile(ServletContext sc) {
		try {
			AppConfig.load();
		} catch (Exception e) {
			sc.log("加载配置文件异常", e);
		}
	}

}
