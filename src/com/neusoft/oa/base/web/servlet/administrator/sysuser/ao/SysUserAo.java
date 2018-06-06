package com.neusoft.oa.base.web.servlet.administrator.sysuser.ao;

import java.util.Date;

public class SysUserAo {
	/**账号*/
	private String account;
	/**名称*/
	private String name;
	/**电话*/
	private String phone;
	/**密保邮箱*/
	private String securityEmail;
	/**头像路径或名称*/
	private String avatar;
	/**备注*/
	private String remark;
	
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSecurityEmail() {
		return securityEmail;
	}
	public void setSecurityEmail(String securityEmail) {
		this.securityEmail = securityEmail;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
