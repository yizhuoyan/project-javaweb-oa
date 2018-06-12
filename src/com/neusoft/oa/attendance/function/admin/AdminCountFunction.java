/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.function.admin;

import java.util.List;

import com.neusoft.oa.attendance.entity.AdminCountEntity;


public interface AdminCountFunction {

	List<AdminCountEntity> loadAllCount()throws Exception;

	
}
