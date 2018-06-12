package com.neusoft.oa.attendance.function.employess;


import com.neusoft.oa.attendance.ao.VacateAo;
import com.neusoft.oa.attendance.entity.VacateEntity;
import com.neusoft.oa.core.dto.PaginationQueryResult;

/**
 * @author 田梦源
 *
 */


public interface VacateFunction {
	void vacate(VacateAo ao) throws Exception;
	PaginationQueryResult<VacateEntity> listVacate(String id,String key,int pageNo,int pageSize)throws Exception;
}
