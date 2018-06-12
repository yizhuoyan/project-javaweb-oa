/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.function.admin;

import java.util.List;

import com.neusoft.oa.attendance.ao.AdminSetDateAo;
import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.core.dto.PaginationQueryResult;

public interface AdminSetDateFunction {
	void setDate(AdminSetDateAo ao) throws Exception;
	PaginationQueryResult<AdminSetDateEntity> listSetDate(String key, int pageNoInt, int pageSizeInt)throws Exception;
	void modSetDate(AdminSetDateAo ao)throws Exception;
	AdminSetDateEntity loadSetDate(String id) throws Exception;
}
