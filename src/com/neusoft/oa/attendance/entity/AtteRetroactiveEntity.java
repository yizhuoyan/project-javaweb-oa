package com.neusoft.oa.attendance.entity;

import java.util.Date;

public class AtteRetroactiveEntity {
	private String id;
	private String empId;
	private Date when;
	private Date retTime;
	private String retM;
	private String reason;
	
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
	public Date getRetTime() {
		return retTime;
	}
	public void setRetTime(Date retTime) {
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
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private int result;
	private String approver;
	private String approvetime;
	private String remark;

}
