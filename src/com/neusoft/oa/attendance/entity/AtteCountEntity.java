package com.neusoft.oa.attendance.entity;

import com.neusoft.oa.organization.entity.EmployeeEntity;

public class AtteCountEntity {
	private EmployeeEntity emp;
	
	private int mouth;
	private int tardycount;
	private int latecount;
	private int retroactivecount;
	public int getRetroactivecount() {
		return retroactivecount;
	}
	public void setRetroactivecount(int retroactivecount) {
		this.retroactivecount = retroactivecount;
	}
	private int vacatecount;
	private int absenteeismcount;
	private int evectioncount;
	
	public int getAbsenteeismcount() {
		return absenteeismcount;
	}
	public void setAbsenteeismcount(int absenteeismcount) {
		this.absenteeismcount = absenteeismcount;
	}
	public int getEvectioncount() {
		return evectioncount;
	}
	public void setEvectioncount(int evectioncount) {
		this.evectioncount = evectioncount;
	}
	public EmployeeEntity getEmp() {
		return emp;
	}
	public void setEmp(EmployeeEntity emp) {
		this.emp = emp;
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
	
	public int getVacatecount() {
		return vacatecount;
	}
	public void setVacatecount(int vacatecount) {
		this.vacatecount = vacatecount;
	}
	
}
