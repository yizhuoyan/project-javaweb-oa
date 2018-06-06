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
	private int age;//年龄
	private boolean male;//性别
	private String address;//住址
	private String email;//邮箱
	private String homePhone; //       家庭电话
	private Instant hiredate;//入职时间
	private String workPhone;//        办公电话
	private DepartmentEntity department;// 部门
	private String nation;//民族
	private String nativePlace; //籍贯
	private String domicilePlace;//户口所在地
	private LocalDate birthday;//出生年月
	private String politicalStatus;//政治面貌
	private String idcard;//身份证号码
	private MarriageState marriageState ;//婚姻状况
	
	
	@Override
	public String toString() {
		return "EmployeeEntity [age=" + age + ", male=" + male + ", address=" + address + ", email=" + email
				+ ", homePhone=" + homePhone + ", hiredate=" + hiredate + ", workPhone=" + workPhone + ", department="
				+ department + ", nation=" + nation + ", nativePlace=" + nativePlace + ", domicilePlace="
				+ domicilePlace + ", birthday=" + birthday + ", politicalStatus=" + politicalStatus + ", idcard="
				+ idcard + ", marriageState=" + marriageState + "]";
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
	public Instant getHiredate() {
		return hiredate;
	}
	public void setHiredate(Instant hiredate) {
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
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public MarriageState getMarriageState() {
		return marriageState;
	}
	public void setMarriageState(MarriageState marriageState) {
		this.marriageState = marriageState;
	}
	
	
	
}
