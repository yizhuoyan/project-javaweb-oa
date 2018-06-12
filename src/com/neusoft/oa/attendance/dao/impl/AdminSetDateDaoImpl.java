/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.attendance.dao.AdminSetDateDao;
import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class AdminSetDateDaoImpl extends TemplateDaoImpl<AdminSetDateEntity> implements AdminSetDateDao {

	protected AdminSetDateDaoImpl() {
		super("atte_day");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(AdminSetDateEntity t) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = DBUtil.generateInsertSql(this.tableName,
				"id,when_day,work_day,on_duty,off_duty,sign_instart,sign_inend,sign_outstart,sign_outend,remark");
		PreparedStatement ps = connection.prepareStatement(sql);
		int i = 1;
		ps.setString(i++, t.getId());
		ps.setDate(i++, localDate2sqlDate(t.getWhenDay()));
		ps.setString(i++, t.getWorkDay());
		ps.setTime(i++, localTime2sqlTime(t.getOnDuty()));
		ps.setTime(i++, localTime2sqlTime(t.getOffDuty()));
		ps.setTime(i++, localTime2sqlTime(t.getSignInStart()));
		ps.setTime(i++, localTime2sqlTime(t.getSignInEnd()));
		ps.setTime(i++, localTime2sqlTime(t.getSignOutStart()));
		ps.setTime(i++, localTime2sqlTime(t.getSignOutEnd()));
		ps.setString(i++, t.getRemark());
		ps.executeUpdate();
	}

	@Override
	protected AdminSetDateEntity resultset2entity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		AdminSetDateEntity sde = new AdminSetDateEntity();
		sde.setId(rs.getString("id"));
		sde.setWhenDay(sqlDate2LocalDate(rs.getDate("when_day")));
		sde.setWorkDay(rs.getString("work_day"));
		sde.setOnDuty(sqlTime2LocalTime(rs.getTime("on_duty")));
		sde.setOffDuty(sqlTime2LocalTime(rs.getTime("off_duty")));
		sde.setSignInStart(sqlTime2LocalTime(rs.getTime("sign_instart")));
		sde.setSignInEnd(sqlTime2LocalTime(rs.getTime("sign_inend")));
		sde.setSignOutStart(sqlTime2LocalTime(rs.getTime("sign_outstart")));
		sde.setSignOutEnd(sqlTime2LocalTime(rs.getTime("sign_outend")));
		sde.setRemark(rs.getString("remark"));
		return sde;
	}

	@Override
	public void updateTime(String ufield, Object value, Map<String, Object> columns) throws Exception {
		// TODO Auto-generated method stub
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(tableName).append(" set ");
		// 保持参数值
		List<Object> values = new ArrayList<>(columns.size());
		for (Map.Entry<String, Object> entry : columns.entrySet()) {
			sql.append(entry.getKey()).append("=?,");
			values.add(entry.getValue());
		}
		sql.setCharAt(sql.length() - 1, ' ');
		sql.append(" where ").append(ufield).append("='").append(value).append("'");
		PreparedStatement ps = connection.prepareStatement(sql.toString());

		// 3传入参数并执行语句对象
		for (int i = 0, len = values.size(); i < len; i++) {
			ps.setObject(i + 1, values.get(i));
		}
		// 4获取执行结果（如果是查询，则转化为实体）
		System.out.println(sql.toString());
		ps.executeUpdate();
	}

	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<AdminSetDateEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		StringBuilder countSql = new StringBuilder("select count(*)");
		countSql.append(" from ").append(this.tableName);
		if (key != null) {					
			countSql.append(" where  when_day  like  ? or work_day like ? ");	
		} 
		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement(countSql.toString());
		
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
		querySql.append(" select * ");
		querySql.append(" from ").append(this.tableName);
		if (key != null) {
			querySql.append(" where when_day like ? or work_day like ? ");
		}
		querySql.append(" order by when_day desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
		ps = connection.prepareStatement(querySql.toString());
		
		
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
			AdminSetDateEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;

	}

}
