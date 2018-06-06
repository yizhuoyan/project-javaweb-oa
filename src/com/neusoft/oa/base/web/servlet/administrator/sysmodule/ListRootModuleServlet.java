package com.neusoft.oa.base.web.servlet.administrator.sysmodule;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.AjaxResponse;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/sysmodule/listRoot.ajax")
public class ListRootModuleServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
				// 2调用业务方法
				try {
					AdministratorFunction fun=FunctionFactory.getFunction(AdministratorFunction.class);
					List<SysModuleEntity>  result=fun.loadRootModules();
					return AjaxResponse.ok(result.stream().map(SysModuleEntity::toJson).collect(Collectors.toList()));
				} catch (OAException e) {
					return AjaxResponse.fail(e);
				} 
		
	}
}
