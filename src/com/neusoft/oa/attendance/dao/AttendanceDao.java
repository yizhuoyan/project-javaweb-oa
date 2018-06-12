package com.neusoft.oa.attendance.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.core.dao.TemplateDao;

/**
 * @author 田梦源
 *
 */

public interface AttendanceDao  extends TemplateDao<AttendanceEntity> {

	/**
	 * 通过用户id查找，用户当天打卡记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<AttendanceEntity> selectsByEmployee(String empid) throws Exception;
	/**
	 * 通过用户id,当前日期，标识，查找用户打卡记录
	 */
	AttendanceEntity selectsSignColumn(String empid,int isvalid ) throws Exception;
	/**
	 * 	 通过用户id,当前日期，标识，修改用户打卡标识
	 */
	void  updateIsvalid(String empid,int isvalid) throws Exception;
}
