package com.neusoft.oa.message.dao.impl;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.message.dao.EmailDao;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.entity.EmailEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class EmailDaoImpl extends TemplateDaoImpl<EmailEntity> implements EmailDao {
	protected EmailDaoImpl() {
		super("oa_email");
	}

	public Timestamp utilDate2timestamp(java.util.Date d) {
		if (d != null) {
			return new Timestamp(d.getTime());
		}
		return null;
	}

	@Override
	public void insertEmail(EmailEntity ee) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = DBUtil.generateInsertSql(this.tableName,
				"id,title,content,recipient,sender,sendTime,inDraftBin,"
						+ "isCheckedByRecipient,isDeleteBySender,isDeleteByRecipient,"
						+ "attachment,inRecycleBin,completelyDeleteBySender," + "completelyDeleteByRecipient");
		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		int i = 1;
		ps.setString(i++, ee.getId());
		ps.setString(i++, ee.getTitle());
		ps.setString(i++, ee.getContent());
		ps.setString(i++, ee.getRecipient().getName());
		ps.setString(i++, ee.getSender().getName());
		ps.setTimestamp(i++, utilDate2timestamp(ee.getSendTime()));
		ps.setBoolean(i++, ee.isDraftBin());
		ps.setBoolean(i++, ee.isCheckedByRecipient());
		ps.setBoolean(i++, ee.isDeletedBySender());
		ps.setBoolean(i++, ee.isDeletedByRecipient());
		ps.setString(i++, ee.getAttachment());
		ps.setBoolean(i++, ee.isRecycledBin());
		ps.setBoolean(i++, ee.isCompletelyDeletedBySender());
		ps.setBoolean(i++, ee.isCompletelyDeletedByRecipient());

		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();

	}

	@Override
	public int deletEmailToRecycleBin(String emailId) throws Exception {
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("update ").append("oa_email set inRecycleBin=true where id='");
		whereSql.append(emailId);
		whereSql.append("'");
		// 预编译
		PreparedStatement ps = connection.prepareStatement(whereSql.toString());
		int e = ps.executeUpdate();
		return e;
	}

	@Override
	public void insert(EmailEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected EmailEntity resultset2entity(ResultSet rs) throws Exception {
		EmailEntity e = new EmailEntity();
		e.setId(rs.getString("id"));
		e.setTitle(rs.getString("title"));
		e.setContent(rs.getString("content"));
		EmployeeEntity recipient = new EmployeeEntity();
		recipient.setName(rs.getString("recipient"));
		// 接受者
		e.setRecipient(recipient);
		// 发送者
		EmployeeEntity sender = new EmployeeEntity();
		sender.setName(rs.getString("sender"));
		e.setSender(sender);
		e.setSendTime(rs.getDate("sendTime"));
		e.setDraftBin(rs.getBoolean("inDraftBin"));
		e.setCheckedByRecipient(rs.getBoolean("isCheckedByRecipient"));
		e.setDeletedBySender(rs.getBoolean("isDeleteBySender"));
		e.setDeletedByRecipient(rs.getBoolean("isDeleteByRecipient"));
		e.setAttachment(rs.getString("attachment"));
		e.setRecycledBin(rs.getBoolean("inRecycleBin"));
		e.setCompletelyDeletedBySender(rs.getBoolean("completelyDeleteBySender"));
		e.setCompletelyDeletedByRecipient(rs.getBoolean("completelyDeleteByRecipient"));
		return e;

	}

	@Override
	public int updateRecoverEmail(String emailId) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("update ").append(this.tableName);
		whereSql.append(" set inRecycleBin=0 where id='");
		whereSql.append(emailId);
		whereSql.append("'");
		PreparedStatement ps = connection.prepareStatement(whereSql.toString());
		int e = ps.executeUpdate();
		return e;
	}

	@Override
	public int updateCompletelyDelete(String emailId) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("update ").append(this.tableName);
		whereSql.append(" set completelyDeleteByRecipient=1 where id='");
		whereSql.append(emailId);
		whereSql.append("'");
		PreparedStatement ps = connection.prepareStatement(whereSql.toString());
		int e = ps.executeUpdate();
		return e;
	}

	@Override
	public int selectsByKey(String userid, String key, int pageNo, int pageSize, List<EmailEntity> pageData)
			throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName)
				.append(" where inRecycleBin=1 and completelyDeleteByRecipient=0");
		whereSql.append(" and  ( recipient like ? or sender like ?) ");
		if (key != null) {
			whereSql.append(" and ( recipient like ? or sender like ?) ");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			userid = "%" + userid + "%";
			ps.setString(i++, userid);
			ps.setString(i++, userid);
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
		} else {
			int i = 1;
			userid = "%" + userid + "%";
			ps.setString(i++, userid);
			ps.setString(i++, userid);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}

		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by recipient desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			userid = "%" + userid + "%";
			ps.setString(i++, userid);
			ps.setString(i++, userid);
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
		} else {
			int i = 1;
			userid = "%" + userid + "%";
			ps.setString(i++, userid);
			ps.setString(i++, userid);
		}
		// 4执行语句对象并获取结果

		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmailEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}

	@Override
	public int selectsSentAndReceivedEmailByKey(String user, String key, int pageNo, int pageSize,
			List<EmailEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = (Connection) DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName).append(" where inDraftBin=0 and (sender=")
				.append("'" + user + "'").append(" or recipient=").append("'" + user + "'").append(")").append(" and inRecycleBin=0");
		if (key != null) {
			whereSql.append(" and  ( title like ? or sender like ? or recipient like ? )");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by sendTime desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = (PreparedStatement) connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmailEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}
	@Override
	public int selectsSentEmailByKey(String sender, String key, int pageNo, int pageSize, List<EmailEntity> pageData)
			throws Exception {
		// 1获取连接
		Connection connection = (Connection) DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName).append(" where inDraftBin=0 and sender=")
				.append("'" + sender + "'").append(" and inRecycleBin=0");
		if (key != null) {
			whereSql.append(" and  ( title like ? or recipient like ? )");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by sendTime desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = (PreparedStatement) connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmailEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}
	@Override
	public int selectsReceivedEmailByKey(String recipient, String key, int pageNo, int pageSize,
			List<EmailEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = (Connection) DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName).append(" where inDraftBin=0 and recipient=")
				.append("'" + recipient + "'").append(" and inRecycleBin=0");
		if (key != null) {
			whereSql.append(" and  ( title like ? or sender like ? )");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by sendTime desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = (PreparedStatement) connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmailEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}
	@Override
	public int selectsAllEmailByKey(String key, int pageNo, int pageSize, List<EmailEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = (Connection) DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(this.tableName).append(" where inDraftBin=0");
		if (key != null) {
			whereSql.append(" and title like ? or sender like ? or recipient like ?");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by sendTime desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = (PreparedStatement) connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmailEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}

	@Override
	public int selectsEmailInDraftBinsByKey(String sender,String key, int pageNo, int pageSize, List<EmailEntity> pageData)
			throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from  ").append(" oa_email where sender='").append(sender).append("'");
		whereSql.append(" and inDraftBin=1 and inRecycleBin=0");
		
		if (key != null) {
			whereSql.append(" and ( title like ? or content like ? or recipient like ?)");
		}
		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by sendTime desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmailEntity email = resultset2entity(rs);
			pageData.add(email);
		}
		return total;
	}

	@Override
	public EmailEntity selectUnreadEmail(String emailId) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("select * from ").append(this.tableName);
		whereSql.append(" where id='");
		whereSql.append(emailId);
		whereSql.append("'");
		PreparedStatement ps = connection.prepareStatement(whereSql.toString());
		ResultSet result= ps.executeQuery();
		while (result.next()) {
			EmailEntity email = resultset2entity(result);
			return email;
		}
		return null;	
	}

	@Override
	public int updateSendEmailById(String emailId) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("update ").append(this.tableName);
		whereSql.append(" set inDraftBin=0 where id='");
		whereSql.append(emailId);
		whereSql.append("'");
		PreparedStatement ps = connection.prepareStatement(whereSql.toString());
		int i= ps.executeUpdate();
		return i;
	}

	@Override
	public EmailEntity selectEmailById(String emailId) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("select * from ").append(this.tableName).append(" where id='");
		whereSql.append(emailId);
		whereSql.append("'");
		PreparedStatement ps = connection.prepareStatement(whereSql.toString());
		ResultSet result=ps.executeQuery();
		 while(result.next()) {
			 EmailEntity email = resultset2entity(result);
				return email;
		 }
		return null;
	}
	@Override
	public String selectEmailSaveAddress(String emailID) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("from ").append(this.tableName);
		whereSql.append(" where id ='").append(emailID);
		whereSql.append("'");
		
		PreparedStatement ps = connection.prepareStatement("select * " + whereSql);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			EmailEntity email = resultset2entity(rs);
			return email.getAttachment();
		}
		return null;	
	}
}
