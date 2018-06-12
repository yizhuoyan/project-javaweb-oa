package com.neusoft.oa.attendance.function.employess.impl;

import java.util.List;


import com.neusoft.oa.attendance.dao.RetroactiveDao;
import com.neusoft.oa.attendance.entity.RetroactiveEntity;
import com.neusoft.oa.attendance.function.employess.StatisticalRetroactiveFunction;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.organization.entity.EmployeeEntity;


/**
 * @author 田梦源
 *
 */

public class StatisticalRetroactiveFunctionImpl implements StatisticalRetroactiveFunction {

	
	/**
	 * 	把当天所有未打卡的员工信息放入补签表中
	 * 	
	 */
	@Override
	public void statisticalRetroactive() throws Exception {
		RetroactiveDao dao=DaoFactory.getDao(RetroactiveDao.class);	
		//1.找到当天所有上午未打卡的员工
		//1.1获得当前日期
		//1.2通过日期和是否有效标识查询出未打卡的员工
		
		List<EmployeeEntity> empAm= dao.selectsAm();
		
		//1.找到当天所有下午未打卡的员工
		//1.1获得当前日期
		//1.2通过日期和是否有效标识查询出未打卡的员工
		
		List<EmployeeEntity> empPm=dao.selectsPm();	
				
		//2.把未打卡的的员工信息放入补签表
		//2.1 通过日期和员工id
		RetroactiveEntity t=new RetroactiveEntity();
		t.setRetM(0);
		if(empAm!=null) {
			EmployeeEntity e=new EmployeeEntity();
			for(EmployeeEntity example : empAm){
				t.setId(DBUtil.uuid());
		       e.setId(example.getId());		  
		       t.setEmp(e);
		       dao.insert(t);
			  }
		}
		if(empPm!=null) {
			EmployeeEntity e=new EmployeeEntity();
			for(EmployeeEntity example : empAm){
				t.setId(DBUtil.uuid());
				e.setId(example.getId());
			    t.setEmp(e);
		        dao.insert(t);
		    }
		}
	}
}
