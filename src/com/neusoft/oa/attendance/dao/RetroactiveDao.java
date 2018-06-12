package com.neusoft.oa.attendance.dao;

import java.util.List;

import com.neusoft.oa.attendance.entity.RetroactiveEntity;
import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public interface RetroactiveDao extends TemplateDao<RetroactiveEntity>{
	int selectsByKey(String id,String key,int pageNo,int pageSize,List<RetroactiveEntity> pageData)throws Exception;
	/**
	 * 当天所有上午未打卡的员工
	 */
	List<EmployeeEntity> selectsAm() throws Exception;
	/**
	 * 当天所有下午未打卡的员工
	 */
	List<EmployeeEntity> selectsPm() throws Exception;
	/**
	 * 通过数据id，申请补签
	 */
	void updateById(String id,String reason) throws Exception;
}
