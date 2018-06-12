package com.neusoft.oa.attendance.function.employess.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.*;

import java.util.Date;
import java.util.List;
import java.sql.Time;
import java.time.LocalTime;

import com.neusoft.oa.attendance.dao.AttendanceDao;
import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.attendance.function.employess.ClockInFunction;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class ClockInFunctionImpl implements ClockInFunction {

	@Override
	public void clockIn(String id, java.util.Date signInDate) throws Exception {
		String empid = $("id", id);
		if(signInDate==null) {
			throw new OAException("打卡时间不能为空!");
		}
		Date date1 = new Date(signInDate.getTime());
		LocalTime time = new Time(signInDate.getTime()).toLocalTime();
		// 执行业务逻辑
		AttendanceEntity entity=new AttendanceEntity();
		EmployeeEntity emp=new EmployeeEntity();
		emp.setId(empid);
		entity.setId(DBUtil.uuid());		
		entity.setEmp(emp);
		entity.setWhenDay(date1);
		entity.setSigninTime(time);
		if(time.getHour()<12) {
			entity.setAmpm(0);
		}else {
			entity.setAmpm(1);
		}
		entity.setIsvalid(1);
		AttendanceDao dao= DaoFactory.getDao(AttendanceDao.class);
		if(dao.selectsSignColumn(empid, 1)!=null) {
			dao.updateIsvalid(empid, 1);
		}
		dao.insert(entity);
	}

	@Override
	public List<AttendanceEntity> select2data(String id) throws Exception {
		String empid = $("id", id);
		AttendanceDao dao= DaoFactory.getDao(AttendanceDao.class);
		List<AttendanceEntity> result=dao.selectsByEmployee(empid);
		return result;
	}

}
