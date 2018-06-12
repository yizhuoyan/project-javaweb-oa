package com.neusoft.oa.attendance.entity;

import java.util.Date;

public class AtteVacateEntity {
	private String id;
	private String empId;
	private Date when;
	private Date starttime;
	private String startM;
	private Date endtime;
	private String endM;	
	private String type;
	private String reason;
	private int result;
	private String approver;
	private Date approvetime;
	public Date getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}
	private String remark;
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
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getStartM() {
		return startM;
	}
	public void setStartM(String startM) {
		this.startM = startM;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getEndM() {
		return endM;
	}
	public void setEndM(String endM) {
		this.endM = endM;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
