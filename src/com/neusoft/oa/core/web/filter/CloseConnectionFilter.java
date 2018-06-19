/**
 * 
 */
package com.neusoft.oa.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.neusoft.oa.core.dao.DBUtil;

/**
 * @author Administrator
 *
 */
@WebFilter({"/api/*","*.do"})
public class CloseConnectionFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(req, resp);
		}finally {
			//关闭数据库连接
			DBUtil.closeConnection();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
