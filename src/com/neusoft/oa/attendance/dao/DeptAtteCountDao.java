package com.neusoft.oa.attendance.dao;

import java.util.List;

import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface DeptAtteCountDao extends TemplateDao<AtteCountEntity> {
	void updateAtteCount(String empId,int lie)throws Exception;
	List<AtteCountEntity> selectsAttetardy(List<AtteCountEntity> totalData)throws Exception;
	List<AtteCountEntity> selectsAtteretroactive(List<AtteCountEntity> totalData)throws Exception;
	List<AtteCountEntity> selectsAttelate(List<AtteCountEntity> totalData)throws Exception;
	List<AtteCountEntity> selectsAtteEvection(List<AtteCountEntity> totalData)throws Exception;	
	List<AtteCountEntity> selectsAtteVacate(List<AtteCountEntity> totalData)throws Exception;
	
}
