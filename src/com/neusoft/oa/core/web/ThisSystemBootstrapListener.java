package com.neusoft.oa.core.web;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ThisSystemBootstrapListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		ServletContext sc=evt.getServletContext();
		this.loadConfigFile(sc);
	}
	private void loadConfigFile(ServletContext sc) {
		final String path="/app.config";
		try {
			Reader reader=new InputStreamReader(this.getClass().getResourceAsStream(path), "utf-8");
			System.getProperties().load(reader);
			sc.log("加载配置文件【"+path+"】成功！");
		}catch(Exception e) {
			sc.log("加载配置文件失败", e);
		}
	}

}
