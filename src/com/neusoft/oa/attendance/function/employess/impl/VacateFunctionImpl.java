package com.neusoft.oa.attendance.function.employess.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.neusoft.oa.attendance.ao.VacateAo;
import com.neusoft.oa.attendance.dao.VacateDao;
import com.neusoft.oa.attendance.entity.VacateEntity;
import com.neusoft.oa.attendance.function.employess.VacateFunction;
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

public class VacateFunctionImpl implements VacateFunction {

	@Override
	public void vacate(VacateAo ao) throws Exception {
		//验证参数
		String empId = $("员工id", ao.getEmpId());
		String starttime=$("请假开始时间",ao.getStarttime());
		String startM=$("请假开始上下午",ao.getStartM());
		String endtime=$("请假结束时间",ao.getEndtime());
		String endM=$("请假结束上下午",ao.getEndM());
		String vacateType=$("请假类型",ao.getType());		
		String reason=$("请假原因",ao.getReason());
		int result=0;
		// 2 执行业务逻辑
		//2.1存入数据库
		VacateDao dao=DaoFactory.getDao(VacateDao.class);
		VacateEntity t=new VacateEntity();
		EmployeeEntity u=new EmployeeEntity();
		u.setId(empId);
		t.setEmp(u);
		t.setId(DBUtil.uuid());
		t.setStarttime(str2UtilDate(starttime, "yyyy-MM-dd"));
		t.setStartM(startM);
		t.setEndtime(str2UtilDate(endtime, "yyyy-MM-dd"));
		t.setEndM(endM);
		t.setType(vacateType);
		t.setReason(reason);
		t.setResult(result);
		dao.insert(t);
	}
	
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
	public PaginationQueryResult<VacateEntity> listVacate(String id, String key, int pageNo, int pageSize) throws Exception {
		// 1清理验证参数
				key = ThisSystemUtil.trim(key);
				if (pageNo <= 0) {
					pageNo = 1;
				}
				if (pageSize <= 0) {
					pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
				}

				List<VacateEntity> pageData = new ArrayList<>(pageSize);
				// 2执行业务逻辑
				VacateDao udao = DaoFactory.getDao(VacateDao.class);
				int total = udao.selectsByKey(id,key, pageNo, pageSize, pageData);
				// 3组装业务结果
				PaginationQueryResult<VacateEntity> result = new PaginationQueryResult<>();
				result.setTotalRows(total);
				result.setRows(pageData);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				return result;
	}
	
}
