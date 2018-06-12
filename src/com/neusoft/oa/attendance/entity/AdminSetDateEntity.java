/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminSetDateEntity {
	private String id;
	private LocalDate whenDay;
	private String workDay;
	private LocalTime onDuty; 
	private LocalTime offDuty; 
	private LocalTime signInStart;  
	private LocalTime signInEnd; 
	private LocalTime signOutStart; 
	private LocalTime signOutEnd; 
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getWhenDay() {
		return whenDay;
	}
	public void setWhenDay(LocalDate whenDay) {
		this.whenDay = whenDay;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public LocalTime getOnDuty() {
		return onDuty;
	}
	public void setOnDuty(LocalTime onDuty) {
		this.onDuty = onDuty;
	}
	public LocalTime getOffDuty() {
		return offDuty;
	}
	public void setOffDuty(LocalTime offDuty) {
		this.offDuty = offDuty;
	}
	public LocalTime getSignInStart() {
		return signInStart;
	}
	public void setSignInStart(LocalTime signInStart) {
		this.signInStart = signInStart;
	}
	public LocalTime getSignInEnd() {
		return signInEnd;
	}
	public void setSignInEnd(LocalTime signInEnd) {
		this.signInEnd = signInEnd;
	}
	public LocalTime getSignOutStart() {
		return signOutStart;
	}
	public void setSignOutStart(LocalTime signOutStart) {
		this.signOutStart = signOutStart;
	}
	public LocalTime getSignOutEnd() {
		return signOutEnd;
	}
	public void setSignOutEnd(LocalTime signOutEnd) {
		this.signOutEnd = signOutEnd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
