/**
 * 
 */
package com.neusoft.oa.common.web.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.oa.common.function.CommonFunction;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.WindowsInfoUtil;
import com.neusoft.oa.core.web.servlet.CommonServlet;

/**
 * @author 易拙言
 *
 */
@WebServlet("/system/statistics.do")
public class StatisticsServlet extends CommonServlet {

	protected AjaxResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String type = req.getParameter("type");
		if(type==null)type="all";
		
		switch (type) {
		case "online-users":// 当前在线用户数
			return AjaxResponse.ok(this.loadOnlineUserCount(req));	
		case "total-accounts":// 系统用户数
			return AjaxResponse.ok(this.loadAccountTotalCount(req));
		case "cpu-payload":// 服务器CPU负荷
			return AjaxResponse.ok(this.loadPlayloadOfCPU(req));
		case "memory-payload":// 服务器内存负荷
			return AjaxResponse.ok(this.loadPlayloadOfMemory(req));
		case "all":
			Map<String, Object> result = new HashMap<>();
			result.put("onlineUsers",this.loadOnlineUserCount(req));
			result.put("totalAccount",this.loadAccountTotalCount(req));
			result.put("cpuPayload",this.loadPlayloadOfCPU(req));
			result.put("memoryPayload",this.loadPlayloadOfMemory(req));
			
			return AjaxResponse.ok(result);
		}
		return AjaxResponse.fail("无法识别此类型");
	}

	/**
	 * 获取在线用户数
	 * 
	 * @return
	 */
	private int loadOnlineUserCount(HttpServletRequest req) throws Exception {
		Integer count = (Integer) this.getServletContext().getAttribute("loginUserCount");
		if (count == null)
			return 0;
		return count.intValue();
	}

	private int loadAccountTotalCount(HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			Integer count = (Integer) session.getAttribute("TOTAL-ACCOUNT");
			if (count == null) {
				CommonFunction fun = FunctionFactory.getFunction(CommonFunction.class);
				count = fun.loadTotalAccount();
				session.setAttribute("TOTAL-ACCOUNT", count);
			}
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private double loadPlayloadOfCPU(HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			Double value = (Double) session.getAttribute("PLAYLOAD-CPU");
			if (value == null) {
				value=WindowsInfoUtil.getCpuRatioForWindows();
				session.setAttribute("PLAYLOAD-CPU", value);
			}
			return value;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	private double loadPlayloadOfMemory(HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			Double value = (Double) session.getAttribute("PLAYLOAD-CPU");
			if (value == null) {
				value=WindowsInfoUtil.getMemery();
				session.setAttribute("PLAYLOAD-CPU", value);
			}
			return value;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
