package com.neusoft.oa.message.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.neusoft.oa.core.util.AssertThrowUtil.*;

import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.message.ao.EmailAo;
import com.neusoft.oa.message.dao.AddressBookDao;
import com.neusoft.oa.message.dao.EmailDao;
import com.neusoft.oa.message.dao.impl.EmailDaoImpl;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.message.function.EmailFunction;

public class EmailFunctionImpl implements EmailFunction {

	@Override
	public EmailEntity addEmail(EmailAo email) throws Exception {
		String title = $("主题", email.getTitle());
		assertLessThan("主题不能超过32个字符", title.length(), 32);
		String content = $("内容", email.getContent());
		String RecipientID = $("收件人", email.getRecipient().getName());
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		EmailEntity emailEntity = new EmailEntity();
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
	public int deleteEmail(String emailId) throws Exception {
		String id = $("id", emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int e = dao.deletEmailToRecycleBin(emailId);
		return e;
	}

	@Override
	public int deleteEmail(String[] arry) throws Exception {
		String id = null;
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int e = 0;
		for (int i = 0; i < arry.length; i++) {
			id = $("id", arry[i]);			
			e = dao.deletEmailToRecycleBin(id);
			e++;
		}
		return e;
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
	public EmailEntity completeQurey() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int recoverEmail(String emailId) throws Exception {
		String id = $("id", emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int e = dao.updateRecoverEmail(id);
		return e;
	}

	@Override
	public int completelyDelete(String emailId) throws Exception {
		String id = $("id", emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int e = dao.updateCompletelyDelete(id);
		return e;
	}

	@Override
	public int selectRecyeBinServletByUserEmailTotal(String userId, String key, int pageNo, int pageSize,
			List<EmailEntity> pageData) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int total = dao.selectsByKey(userId, key, pageNo, pageSize, pageData);
		return total;
	}

	@Override
	public PaginationQueryResult<EmailEntity> querySentAndReceivedEmailByKey(String user, String key, String pageNo,
			String pageSize) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, 1);
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize,
				ThisSystemUtil.parseInt(System.getProperty("sys.default-page-size"), 10));
		List<EmailEntity> pageData = new ArrayList<>(pageSizeInt);
		// 2执行业务逻辑
		EmailDao edao = DaoFactory.getDao(EmailDao.class);
		int total = edao.selectsSentAndReceivedEmailByKey(user, key, pageNoInt, pageSizeInt, pageData);
		// 3组装业务结果
		PaginationQueryResult<EmailEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNoInt);
		result.setPageSize(pageSizeInt);
		return result;
	}
	@Override
	public PaginationQueryResult<EmailEntity> querySentEmailByKey(String sender, String key, String pageNo,
			String pageSize) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, 1);
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize,
				ThisSystemUtil.parseInt(System.getProperty("sys.default-page-size"), 10));
		List<EmailEntity> pageData = new ArrayList<>(pageSizeInt);
		// 2执行业务逻辑
		EmailDao edao = DaoFactory.getDao(EmailDao.class);
		int total = edao.selectsSentEmailByKey(sender, key, pageNoInt, pageSizeInt, pageData);
		// 3组装业务结果
		PaginationQueryResult<EmailEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNoInt);
		result.setPageSize(pageSizeInt);
		return result;
	}
	@Override
	public PaginationQueryResult<EmailEntity> queryReceivedEmailByKey(String recipient, String key, String pageNo,
			String pageSize) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, 1);
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize,
				ThisSystemUtil.parseInt(System.getProperty("sys.default-page-size"), 10));
		List<EmailEntity> pageData = new ArrayList<>(pageSizeInt);
		// 2执行业务逻辑
		EmailDao edao = DaoFactory.getDao(EmailDao.class);
		int total = edao.selectsReceivedEmailByKey(recipient, key, pageNoInt, pageSizeInt, pageData);
		// 3组装业务结果
		PaginationQueryResult<EmailEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNoInt);
		result.setPageSize(pageSizeInt);
		return result;
	}
	@Override
	public PaginationQueryResult<EmailEntity> queryAllEmailByKey(String key, String pageNo, String pageSize)
			throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		int pageNoInt = ThisSystemUtil.parseInt(pageNo, 1);
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize,
				ThisSystemUtil.parseInt(System.getProperty("sys.default-page-size"), 10));
		List<EmailEntity> pageData = new ArrayList<>(pageSizeInt);
		// 2执行业务逻辑
		EmailDao edao = DaoFactory.getDao(EmailDao.class);
		int total = edao.selectsAllEmailByKey(key, pageNoInt, pageSizeInt, pageData);
		// 3组装业务结果
		PaginationQueryResult<EmailEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNoInt);
		result.setPageSize(pageSizeInt);
		return result;
	}

	@Override
	public int queryDraftBins(String recipient, String key, int pageNo, int pageSize, List<EmailEntity> pageData)
			throws Exception {
		key = ThisSystemUtil.trim(key);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int total = dao.selectsEmailInDraftBinsByKey(recipient, key, pageNo, pageSize, pageData);
		return total;

	}

	@Override
	public EmailEntity loadUnreadEmail(String emailId) throws Exception {
		String id = $("id", emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		EmailEntity e = dao.selectUnreadEmail(id);
		return e;
	}

	@Override
	public int sendEmail(String emailId) throws Exception {
		String id = $("id", emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		int e= dao.updateSendEmailById(emailId);
		return e;
	}

	@Override
	public EmailEntity loadSendEmailById(String emailId) throws Exception {
		String id = $("id", emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		EmailEntity e= dao.selectEmailById(emailId);
		
		return e;
	}
	@Override
	public String loadFilePath(String emailId) throws Exception {
		emailId = ThisSystemUtil.trim(emailId);
		EmailDao dao = DaoFactory.getDao(EmailDao.class);
		String EmailSaveAddress = dao.selectEmailSaveAddress(emailId);
		return EmailSaveAddress;
	}

}
