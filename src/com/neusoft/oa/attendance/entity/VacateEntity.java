package com.neusoft.oa.attendance.entity;


import java.util.Date;

import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class VacateEntity {
	private String id;
	private EmployeeEntity emp;
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
	@Override
	public String toString() {
		return "VacateEntity [id=" + id + ", emp=" + emp + ", when=" + when + ", starttime=" + starttime + ", startM="
				+ startM + ", endtime=" + endtime + ", endM=" + endM + ", type=" + type + ", reason=" + reason
				+ ", result=" + result + ", approver=" + approver + ", approvetime=" + approvetime + ", remark="
				+ remark + "]";
	}

	

}
