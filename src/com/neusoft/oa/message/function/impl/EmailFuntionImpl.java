package com.neusoft.oa.message.function.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.neusoft.oa.core.util.AssertThrowUtil.*;

import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.message.ao.EmailAo;
import com.neusoft.oa.message.dao.EmailDao;
import com.neusoft.oa.message.dao.impl.EmailDaoImpl;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;

public class EmailFuntionImpl implements EmailFunction{

	@Override
	public EmailEntity addEmail(EmailAo email) throws Exception {
		String title=$("主题", email.getTitle());
		assertLessThan("主题不能超过32个字符", title.length(), 32);
		String content=$("内容", email.getContent());
		String RecipientID=$("收件人", email.getRecipient().getId());
		EmailDao dao=new EmailDaoImpl();
		EmailEntity emailEntity=new EmailEntity();
		emailEntity.setAttachment(email.getAttachment());
		emailEntity.setCheckedByRecipient(email.isCheckedByRecipient());
		emailEntity.setCompletelyDeletedByRecipient(email.isCompletelyDeletedByRecipient());
		emailEntity.setCompletelyDeletedBySender(email.isCompletelyDeletedBySender());
		emailEntity.setContent(content);
		emailEntity.setDeletedByRecipient(email.isDeletedByRecipient());
		emailEntity.setDeletedBySender(email.isDeletedBySender());
		emailEntity.setDraftBin(email.isInDraftBin());
		emailEntity.setId(UUID.randomUUID().toString().replace("-", ""));
		emailEntity.setRecipient(email.getRecipient());
		emailEntity.setRecycledBin(email.isInRecycleBin());
		emailEntity.setSender(email.getSender());
		emailEntity.setSendTime(new Date());
		emailEntity.setTitle(title);
		dao.insertEmail(emailEntity);
		return emailEntity;
	}

	@Override
	public EmailEntity loadMeassge(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailEntity deleteMeassge(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailEntity completeDelet(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailEntity> adminMassMessage(List<EmailEntity> email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailEntity> managerMassMessage(List<EmailEntity> email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailEntity completeQurey() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressBookEntity> SearchEmailAddressBookByKey(String key) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		EmailDao dao=new EmailDaoImpl();
		List<AddressBookEntity> AddressBook=dao.selectEmailAddressBookByKey(key);
		return AddressBook;
	}

	
}
