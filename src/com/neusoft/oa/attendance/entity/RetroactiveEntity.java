package com.neusoft.oa.attendance.entity;

import java.util.Date;

import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class RetroactiveEntity {
	private String id;
	private EmployeeEntity emp;
	private Date when;
	private Date retTime;
	private int retM;
	private String reason;
	private int result;
	private String approver;
	private Date approvetime;
	private String remark;
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
	
	public int getRetM() {
		return retM;
	}
	public void setRetM(int retM) {
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
	public Date getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	

}
