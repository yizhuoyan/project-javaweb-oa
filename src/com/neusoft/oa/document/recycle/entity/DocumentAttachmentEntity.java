package com.neusoft.oa.document.recycle.entity;

import java.time.Instant;

import com.neusoft.oa.base.entity.SysUserEntity;

public class DocumentAttachmentEntity {
	private String id;//附件编号
	private DocumentEntity	documentId;//附件所属文档编号
	private String path;//附件位置
	private String name;//附件名称
	private String property;//附件描述
	private SysUserEntity createUserId;//附件创建者编号
	private Instant createTime;//附件创建时间
	private String remark;//备注
	public static final int FLAG_ENABLE=1,FLAG_BEUSED=2,FLAG_DELETED=0;//删除标志
	private String deptId;//部门编号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DocumentEntity getDocumentId() {
		return documentId;
	}
	public void setDocumentId(DocumentEntity documentId) {
		this.documentId = documentId;
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
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public SysUserEntity getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(SysUserEntity createUserId) {
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public static int getFlagEnable() {
		return FLAG_ENABLE;
	}
	public static int getFlagBeused() {
		return FLAG_BEUSED;
	}
	public static int getFlagDeleted() {
		return FLAG_DELETED;
	}
	
	
	

}
