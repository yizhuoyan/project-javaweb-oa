package com.neusoft.oa.message.ao;

import com.neusoft.oa.organization.entity.EmployeeEntity;

public class EmailAo {
	   //主题
		private String title;
		//内容
		private String content;
		//接受者
		private EmployeeEntity recipient;
		//附件地址
		private String  attachment;
		//发送者
		private EmployeeEntity sender;
		//草稿箱
		private boolean inDraftBin = false;
		//是否被接受查看
		private boolean checkedByRecipient = false;
		//是否被发送删除
		private boolean deletedBySender = false;
		//是否被接受者删除
		private boolean deletedByRecipient = false;
		//是否放入废件箱
		private boolean inRecycleBin = false;
		//是否被发送者永久删除
		private boolean completelyDeletedBySender = false;
		//是否被接受者永久删除
		private boolean completelyDeletedByRecipient = false;
		//是否失效
		private boolean invalid = false;
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
		
		public EmployeeEntity getRecipient() {
			return recipient;
		}
		public void setRecipient(EmployeeEntity recipient) {
			this.recipient = recipient;
		}
		public EmployeeEntity getSender() {
			return sender;
		}
		public void setSender(EmployeeEntity sender) {
			this.sender = sender;
		}
		public String getAttachment() {
			return attachment;
		}
		public void setAttachment(String attachment) {
			this.attachment = attachment;
		}
		
		public boolean isInDraftBin() {
			return inDraftBin;
		}
		public void setInDraftBin(boolean inDraftBin) {
			this.inDraftBin = inDraftBin;
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
		public boolean isInRecycleBin() {
			return inRecycleBin;
		}
		public void setInRecycleBin(boolean inRecycleBin) {
			this.inRecycleBin = inRecycleBin;
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
