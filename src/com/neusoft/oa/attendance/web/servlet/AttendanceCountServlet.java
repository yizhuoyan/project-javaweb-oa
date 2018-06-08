package com.neusoft.oa.attendance.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.oa.attendance.dto.PageFindResult;
import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.attendance.function.DeptManagerFunction;
import com.neusoft.oa.attendance.function.QueryCountFunction;
import com.neusoft.oa.attendance.function.impl.DeptManagerFunctionImpl;
import com.neusoft.oa.attendance.function.impl.QueryCountFunctionImpl;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.core.web.CommonServlet;
@WebServlet("/attendance /personal.do")
public class AttendanceCountServlet extends CommonServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2562409390569926219L;

	protected Object handlerRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1获取参数
		String departmentId=req.getParameter("departmentId");
		int mouth=Integer.parseInt(req.getParameter("mouth"));
		String key=req.getParameter("key");
		String pageNo=req.getParameter("pageNo");
		String pageSize=req.getParameter("pageSize");
		
		int pageNoInt=ThisSystemUtil.parseInt(pageNo, -1);
		int pageSizeInt=ThisSystemUtil.parseInt(pageSize, -1);
	
		//2调用业务层方法获取业务结果
		DeptManagerFunction qa=new DeptManagerFunctionImpl();
		
		List<AtteCountEntity> result=qa.queryCounts(departmentId, mouth, key, pageNoInt, pageSizeInt);
		//3转换业务结果为dto
		
				List<AtteCountEntity> rows = result.getRows();
				List<ModuleFindDto> rowsUse=new ArrayList<>(rows.size());
				for (ModuleEntity m : rows) {
					rowsUse.add(ModuleFindDto.of(m));
		return result;
	}	
}
