package com.neusoft.oa.attendance.entity;


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
	public LocalTime getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(LocalTime signinTime) {
		this.signinTime = signinTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	
	
	
}
