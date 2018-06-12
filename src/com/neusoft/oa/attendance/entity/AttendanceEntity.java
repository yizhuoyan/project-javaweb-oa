package com.neusoft.oa.attendance.entity;

<<<<<<< HEAD
import java.sql.Date;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class AttendanceEntity {
	private String id;	
	private EmployeeEntity emp;
	private Date whenDay;
	private Date signinTime;	
=======

import java.time.LocalTime;
import java.util.Date;


import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class AttendanceEntity {
	private String id;
	private EmployeeEntity emp;
	private Date whenDay;
	private LocalTime signinTime;
	private String remark;
	private int isvalid;
	private int ampm;
	
>>>>>>> 9e9b7621c8d4a7813daf057771910caeed2250ed
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 9e9b7621c8d4a7813daf057771910caeed2250ed
	public EmployeeEntity getEmp() {
		return emp;
	}
	public void setEmp(EmployeeEntity emp) {
		this.emp = emp;
	}
<<<<<<< HEAD
=======
	
	
>>>>>>> 9e9b7621c8d4a7813daf057771910caeed2250ed
	public Date getWhenDay() {
		return whenDay;
	}
	public void setWhenDay(Date whenDay) {
		this.whenDay = whenDay;
	}
<<<<<<< HEAD
	public Date getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(Date signinTime) {
=======
	public LocalTime getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(LocalTime signinTime) {
>>>>>>> 9e9b7621c8d4a7813daf057771910caeed2250ed
		this.signinTime = signinTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
<<<<<<< HEAD
	private String remark;
=======
	public int getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(int isvalid) {
		this.isvalid = isvalid;
	}
	public int getAmpm() {
		return ampm;
	}
	public void setAmpm(int ampm) {
		this.ampm = ampm;
	}	
	
	
>>>>>>> 9e9b7621c8d4a7813daf057771910caeed2250ed
	
}
