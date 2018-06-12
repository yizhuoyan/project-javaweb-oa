package com.neusoft.oa.attendance.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.attendance.dao.VacateDao;

import com.neusoft.oa.attendance.entity.VacateEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class VacateDaoImpl extends TemplateDaoImpl<VacateEntity> implements VacateDao {

	protected VacateDaoImpl() {
		super("atte_vacate");	
	}

	@Override
	public void insert(VacateEntity t) throws Exception {
		String sql=DBUtil.generateInsertSql(this.tableName, "id,emp_id,when_day,starttime,start_m,endtime,end_m,type,reason,result");
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int i=1;
		ps.setString(i++,t.getId());
		ps.setString(i++, t.getEmp().getId());
		ps.setTimestamp(i++,utilDate2timestamp(new Date()));
		ps.setDate(i++,new java.sql.Date(t.getStarttime().getTime()));
		ps.setString(i++, t.getStartM());
		ps.setDate(i++, new java.sql.Date(t.getEndtime().getTime()));
		ps.setString(i++, t.getEndM());
		ps.setString(i++, t.getType());
		ps.setString(i++, t.getReason());
		ps.setInt(i++, t.getResult());
		ps.executeUpdate();
	}

	@Override
	protected VacateEntity resultset2entity(ResultSet rs) throws Exception {
		VacateEntity u=new VacateEntity();
		EmployeeEntity e=new EmployeeEntity();
		u.setId(rs.getString("id"));
		e.setId(rs.getString("emp_id"));
		u.setEmp(e);
		u.setWhen(new Date(rs.getTimestamp("when_day").getTime()));
		u.setStarttime(new Date(rs.getDate("starttime").getTime()));
		u.setStartM(rs.getString("start_m"));
		u.setEndtime(new Date(rs.getDate("endtime").getTime()));
		u.setEndM(rs.getString("end_m"));
		u.setType(rs.getString("type"));
		u.setReason(rs.getString("reason"));
		u.setResult(rs.getInt("result"));
		u.setApprover(rs.getString("approver"));
		if(rs.getTimestamp("approvetime")!=null){
			u.setApprovetime(new Date(rs.getTimestamp("approvetime").getTime()));
		}else {
			u.setApprovetime(null);
		}
		u.setRemark(rs.getString("remark"));
		return u;
	}

	@Override
	public int selectsByKey(String id,String key, int pageNo, int pageSize, List<VacateEntity> pageData) throws Exception {
		// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 组装wheresql
				StringBuilder countSql = new StringBuilder("select count(*)");
				countSql.append(" from ").append(" atte_vacate ")
				.append(" where emp_id=? ");
				if (key != null) {					
					countSql.append(" and  when_day  like  ? or type like ? or result like ?  ");	
				} 
				// 2查询总记录语句对象
				PreparedStatement ps = connection.prepareStatement(countSql.toString());
				int i = 1;
				ps.setString(i++, id);
				// 3传入参数
				if (key != null) {
					
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
				querySql.append(" select * ");
				querySql.append(" from ").append("atte_vacate ");
				querySql.append(" where emp_id=?");
				if (key != null) {
					querySql.append(" and when_day like ? or type like ? or result  like ? ");
				}
				querySql.append(" order by when_day desc");
				querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
				System.out.println(querySql.toString());
				ps = connection.prepareStatement(querySql.toString());
				i = 1;
				ps.setString(i++, id);
				
				// 3传入参数
				if (key != null) {
					
					key = "%" + key + "%";
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
				}
				// 4执行语句对象并获取结果
				rs = ps.executeQuery();
				// 5转换结果为实体
				while (rs.next()) {
					VacateEntity e = resultset2entity(rs);
					pageData.add(e);
				}
				return total;
	}

}
