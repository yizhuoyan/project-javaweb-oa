package com.neusoft.oa.system.web.servlet.modulemanage.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

@WebServlet("/api/system/module/listRoot")
public class ListRootModuleServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		// 2调用业务方法
		AdministratorFunction fun = FunctionFactory.getFunction(AdministratorFunction.class);
		List<SysModuleEntity> result = fun.loadRootModules();
		return result.stream().map(SysModuleEntity::toVo).collect(Collectors.toList());
	}
}
