package com.neusoft.oa.message.entity;

public class AddressBookEntity {
   private String name;
   private String phone;
   private String email;
   private String department;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
@Override
public String toString() {
	return "AddressBookEntity [name=" + name + ", phone=" + phone + ", email=" + email + ", department=" + department + "]";
}
   
}
