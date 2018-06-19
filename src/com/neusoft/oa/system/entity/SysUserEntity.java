package com.neusoft.oa.system.entity;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.core.dto.VOMap;

/**
 * 用户实体
 * @author yizhuoyan@hotmail.com
 *
 */

public class SysUserEntity {
	/**用户状态 */
	public static final int FLAG_NORMAL=0,
							FLAG_LOCK=1,
							FLAG_DELETED=9;
	private String id;
	/**账号*/
	private String account;
	/**名称*/
	private String name;
	/**密码*/
	private String password;
	/**创建时间*/
	private Date createTime;
	/**最后登陆时间*/
	private Date lastLoginTime;
	/**最后登陆ip*/
	private String lastLoginIP;
	/**最后密码修改时间
	 * 如果用户超过三个月未修改密码，则锁定账户
	 * */
	private Date lastModPasswordTime;
	/**电话*/
	private String phone;
	/**密保邮箱*/
	private String securityEmail;
	/**头像路径或名称*/
	private String avatar;
	/**备注*/
	private String remark;
	/**状态
	 * 0=正常
	 * 1=锁定
	 * 9=删除
	 */
	private int flag;
	
	private SysUserEntity createUser;
	/**
	 * 用户所属角色
	 */
	private List<SysRoleEntity> roles;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUserEntity other = (SysUserEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public List<SysRoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRoleEntity> roles) {
		this.roles = roles;
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
	public String getName() {
		return name;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastModPasswordTime() {
		return lastModPasswordTime;
	}
	public void setLastModPasswordTime(Date lastModPasswordTime) {
		this.lastModPasswordTime = lastModPasswordTime;
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
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public VOMap toVo() {
		VOMap m=VOMap.of(9);
		m.put("id", this.id)
		.put("name", this.name)
		.put("account", this.account)
		.put("createTime", this.createTime)
		.put("avatar", this.avatar)
		.put("locked", this.flag==SysUserEntity.FLAG_LOCK)
		.put("lastLoginIP", this.lastLoginIP)
		.put("lastLoginTime", this.lastLoginTime)
		.put("lastModPasswordTime", this.lastModPasswordTime)
		.put("phone", this.phone)
		.put("remark", this.remark)
		.put("securityEmail", this.securityEmail);
		if(this.createUser!=null) {
			m.put("createUser", VOMap.of(2)
					.put("id", createUser.id)
					.put("name", createUser.name));
		}
		return m;
	}
	public VOMap toSimpleVo() {
		return VOMap.of(3)
		.put("id", this.id)
		.put("name", this.name)
		.put("account", this.account);
	}
	public SysUserEntity getCreateUser() {
		return createUser;
	}
	public void setCreateUser(SysUserEntity createUser) {
		this.createUser = createUser;
	}
	
}
