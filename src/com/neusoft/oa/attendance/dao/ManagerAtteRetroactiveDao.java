package com.neusoft.oa.attendance.dao;

import java.util.List;

import com.neusoft.oa.attendance.entity.AtteRetroactiveEntity;

public interface ManagerAtteRetroactiveDao {
	String selectsDepartmentId(String managerid)throws Exception;
	int selectsAtteRetroactive(String departmentid,String key,int pageNo,int pageSize,List<AtteRetroactiveEntity> pageData)throws Exception;
}
