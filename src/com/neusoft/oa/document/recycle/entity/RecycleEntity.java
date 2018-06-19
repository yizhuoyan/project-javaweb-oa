package com.neusoft.oa.document.recycle.entity;

import java.time.Instant;

import com.neusoft.oa.system.entity.SysUserEntity;

public class RecycleEntity {
	private String docId;//文档编号
	private String atmId;//附件编号
	private DocumentEntity	documentId;//附件所属文档编号
	private String docPath;//文档位置
	private String atmPath;//附件位置
	private String docName;//文档名称
	private String atmName;//附件名称
	private Instant docCreateTime;//文档创建时间
	private Instant atmCreateTime;//附件创建时间
	private SysUserEntity docCreateUserId;//文档创建者
	private SysUserEntity atmCreateUserId;//附件创建者编号
	private String remark;//备注
	private String docDeptId;//部门编号
	private String atmDeptId;//部门编号
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getAtmId() {
		return atmId;
	}
	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	public DocumentEntity getDocumentId() {
		return documentId;
	}
	public void setDocumentId(DocumentEntity documentId) {
		this.documentId = documentId;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getAtmPath() {
		return atmPath;
	}
	public void setAtmPath(String atmPath) {
		this.atmPath = atmPath;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	public Instant getDocCreateTime() {
		return docCreateTime;
	}
	public void setDocCreateTime(Instant docCreateTime) {
		this.docCreateTime = docCreateTime;
	}
	
	public SysUserEntity getDocCreateUserId() {
		return docCreateUserId;
	}
	public void setDocCreateUserId(SysUserEntity docCreateUserId) {
		this.docCreateUserId = docCreateUserId;
	}
	public SysUserEntity getAtmCreateUserId() {
		return atmCreateUserId;
	}
	public void setAtmCreateUserId(SysUserEntity atmCreateUserId) {
		this.atmCreateUserId = atmCreateUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDocDeptId() {
		return docDeptId;
	}
	public void setDocDeptId(String docDeptId) {
		this.docDeptId = docDeptId;
	}
	public String getAtmDeptId() {
		return atmDeptId;
	}
	public void setAtmDeptId(String atmDeptId) {
		this.atmDeptId = atmDeptId;
	}
	public Instant getAtmCreateTime() {
		return atmCreateTime;
	}
	public void setAtmCreateTime(Instant atmCreateTime) {
		this.atmCreateTime = atmCreateTime;
	}
	
	
	
	
}
