package com.neusoft.oa.message.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;



import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.message.dao.AddressBookDao;
import com.neusoft.oa.message.entity.AddressBookEntity;

public class AddressBookDaoImpl extends TemplateDaoImpl<AddressBookEntity> implements AddressBookDao{
	
	protected AddressBookDaoImpl() {
		super("sys_user");
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
	
	
	@Override
	public void insert(AddressBookEntity t) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected AddressBookEntity resultset2entity(ResultSet rs) throws Exception {
		AddressBookEntity e = new AddressBookEntity();
		e.setName(rs.getString("name"));
		e.setEmail(rs.getString("securityEmail"));
		e.setPhone(rs.getString("phone"));
		e.setDepartment(rs.getString("account"));
		return e;
	}
	

	
}
