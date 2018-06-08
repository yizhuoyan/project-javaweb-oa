package com.neusoft.oa.attendance.entity;

import java.sql.Date;

public class AtteVacateEntity {
	private String id;
	private String emp_id;
	private Date when;
	private Date starttime;
	private String start_m;
	private Date endtime;
	private String end_m;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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
	public String getStart_m() {
		return start_m;
	}
	public void setStart_m(String start_m) {
		this.start_m = start_m;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getEnd_m() {
		return end_m;
	}
	public void setEnd_m(String end_m) {
		this.end_m = end_m;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
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
	private String type;
	private String reason;
	private String result;
	private String approver;
	private String approvetime;
	private String remark;

}
