package com.neusoft.oa.document.web.documentManage;

import java.time.Instant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.util.ArgumentObjectUtil;
import com.neusoft.oa.core.web.servlet.CommonServlet;
import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.DocumentFunction;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

@WebServlet("/documentManage/add.ajax")
public class AddServlet extends CommonServlet {

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {

		// 1获取参数
		DocumentAo ao=ArgumentObjectUtil.parse(req, DocumentAo.class);
		
		// 2调用业务层方法
		//获取当前用户id
		
		String userId=getCurrentUserId(req);
		
		DocumentFunction fun=FunctionFactory.getFunction(DocumentFunction.class);
		DocumentEntity doc=fun.addDocument(userId, ao);
		// 3返回业务结果
		return doc;
	}
}
