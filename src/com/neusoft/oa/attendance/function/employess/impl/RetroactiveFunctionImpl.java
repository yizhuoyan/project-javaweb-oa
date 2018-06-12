package com.neusoft.oa.attendance.function.employess.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.attendance.ao.RetroactiveAo;
import com.neusoft.oa.attendance.dao.RetroactiveDao;

import com.neusoft.oa.attendance.entity.RetroactiveEntity;
import com.neusoft.oa.attendance.function.employess.RetroactiveFunction;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


/**
 * @author 田梦源
 *
 */
public class RetroactiveFunctionImpl implements RetroactiveFunction{
	

	
	public static java.util.Date str2UtilDate(String strDate, String dateFormat) throws java.text.ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		java.util.Date date = null;
		try {
			date = sf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	@Override
	public PaginationQueryResult<RetroactiveEntity> listRetroactive(String id, String key, int pageNo, int pageSize)
			throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
		}

		List<RetroactiveEntity> pageData = new ArrayList<>(pageSize);
		// 2执行业务逻辑
		RetroactiveDao udao = DaoFactory.getDao(RetroactiveDao.class);
		int total = udao.selectsByKey(id,key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<RetroactiveEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
	}
	@Override
	public List<RetroactiveEntity> singleRetroactive(String id) throws Exception {
		String empId=$("员工id",id);
		RetroactiveDao dao=DaoFactory.getDao(RetroactiveDao.class);
		List<RetroactiveEntity> e=dao.selectAllByColumn("emp_id", empId, "apply_time");
		return e;
	}
	@Override
	public void retroactive(String id, String reason) throws Exception {
		RetroactiveDao dao=DaoFactory.getDao(RetroactiveDao.class);
		dao.updateById(id, reason);	
	}


}
