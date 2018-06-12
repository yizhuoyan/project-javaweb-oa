/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.ao;

import java.util.Date;

public class AdminSetDateAo {
	private String id;
	private String when;
	private String whenStart;
	private String whenEnd;
	private String workDay;
	private String onDuty;
	private String offDuty;
	private String signInStart;
	private String signInEnd;
	private String signOutStart;
	private String signOutEnd;
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}
	
	public String getWhenStart() {
		return whenStart;
	}
	public void setWhenStart(String whenStart) {
		this.whenStart = whenStart;
	}
	public String getWhenEnd() {
		return whenEnd;
	}
	public void setWhenEnd(String whenEnd) {
		this.whenEnd = whenEnd;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getOnDuty() {
		return onDuty;
	}
	public void setOnDuty(String onDuty) {
		this.onDuty = onDuty;
	}
	public String getOffDuty() {
		return offDuty;
	}
	public void setOffDuty(String offDuty) {
		this.offDuty = offDuty;
	}
	public String getSignInStart() {
		return signInStart;
	}
	public void setSignInStart(String signInStart) {
		this.signInStart = signInStart;
	}
	public String getSignInEnd() {
		return signInEnd;
	}
	public void setSignInEnd(String signInEnd) {
		this.signInEnd = signInEnd;
	}
	public String getSignOutStart() {
		return signOutStart;
	}
	public void setSignOutStart(String signOutStart) {
		this.signOutStart = signOutStart;
	}
	public String getSignOutEnd() {
		return signOutEnd;
	}
	public void setSignOutEnd(String signOutEnd) {
		this.signOutEnd = signOutEnd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
