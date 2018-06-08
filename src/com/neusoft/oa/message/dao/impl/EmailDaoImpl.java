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
import com.neusoft.oa.message.dao.EmailDao;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.entity.EmailEntity;

public class EmailDaoImpl implements EmailDao{
	protected Timestamp utilDate2timestamp(java.util.Date d ) {
		if (d != null) {
			return  new Timestamp(d.getTime());
		} 
		return null;
	}
	@Override
	public void insertEmail(EmailEntity ee) throws Exception {
		         // 1获取连接
				Connection connection =DBUtil.getConnection();
				// 2创建sql语句对象
				String sql = DBUtil.generateInsertSql("oa_email",
						"id,title,content,recipient,sender,sendTime,inDraftBin,"
						+ "isCheckedByRecipient,isDeleteBySender,isDeleteByRecipient,"
						+ "attachment,inRecycleBin,completelyDeleteBySender,"
						+ "completelyDeleteByRecipient");
				PreparedStatement ps =connection.prepareStatement(sql);
				// 3传入参数并执行语句对象
				System.out.println(sql);
				System.out.println(ee.getId());
				int i = 1;
				ps.setString(i++, ee.getId());
				ps.setString(i++, ee.getTitle());
				ps.setString(i++, ee.getContent());
				ps.setString(i++, "sdfas");
				ps.setString(i++, "ihii");
				ps.setTimestamp(i++,utilDate2timestamp(ee.getSendTime()));
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
	public List<AddressBookEntity> selectEmailAddressBookByKey(String key) throws Exception {
		     // 1获取连接
		     Connection connection =DBUtil.getConnection();  
		        // 2创建语句对象
				StringBuilder sql = new StringBuilder();
				sql.append("select name,securityEmail,phone,account from ").append(" sys_user ");
				if (key != null) {
					sql.append(" where ( name like ? or securityEmail like ? or phone like ? or  account like ? )");

				}
				sql.append(" order by name desc");
				System.out.println(sql);
				PreparedStatement ps = connection.prepareStatement(sql.toString());
				// 3传入参数
				if (key != null) {
					int i = 1;
					key = "%" + key + "%";
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
				}
				// 4执行语句对象并获取结果
				ResultSet rs = ps.executeQuery();	
				// 5转换结果为实体
				List<AddressBookEntity> result = new LinkedList<>();
				while (rs.next()) {
					AddressBookEntity e = resultset2entity(rs);
					result.add(e);
				}
				return result;
	
	}
	protected AddressBookEntity resultset2entity(ResultSet rs) throws Exception {
		AddressBookEntity e = new AddressBookEntity();
		e.setName(rs.getString("name"));
		e.setEmail(rs.getString("securityEmail"));
		e.setPhone(rs.getString("phone"));
		e.setDepartment(rs.getString("account"));
		return e;
	}
	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<AddressBookEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		//组装wheresql 
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from  ").append(" sys_user");
				
		if (key != null) {
			whereSql.append(" where ( name like ? or securityEmail like ? or phone like ? or  account like ? )");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement("select count(*) "+whereSql);
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
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
		querySql.append(" select name,securityEmail,phone,account ").append(whereSql);
		querySql.append(" order by account desc");
		querySql.append(" limit ").append((pageNo-1)*pageSize).append(",")
		.append(pageSize);
		

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			AddressBookEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}

}
