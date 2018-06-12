package com.neusoft.oa.message.entity;

import java.util.Date;

import com.neusoft.oa.organization.entity.EmployeeEntity;

public class MessageEntity {
	private String id;
	private String title;
	private String content;
	private String recipient;
	private EmployeeEntity sender;
	private Date sendTime;
	private int liveTime;
	private boolean draftBin;
	private boolean checkedByRecipient;
	private boolean deletedBySender;
	private boolean deletedByRecipient;
	private boolean recycledBin;
	private boolean completelyDeletedBySender;
	private boolean completelyDeletedByRecipient;
	private boolean invalid;
	
	public int getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public EmployeeEntity getSender() {
		return sender;
	}
	public void setSender(EmployeeEntity sender) {
		this.sender = sender;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public boolean isDraftBin() {
		return draftBin;
	}
	public void setDraftBin(boolean draftBin) {
		this.draftBin = draftBin;
	}
	public boolean isCheckedByRecipient() {
		return checkedByRecipient;
	}
	public void setCheckedByRecipient(boolean checkedByRecipient) {
		this.checkedByRecipient = checkedByRecipient;
	}
	public boolean isDeletedBySender() {
		return deletedBySender;
	}
	public void setDeletedBySender(boolean deletedBySender) {
		this.deletedBySender = deletedBySender;
	}
	public boolean isDeletedByRecipient() {
		return deletedByRecipient;
	}
	public void setDeletedByRecipient(boolean deletedByRecipient) {
		this.deletedByRecipient = deletedByRecipient;
	}
	public boolean isRecycledBin() {
		return recycledBin;
	}
	public void setRecycledBin(boolean recycledBin) {
		this.recycledBin = recycledBin;
	}
	public boolean isCompletelyDeletedBySender() {
		return completelyDeletedBySender;
	}
	public void setCompletelyDeletedBySender(boolean completelyDeletedBySender) {
		this.completelyDeletedBySender = completelyDeletedBySender;
	}
	public boolean isCompletelyDeletedByRecipient() {
		return completelyDeletedByRecipient;
	}
	public void setCompletelyDeletedByRecipient(boolean completelyDeletedByRecipient) {
		this.completelyDeletedByRecipient = completelyDeletedByRecipient;
	}
	public boolean isInvalid() {
		return invalid;
	}
	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}
	

}
