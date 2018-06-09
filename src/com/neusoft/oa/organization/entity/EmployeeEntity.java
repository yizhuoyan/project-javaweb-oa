package com.neusoft.oa.organization.entity;

import java.time.Instant;
import java.time.LocalDate;

import com.neusoft.oa.base.entity.SysUserEntity;

/**
 * 
 * @author yizhouyan
 *
 */

public class EmployeeEntity extends SysUserEntity {
	private String idcard;//身份证号码
	private int age;//年龄
	private boolean male;//性别
	private String address;//住址
	private String workEmail;//邮箱
	private String homePhone; //       家庭电话
	private LocalDate hiredate;//入职日期
	private String workPhone;//        办公电话
	private DepartmentEntity department;// 部门
	private String nativePlace; //籍贯
	private String domicilePlace;//户口所在地
	private LocalDate birthday;//出生年月
	private int nationality;//民族
	private int politicalStatus;//政治面貌
	private int marriageState ;//婚姻状况
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public LocalDate getHiredate() {
		return hiredate;
	}
	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public DepartmentEntity getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentEntity department) {
		this.department = department;
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
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public int getNationality() {
		return nationality;
	}
	public void setNationality(int nationality) {
		this.nationality = nationality;
	}
	public int getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(int politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public int getMarriageState() {
		return marriageState;
	}
	public void setMarriageState(int marriageState) {
		this.marriageState = marriageState;
	}
	
}
