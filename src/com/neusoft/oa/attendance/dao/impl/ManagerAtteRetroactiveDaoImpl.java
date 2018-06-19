package com.neusoft.oa.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.attendance.dao.ManagerAtteRetroactiveDao;
import com.neusoft.oa.attendance.entity.AtteRetroactiveEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;

public class ManagerAtteRetroactiveDaoImpl extends TemplateDaoImpl<AtteRetroactiveEntity> implements ManagerAtteRetroactiveDao {

	protected ManagerAtteRetroactiveDaoImpl() {
		super("atte_retroactive");
		
	}

	@Override
	public String selectsDepartmentId(String managerid) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 3创建预编译语句对象PreparedStatement
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from oa_emp  ")		
		.append(" where id= ? ");
		PreparedStatement ps = connection.prepareStatement("select department_id "+whereSql);
		ps.setString(1, managerid);
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		while(rs.next()){      //这里必须循环遍历
			String result = rs.getString("department_id");//返回一条记录			
			return result;
			}
		
		return null;
		
		
	}


	@Override
	public int selectsAtteRetroactive(String departmentid, String key, int pageNo, int pageSize,
			List<AtteRetroactiveEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 3创建预编译语句对象PreparedStatement
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from atte_retroactive m ")
		.append(" join oa_emp s ")
		.append(" on m.emp_id=s.id ");
		
		whereSql.append(" where s.department_id = ?")
		.append(" and result= ? ");
		
		PreparedStatement ps = connection.prepareStatement("select count(*) "+whereSql);
		
		ps.setString(1, departmentid);
		ps.setString(2, "0");
		
		// 4传参并执行语句executeQuery
		ResultSet rs = ps.executeQuery();
		//分页查询获取总行数
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
		querySql.append(" select e.*,u.*");
		querySql.append(" from ").append(" oa_emp e join atte_retroactive  u on e.id=u.emp_id ")
		.append("where e.department_id = ? ")
		.append(" and u.result=? ");
		if (key != null) {					
			
			querySql.append(" and ( u.when like ? or u.ret_time like ? or u.reason like  ? ) ");
		}
		querySql.append(" order by e.id desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		ps.setString(1, departmentid);
		ps.setString(2, "0");
		if (key != null) {
			int i = 3;
			key = "%" + key + "%";
			
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
			
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			AtteRetroactiveEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}
	

	@Override
	public void insert(AtteRetroactiveEntity t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	protected AtteRetroactiveEntity resultset2entity(ResultSet rs) throws Exception {
		AtteRetroactiveEntity u = new AtteRetroactiveEntity();
		u.setRetTime(new Date(rs.getTimestamp("ret_time").getTime()));
		u.setReason(rs.getString("reason"));
		u.setResult(rs.getInt("result"));		
		u.setWhen(new Date(rs.getTimestamp("when").getTime()));		
		u.setRemark(rs.getString("remark"));
		return u;		
	}


}
