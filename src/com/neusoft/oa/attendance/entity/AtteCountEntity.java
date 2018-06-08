package com.neusoft.oa.attendance.entity;

public class AtteCountEntity {
	private String empId;
	private int mouth;
	private int tardycount;
	private int latecount;
	private int retroactioncount;
	private int vacatecount;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getMouth() {
		return mouth;
	}
	public void setMouth(int mouth) {
		this.mouth = mouth;
	}
	public int getTardycount() {
		return tardycount;
	}
	public void setTardycount(int tardycount) {
		this.tardycount = tardycount;
	}
	public int getLatecount() {
		return latecount;
	}
	public void setLatecount(int latecount) {
		this.latecount = latecount;
	}
	public int getRetroactioncount() {
		return retroactioncount;
	}
	public void setRetroactioncount(int retroactioncount) {
		this.retroactioncount = retroactioncount;
	}
	public int getVacatecount() {
		return vacatecount;
	}
	public void setVacatecount(int vacatecount) {
		this.vacatecount = vacatecount;
	}
	
}
