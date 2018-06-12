package com.neusoft.oa.attendance.dao;

import java.util.List;


import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface DeptAttendanceDao extends TemplateDao<AttendanceEntity> {
	int selectsAttedance(String departmentId,String key,int pageNo,int pageSize,List<AttendanceEntity> pageData)throws Exception;
	String selectsDepartmentId(String managerid)throws Exception;
	String selectEmpName() throws Exception;
}
