package com.neusoft.oa.core.email;

public class TextEmail {
	private String fromAccount;
	private String fromName;
	private String recipientAccount;
	private String subject;
	private String content;
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getRecipientAccount() {
		return recipientAccount;
	}
	public void setRecipientAccount(String recipientAccount) {
		this.recipientAccount = recipientAccount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
