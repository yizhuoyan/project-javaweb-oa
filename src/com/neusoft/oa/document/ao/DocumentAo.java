package com.neusoft.oa.document.ao;

import java.time.Instant;

import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.system.entity.SysUserEntity;

public class DocumentAo {
	private String id;//文档编号
	private String property;//文档属性说明
	private String path;//文档位置
	private String name;//文档名称
	private String remark;//备注
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
