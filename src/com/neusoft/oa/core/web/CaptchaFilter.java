package com.neusoft.oa.core.web;

import java.io.IOException;
import java.util.Objects;

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

public class CaptchaFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(CaptchaFilter.class);
	private static final String CAPTCHA_SESSION_KEY = "CURRENT-CAPTCHA";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		StringBuilder message = new StringBuilder();
		try {
			HttpServletRequest req = (HttpServletRequest) servletRequest;
			HttpServletResponse resp = (HttpServletResponse) servletResponse;
			// 1判断session中是否有验证码
			HttpSession session = req.getSession(false);
			do {
				if (session == null) {// 无session非法访问
					handleErrorCaptcha("非法访问", req, resp);
					break;
				}
				String captchaCode = (String) session.getAttribute(CAPTCHA_SESSION_KEY);
				if (captchaCode == null) {
					handleErrorCaptcha("非法访问", req, resp);
					break;
				}
				// 判断验证码是否正确
				String userCaptchaCode = req.getParameter("captcha");
				if (userCaptchaCode == null || (userCaptchaCode = userCaptchaCode.trim()).length() == 0) {
					handleErrorCaptcha("验证码必须填写", req, resp);
					break;
				}
				if (!Objects.equals(userCaptchaCode, captchaCode)) {
					handleErrorCaptcha("验证码错误", req, resp);
					break;
				}
			} while (false);
			// 相等，通过,删除验证码
			session.removeAttribute(CAPTCHA_SESSION_KEY);
			filterChain.doFilter(req, resp);
		} catch (ServletException e) {
			throw e;
		} finally {
			// LOG.trace(message);
		}
	}

	private void handleErrorCaptcha(String message, HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("captchaError", message);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {

	}

}
