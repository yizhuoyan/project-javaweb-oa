/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.function.admin.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.neusoft.oa.attendance.ao.AdminSetDateAo;
import com.neusoft.oa.attendance.dao.AdminSetDateDao;
import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.attendance.function.admin.AdminSetDateFunction;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;

import static com.neusoft.oa.core.util.AssertThrowUtil.*;

public class AdminSetDateFunctionImpl implements AdminSetDateFunction {

	@Override
	public void setDate(AdminSetDateAo ao) throws Exception {
		// TODO Auto-generated method stub
		LocalDate whenDayStart = string2LocalDate($("日期", ao.getWhenStart()));
		LocalDate whenDayEnd = string2LocalDate($("日期", ao.getWhenEnd()));
		String workDay = $("是否工作日", ao.getWorkDay());
		String remark = ao.getRemark();
		AdminSetDateDao dao = DaoFactory.getDao(AdminSetDateDao.class);
		List<LocalDate> list = getBetweenDate(whenDayStart, whenDayEnd);
		System.out.println(ao.getOnDuty());
		if ("是".equals(workDay)) {
			$("上班时间", ao.getOnDuty());
			$("下班时间", ao.getOffDuty());
			$("上班打卡开始时间", ao.getSignInStart());
			$("上班打卡结束时间", ao.getSignInEnd());
			$("下班打卡开始时间", ao.getSignOutStart());
			$("下班打卡结束时间", ao.getSignOutEnd());
		}
		LocalTime onDuty = string2LocalTime(ao.getOnDuty());
		LocalTime offDuty = string2LocalTime(ao.getOffDuty());
		LocalTime signInStart = string2LocalTime(ao.getSignInStart());
		LocalTime signInEnd = string2LocalTime(ao.getSignInEnd());
		LocalTime signOutStart = string2LocalTime(ao.getSignOutStart());
		LocalTime signOutEnd = string2LocalTime(ao.getSignOutEnd());

		for (LocalDate whenDay : list) {

			if (!dao.exist("when_day", whenDay)) {
				AdminSetDateEntity e = new AdminSetDateEntity();
				e.setId(DBUtil.uuid());
				e.setWhenDay(whenDay);
				e.setWorkDay(workDay);
				e.setOnDuty(onDuty);
				e.setOffDuty(offDuty);
				e.setSignInStart(signInStart);
				e.setSignInEnd(signInEnd);
				e.setSignOutStart(signOutStart);
				e.setSignOutEnd(signOutEnd);
				e.setRemark(remark);
				dao.insert(e);
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("work_day", workDay);
				map.put("on_duty", onDuty);
				map.put("off_duty", offDuty);
				map.put("sign_instart", signInStart);
				map.put("sign_inend", signInEnd);
				map.put("sign_outstart", signOutStart);
				map.put("sign_outend", signOutEnd);
				map.put("remark", remark);
				dao.updateTime("when_day", whenDay, map);
			}
		}
	}

	

	private static LocalTime string2LocalTime(String str) {
		if (str != null) {
			LocalTime localTime = LocalTime.parse(str, DateTimeFormatter.ofPattern("HH:mm"));
			return localTime;
		}
		return null;
	}

	public static LocalDate string2LocalDate(String s) {
		if (s != null) {
			LocalDate localDate = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return localDate;
		}
		return null;
	}

	


	public static List<LocalDate> getBetweenDate(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> list = new ArrayList<>();
		long distance = ChronoUnit.DAYS.between(startDate, endDate);
		if (distance < 1) {
			return list;
		}
		Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f));
		return list;
	}

	@Override
	public PaginationQueryResult<AdminSetDateEntity> listSetDate(String key, int pageNo, int pageSize)
			throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
		}
		List<AdminSetDateEntity> pageData = new ArrayList<>(pageSize);
		// 2执行业务逻辑
		AdminSetDateDao udao = DaoFactory.getDao(AdminSetDateDao.class);
		int total = udao.selectsByKey(key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<AdminSetDateEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		
		return result;
	}



	@Override
	public void modSetDate(AdminSetDateAo ao) throws Exception {
		// TODO Auto-generated method stub
		String id = ao.getId();
		String workDay = ao.getWorkDay();
		String remark = ao.getRemark();
		Map<String, Object> map = new HashMap<>();
		map.put("work_day", workDay);
		map.put("remark", remark);
		if ("是".equals(workDay)) {
			$("上班时间", ao.getOnDuty());
			$("下班时间", ao.getOffDuty());
			$("上班打卡开始时间", ao.getSignInStart());
			$("上班打卡结束时间", ao.getSignInEnd());
			$("下班打卡开始时间", ao.getSignOutStart());
			$("下班打卡结束时间", ao.getSignOutEnd());
			LocalTime onDuty = string2LocalTime(ao.getOnDuty());
			LocalTime offDuty = string2LocalTime(ao.getOffDuty());
			LocalTime signInStart = string2LocalTime(ao.getSignInStart());
			LocalTime signInEnd = string2LocalTime(ao.getSignInEnd());
			LocalTime signOutStart = string2LocalTime(ao.getSignOutStart());
			LocalTime signOutEnd = string2LocalTime(ao.getSignOutEnd());
			map.put("on_duty", onDuty);
			map.put("off_duty", offDuty);
			map.put("sign_instart", signInStart);
			map.put("sign_inend", signInEnd);
			map.put("sign_outstart", signOutStart);
			map.put("sign_outend", signOutEnd);
		}
		
		AdminSetDateDao dao = DaoFactory.getDao(AdminSetDateDao.class);
		dao.update(id, map);
	}



	@Override
	public AdminSetDateEntity loadSetDate(String id) throws Exception {
		// TODO Auto-generated method stub
		AdminSetDateDao dao = DaoFactory.getDao(AdminSetDateDao.class);
		AdminSetDateEntity e = dao.select("id", id);
		return e;
	}

}
