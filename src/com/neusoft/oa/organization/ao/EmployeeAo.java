package com.neusoft.oa.organization.ao;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.MarriageState;
/**
 * 员工参数对象
 * @author yizhouyan
 *
 */
public class EmployeeAo {
	
	private String departmentId;// 部门
	private String hiredate;// 入职时间
	/** 账号 */
	private String account;
	/** 名称 */
	private String name;
	//备注
	private String remark;
	private String idcard;// 身份证号码
	private String nation;// 民族
	private String nativePlace; // 籍贯
	private String domicilePlace;// 户口所在地
	private String politicalStatus;// 政治面貌
	private String marriageState;// 婚姻状况
	
	
	
	private String homePhone; // 家庭电话
	private String workPhone;// 办公电话
	private String email;// 邮箱
	private String address;// 住址
	
	
	
	
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getDomicilePlace() {
		return domicilePlace;
	}
	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getMarriageState() {
		return marriageState;
	}
	public void setMarriageState(String marriageState) {
		this.marriageState = marriageState;
	}
	

}
