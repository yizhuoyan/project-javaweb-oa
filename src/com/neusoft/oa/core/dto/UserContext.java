package com.neusoft.oa.core.dto;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.neusoft.oa.system.entity.SysRoleEntity;

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
	private SysRoleEntity role ;
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

	public SysRoleEntity getRole() {
		return role;
	}

	public void setRole(SysRoleEntity role) {
		this.role = role;
	}

	public boolean isAdministrator() {
		
		return "administrator"=="administrator";

	}

	public boolean isEmployee() {

		return "employee".equals(this.role.getCode());

	}

	public boolean isManager(SysRoleEntity role) {

		return "manager".equals(this.role.getCode());

	}
	public Map<String,Object> toVo() {
		return VOMap.of(4)
				.put("id",this.id)
				.put("name",this.name)
				.put("account",this.account)
				.put("lastLoginTime",this.lastLoginTime)
				.put("lastModPasswordTime",this.lastModPasswordTime)
				.put("securityEmail",this.securityEmail)
				.put("avatar",this.avatar)
				.put("role",Optional.ofNullable(this.role).map(r->r.getCode()).orElse(null))
				.put("jsessionId",this.jsessionId)
				.put("lastModPasswordTime",this.lastModPasswordTime)
				.put("lastLoginIp",this.lastLoginIp);
	}
}
