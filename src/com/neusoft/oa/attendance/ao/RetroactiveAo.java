package com.neusoft.oa.attendance.ao;

/**
 * @author 田梦源
 *
 */

public class RetroactiveAo {
	private String  empId;
	private String  retTime;
	private String  retM;
	private String  reason;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRetTime() {
		return retTime;
	}
	public void setRetTime(String retTime) {
		this.retTime = retTime;
	}
	public String getRetM() {
		return retM;
	}
	public void setRetM(String retM) {
		this.retM = retM;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
