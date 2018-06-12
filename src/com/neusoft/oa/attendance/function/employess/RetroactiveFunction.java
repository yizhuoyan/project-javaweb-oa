package com.neusoft.oa.attendance.function.employess;

import java.util.List;


import com.neusoft.oa.attendance.entity.RetroactiveEntity;
import com.neusoft.oa.core.dto.PaginationQueryResult;


/**
 * @author 田梦源
 *
 */
public interface RetroactiveFunction {
	void retroactive(String id ,String reason) throws Exception;
	List<RetroactiveEntity> singleRetroactive(String id) throws Exception;
	PaginationQueryResult<RetroactiveEntity> listRetroactive(String id,String key,int pageNo,int pageSize)throws Exception;
	
}
