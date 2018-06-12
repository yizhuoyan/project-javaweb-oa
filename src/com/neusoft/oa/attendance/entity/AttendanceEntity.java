package com.neusoft.oa.attendance.entity;

import java.sql.Date;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class AttendanceEntity {
	private String id;	
	private EmployeeEntity emp;
	private Date whenDay;
	private Date signinTime;	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public EmployeeEntity getEmp() {
		return emp;
	}
	public void setEmp(EmployeeEntity emp) {
		this.emp = emp;
	}
	public Date getWhenDay() {
		return whenDay;
	}
	public void setWhenDay(Date whenDay) {
		this.whenDay = whenDay;
	}
	public Date getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(Date signinTime) {
		this.signinTime = signinTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String remark;
	
}
