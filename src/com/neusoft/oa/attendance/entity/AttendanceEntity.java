package com.neusoft.oa.attendance.entity;

import java.sql.Date;

public class AttendanceEntity {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getSignIn() {
		return signIn;
	}
	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}
	public String getResultAm() {
		return resultAm;
	}
	public void setResultAm(String resultAm) {
		this.resultAm = resultAm;
	}
	public String getSignOut() {
		return signOut;
	}
	public void setSignOut(String signOut) {
		this.signOut = signOut;
	}
	public String getResultPm() {
		return resultPm;
	}
	public void setResultPm(String resultPm) {
		this.resultPm = resultPm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String empId;
	private Date when;
	private String signIn;
	private String resultAm;
	private String signOut;
	private String resultPm;
	private String remark;
	
}
