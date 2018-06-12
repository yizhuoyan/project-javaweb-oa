package com.neusoft.oa.document.ao;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DocumentAttachmentAo {
	private String id;//附件编号
	private String documentId;//附件所属文档
	private String name;//附件名称
	private String property;//附件属性说明
	private String createUserId;//附件创建者
	private String remark;//备注
	private String deptId;//所属部门id
	private String path;//路径
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	private String type;//附件类型
	private long size;//附件大小
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
}
