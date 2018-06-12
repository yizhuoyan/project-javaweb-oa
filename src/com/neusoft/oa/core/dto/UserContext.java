package com.neusoft.oa.core.dto;

import java.util.Date;

/**
 * 用户上下文
 * 包含用户的信息
 * @author yizhuoyan@hotmail.com
 *
 */
public class UserContext {
	private String id;
	private String account;
	private String name;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Date lastModPasswordTime;
	
	private String jsessionId;
	/**密保邮箱*/
	private String securityEmail;
	/**头像路径或名称*/
	private String avatar;
	
	public Date getLastModPasswordTime() {
		return lastModPasswordTime;
	}
	public void setLastModPasswordTime(Date lastModPasswordTime) {
		this.lastModPasswordTime = lastModPasswordTime;
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
	public String getJsessionId() {
		return jsessionId;
	}
	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
