/**
 * 
 */
package com.neusoft.oa.core.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.util.JsonUtil;

/**
 * @author 易拙言
 *
 */
public abstract class CommonServlet extends HttpServlet {
	protected UserContext getCurrentUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserContext uc = (UserContext) session.getAttribute("loginUser");
		return uc;
	}
	protected void putSessionAttribute(HttpServletRequest req,String key,Object data) {
		HttpSession session=req.getSession();
		session.setAttribute(key, data);
	}
	
	@SuppressWarnings("unchecked")
	protected <T>T getSessionAttribute(HttpServletRequest req,String key) {
		HttpSession session=req.getSession();
		return (T)session.getAttribute(key);
	}
	
	protected String getCurrentUserId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserContext uc = (UserContext) session.getAttribute("loginUser");
		if (uc != null) {
			return uc.getId();
		}
		return null;
	}
	

	protected boolean isLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object uc = session.getAttribute("loginUser");
		return uc != null;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		if (isAjaxRequest(req)) {
			handleAjaxRequest(req, resp);
		} else {
			handleNormalRequest(req, resp);
		}
	}
	
	private boolean isAjaxRequest(HttpServletRequest req) {
		String requestWith=req.getHeader("X-Requested-With");
		if(requestWith!=null) {
			if("XMLHTTPREQUEST".equals(requestWith.toUpperCase())) {
				return true;
			}
		}
		return req.getRequestURI().endsWith(".ajax");
	}

	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		return null;
	}

	private void handleAjaxRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AjaxResponse result = new AjaxResponse();
		try {
			Object respData = this.handleRequest(req, resp);
			
			if (respData != null) {
				if(respData instanceof AjaxResponse) {
					result=(AjaxResponse)respData;
				}else {
					result.setCode("ok");
					result.setData(respData);
				}
			}
		} catch (OAException e) {
			result.setCode("error");
			result.setMessage(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			result.setCode("fatal");
			result.setMessage("系统繁忙，请稍后再试!");
		} finally {
			resp.setContentType("application/json");
			// 设置允许跨域
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			// mapper.set
			PrintWriter out = resp.getWriter();
			out.println(JsonUtil.toJsonString(result));
			out.close();
		}
	}

	private void handleNormalRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Object result = this.handleRequest(req, resp);
			if (result == null)
				return;
			// 如果是字符串，则表示是页码跳转
			if (result instanceof String) {
				String path = (String) result;
				if (path.charAt(0) == '@') {
					// 重定向
					resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + path.substring(1)));
				} else {
					req.getRequestDispatcher(resp.encodeURL(path)).forward(req, resp);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}


}
