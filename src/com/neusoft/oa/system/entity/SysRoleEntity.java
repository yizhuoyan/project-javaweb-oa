package com.neusoft.oa.system.entity;
/**
 * 系统角色表
 * @author yizhuoyan@hotmail.com
 *
 */

import java.time.Instant;

import com.neusoft.oa.core.dto.VOMap;

public class SysRoleEntity {
	private String id;
	private String code;
	private String name;
	private String remark;
	private SysUserEntity createUser;
	private Instant createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	public VOMap toVo() {
		VOMap map=VOMap.of(4).put("id", id)
				.put("code", code)
				.put("name", name)
				.put("remark", remark)
				.put("createTime", createTime);
				
		if(this.createUser!=null) {
			map.put("createUser",VOMap.of(2)
					.put("id", this.createUser.getId())
					.put("name",this.createUser.getName()));
		}
		return map;
	}
	public SysUserEntity getCreateUser() {
		return createUser;
	}
	public void setCreateUser(SysUserEntity createUser) {
		this.createUser = createUser;
	}
	public Instant getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}
	
}
