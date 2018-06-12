package com.neusoft.oa.attendance.function.employess;

import java.util.Date;
import java.util.List;
import com.neusoft.oa.attendance.entity.AttendanceEntity;

/**
 * @author 田梦源
 *
 */
public interface ClockInFunction {
	void clockIn(String id,Date signInDate) throws Exception;
	List<AttendanceEntity> select2data(String id)throws Exception;
}
