package com.neusoft.oa.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.attendance.dao.RetroactiveDao;
import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.attendance.entity.RetroactiveEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.SQLGenerator;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class RetroactiveDaoImpl  extends TemplateDaoImpl<RetroactiveEntity> implements  RetroactiveDao{

	protected RetroactiveDaoImpl() {
		super("atte_retroactive");
		
	}
	@Override
	public void insert(RetroactiveEntity t) throws Exception {
		String sql=SQLGenerator.generateInsertSql(this.tableName, "id,emp_id,apply_time,sign_m");
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int i=1;
		ps.setString(i++,t.getId());
		ps.setString(i++, t.getEmp().getId());
		ps.setDate(i++, new java.sql.Date(t.getRetTime().getTime()));
		ps.setInt(i++, t.getRetM());
		ps.executeUpdate();
	}

	protected RetroactiveEntity resultset2entity(ResultSet rs) throws Exception {
		RetroactiveEntity u=new RetroactiveEntity();
		EmployeeEntity e=new EmployeeEntity();
		u.setId(rs.getString("id"));
		e.setId(rs.getString("emp_id"));
		u.setEmp(e);
		if(rs.getTimestamp("apply_time")!=null) {
			u.setRetTime(new Date(rs.getTimestamp("apply_time").getTime()));
		}else {
			u.setRetTime(null);
		}
		u.setRetTime(new Date(rs.getDate("sign_time").getTime()));
		u.setRetM(rs.getInt("sign_m"));
		u.setReason(rs.getString("reason"));
		u.setResult(rs.getInt("result"));
		u.setApprover(rs.getString("approver"));
		if(rs.getTimestamp("approvetime")!=null) {
			u.setApprovetime(new Date(rs.getTimestamp("approvetime").getTime()));
		}else {
			u.setApprovetime(null);
		}
		u.setRemark(rs.getString("remark"));
		return u;
	}

	@Override
	public int selectsByKey(String id, String key, int pageNo, int pageSize, List<RetroactiveEntity> pageData)
			throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder countSql = new StringBuilder("select count(*)");
		countSql.append(" from ").append(" atte_retroactive ")
		.append(" where emp_id=? ");
		if (key != null) {					
			countSql.append(" and  apply_time  like  ? or type like ? or result like ?  ");	
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
		querySql.append(" from ").append("atte_retroactive ");
		querySql.append(" where emp_id=?");
		if (key != null) {
			querySql.append(" and apply_time like ? or type like ? or result  like ? ");
		}
		querySql.append(" order by apply_time desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
		
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
			RetroactiveEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}

	@Override
	public List<EmployeeEntity> selectsAm() throws Exception {
	 	Connection connection=DBUtil.getConnection();
		 StringBuilder sql=new StringBuilder();
		 sql.append("SELECT u.uid FROM oa_emp u  where not exists")
		 .append("(select * from  atte_attendance s where s.emp_id=u.uid and)")
		 .append("and s.when_day= ? and s.isvalid=? and s.ampm=?");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			int i=1;
			ps.setDate(i++,  new Date(System.currentTimeMillis()));
			ps.setInt(i++, 1);
			ps.setInt(i++, 0);
			ResultSet rs = ps.executeQuery();
			List<EmployeeEntity> result=new LinkedList<>();
			while(rs.next()) {
				
				EmployeeEntity e=new EmployeeEntity();
				e.setId(rs.getString("uid"));
				result.add(e);
			}
			return result;
	}

	@Override
	public List<EmployeeEntity> selectsPm() throws Exception {
		Connection connection=DBUtil.getConnection();
		StringBuilder sql=new StringBuilder();
		 sql.append("SELECT u.uid FROM oa_emp u  where not exists")
		 .append("(select * from  atte_attendance s where s.emp_id=u.uid and)")
		 .append("and s.when_day= ? and s.isvalid=? and s.ampm=?");
		 	
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			int i=1;
			ps.setDate(i++,  new Date(System.currentTimeMillis()));
			ps.setInt(i++, 1);
			ps.setInt(i++, 1);
			ResultSet rs = ps.executeQuery();
			List<EmployeeEntity> result=new LinkedList<>();
			while(rs.next()) {				
				EmployeeEntity e=new EmployeeEntity();
				e.setId(rs.getString("uid"));
				result.add(e);
			}
			return result;
	}

	@Override
	public void updateById(String id, String reason) throws Exception {
		String sql="UPDATE atte_retroactive SET  apply_time=? ,reason=?  WHERE id = ? ";
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int i=1;
		ps.setDate(i++, new Date(System.currentTimeMillis()));
		ps.setString(i++, reason);
		ps.setString(i++, id);
			
		ps.executeUpdate();
		
	}

}
