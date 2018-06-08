package com.neusoft.oa.attendance.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.neusoft.oa.attendance.dao.ManagerAtteCountDao;
import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.util.ThisSystemUtil;

public class ManagerAtteCountDaoImpl implements ManagerAtteCountDao {

	

	@Override
	public int selectsAtteCount(String ManagerId,int mouth, String key,int pageNo,int pageSize,List<AtteCountEntity> pageData)
			throws Exception {
			//验证参数
			//加载驱动			
			//获取数据库连接
			//开启事物			
			List<AtteCountEntity> result = new LinkedList<>();
			//try-with-release-resource
			try (Connection connection = (Connection) DBUtil.getConnection()) {
				// 3创建预编译语句对象PreparedStatement
				StringBuilder whereSql = new StringBuilder();
				whereSql.append(" from atte_count m ")
				.append(" join oa_emp s")
				.append(" on m.emp_id=s.id")
				.append("where id="+ManagerId)
				.append("where mouth="+mouth)
				.append(" and");
				if(key!=null) {
					whereSql.append(" (tardycount like ? or latecount like  ?or vacatecount like  ?)");
				}
				
				PreparedStatement ps = connection.prepareStatement("select m.*,s.department_id "+whereSql);
				if(key!=null) {
					//key还需要转义 %
					key=key.replaceAll("%","\\%");
					key="%"+key+"%";
					ps.setString(1, key);
					ps.setString(2, key);
					ps.setString(3, key);
				}
				// 4传参并执行语句executeQuery
				ResultSet rs = ps.executeQuery();
				int total=0;
				if (rs.next()) {
					total=rs.getInt(1);
				}
				rs.close();
				if(total==0) {
					return 0;
				}
				ps.close();
				//算出总页面
				int totalPage=ThisSystemUtil.countPage(total, pageSize);
				//如果页面大于总记录数，则不进行查询
				if(pageNo>totalPage) {
					return total;
				}
				//进行分页查询
				whereSql.append(" order by showorder limit ")
				.append((pageNo-1)*pageSize)
				.append(",")
				.append(pageSize);
				
				ps = connection.prepareStatement("select * "+whereSql);
				if(key!=null) {
					//key还需要转义 %
					key=key.replaceAll("%","\\%");
					key="%"+key+"%";
					ps.setString(1, key);
					ps.setString(2, key);
					ps.setString(3, key);
				}
				// 4传参并执行语句executeQuery
				rs = ps.executeQuery();
				while (rs.next()) {
					pageData.add(row2bean(rs));
				}
				return total;
			}
			
			
		}
		
	
	
	
	protected AtteCountEntity row2bean(ResultSet rs) throws SQLException {
		AtteCountEntity u = new AtteCountEntity();
	
		u.setEmpId(rs.getString("empId"));
		u.setMouth(rs.getInt("mouth"));
		u.setLatecount(rs.getInt("latecount"));
		u.setTardycount( rs.getInt("tardycount"));
		u.setRetroactioncount(rs.getInt("Retroactioncount"));
		u.setVacatecount(rs.getInt("vacatecount"));		
		return u;
	}




	@Override
	public void insert(AtteCountEntity t) throws Exception {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void delete(String ufield, Object value) throws Exception {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void update(Serializable id, Map<String, Object> columns) throws Exception {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void update(Serializable id, String column, Object value) throws Exception {
		// TODO Auto-generated method stub
		
	}




	@Override
	public AtteCountEntity select(String ufield, Object value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<AtteCountEntity> selectAllByColumn(String ufield, Object value, String... orderbys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public List<AtteCountEntity> selectAll(String... orderbys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
