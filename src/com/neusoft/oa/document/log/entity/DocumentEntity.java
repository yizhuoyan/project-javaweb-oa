package com.neusoft.oa.document.log.entity;

import java.time.Instant;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DocumentEntity {
	
	private String id;//文档编号
	private String property;//文档属性说明
	private String path;//文档位置
	private String name;//文档名称
	private EmployeeEntity createUserId;//文档创建者
	private Instant createTime;//文档创建时间
	private String remark;//备注
	private int flag;//状态标志 0在回收站1正常2被占用
	private DepartmentEntity deptId;//所属部门id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EmployeeEntity getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(EmployeeEntity createUserId) {
		this.createUserId = createUserId;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public DepartmentEntity getDeptId() {
		return deptId;
	}
	public void setDeptId(DepartmentEntity deptId) {
		this.deptId = deptId;
	}
	
	
	
	
}
