package com.neusoft.oa.attendance.dao;

import java.util.List;

import com.neusoft.oa.attendance.entity.AtteVacateEntity;

public interface ManagerAtteVacateDao {
	String selectsDepartmentId(String managerid)throws Exception;
	int selectsAtteVacate(String departmentId,String key,int pageNo,int pageSize,List<AtteVacateEntity> pageData)throws Exception;
	
}
