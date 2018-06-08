package com.neusoft.oa.organization.entity;

import java.time.Instant;

public class DepartmentEntity {

	private String id;// 部门表ID
	private DepartmentEntity parent;
	private String code;// 部门编号
	private EmployeeEntity manager;// 部门经理编号
	private int members;// 部门人数
	private String name; // 部门名称
	private Instant createTime;// 部门创建时间
	private String remark;// 备注

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

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DepartmentEntity [id=" + id + ", code=" + code + ", manager=" + manager + ", members=" + members
				+ ", name=" + name + ", createTime=" + createTime + ", remark=" + remark + "]";
	}

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
		DepartmentEntity other = (DepartmentEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public DepartmentEntity getParent() {
		return parent;
	}

	public void setParent(DepartmentEntity parent) {
		this.parent = parent;
	}
	

}
