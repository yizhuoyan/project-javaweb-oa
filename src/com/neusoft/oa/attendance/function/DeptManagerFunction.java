package com.neusoft.oa.attendance.function;

import java.util.List;

import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.attendance.entity.AtteRetroactiveEntity;
import com.neusoft.oa.attendance.entity.AtteVacateEntity;
import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.core.dto.PaginationQueryResult;

/**
 * 部门经理拥有的功能
 * 
 * @author Administrator
 *
 */
public interface DeptManagerFunction {
	/**
	 * 查看本部门当月员工考勤记录
	 * 
	 * @param managerId
	 *            部门经理id
	 * @param mouth
	 *            月份
	 * @param key
	 *            关键字
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            一页行数
	 * @return
	 * @throws Exception
	 */
	PaginationQueryResult<AtteCountEntity> queryCounts(String managerid, String key, int pageNo, int pageSize)
			throws Exception;

	/**
	 * 看本部门员工当月请假记录
	 * 
	 * @param managerId
	 *            部门经理id
	 * @param key
	 *            关键字
	 * @param mouth
	 *            月份
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            一页行数
	 * @return
	 * @throws Exception
	 */
	PaginationQueryResult<AtteVacateEntity> queryVacate(String managerid, String key, int pageNo, int pageSize)
			throws Exception;

	/**
	 * 查看本部门员工请假记录
	 * 
	 * @param managerId
	 *            部门经理id
	 * @param key
	 *            关键字
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            一页行数
	 * @return
	 * @throws Exception
	 */
	PaginationQueryResult<AtteRetroactiveEntity> queryRetroactive(String managerid, String key, int pageNo,
			int pageSize) throws Exception;

	/**
	 * 
	 * @param managerid  部门经理id
	 * @param key 关键字
	 * @param pageNo 页码
	 * @param pageSize 一页行数
	 * @return
	 * @throws Exception
	 */
	PaginationQueryResult<AttendanceEntity> queryAttendance(String managerid, String key, int pageNo, int pageSize)
			throws Exception;
	void addAtteCounts()throws Exception;
			
}
