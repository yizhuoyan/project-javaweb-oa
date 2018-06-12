package com.neusoft.oa.document.log.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.log.dao.DocumentLogDao;
import com.neusoft.oa.document.log.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.log.entity.DocumentLogEntity;
import com.neusoft.oa.document.log.function.DocumentLogAO;

import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DocumentLogDaoImpl extends TemplateDaoImpl<DocumentLogEntity> implements DocumentLogDao {

	final String DATEFORMAT = "yyyy-MM-dd";

	public static void main(String[] args) {

	}

	protected DocumentLogDaoImpl() {
		super("log_document");
		// TODO Auto-generated constructor stub
	}

	// 操作 1放入回收站2创建3下载4还原5彻底删除
	@Override
	public void insert(DocumentLogEntity t) throws Exception {

		Connection con = DBUtil.getConnection();
		String sql = DBUtil.generateInsertSql(tableName, "id,target,operation,operationTime,operator_id,content");
		// 根据操作类型生成操作具体内容
		try (PreparedStatement ps = con.prepareStatement(sql.toString());) {

			int i = 1;
			ps.setString(i++, t.getId());
			ps.setString(i++, t.getTarget().getId());
			ps.setLong(i++, t.getOperation());
			ps.setString(i++, t.getOperationTime());
			ps.setString(i++, t.getOperator().getId());
			ps.setString(i++, t.getContent());

			ps.executeUpdate();
		}
	}

	// 分页查询
	@Override
	public int selectsByDocumentlog(DocumentLogAO condition, int pageNo, int pageSize, List<DocumentLogEntity> pageData)
			throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql

		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append( this.tableName ).append(" where 1=1 ");

		List<Object> parameters = new ArrayList();

		// .append(SysUserEntity.FLAG_DELETED);
		if (condition != null) {
			String operatorId = condition.getOperatorId();
			if (operatorId != null) {
				whereSql.append(" and operator_Id =? ");
				parameters.add(operatorId);
			}
			String operationType = condition.getOperation();
			if (operationType != null) {
				whereSql.append(" and operation =? ");
				parameters.add(operationType);
			}
			String attamentid = condition.getTarget();
			if (attamentid != null) {
				whereSql.append(" and target =? ");
				parameters.add(attamentid);
			}


			java.sql.Date begin = string2date(condition.getBeginoperationTime(), DATEFORMAT);
			java.sql.Date end = string2date(condition.getEndoperationTime(), DATEFORMAT);

			if (begin != null && end == null) {
				whereSql.append(" and operationTime >= ?");
				parameters.add(begin);

			} else if (begin == null && end != null) {
				whereSql.append(" and operationTime <= ?");
				parameters.add(end);

			} else if (begin != null && end != null) {
				whereSql.append(" and operationTime between ? and ?");
				parameters.add(begin);
				parameters.add(end);
			}

		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		if (parameters != null) {

			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}

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
		querySql.append(" order by operationTime desc ");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (parameters != null) {

			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}

		}
		System.out.println(querySql.toString());
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();

		// 5转换结果为实体
		while (rs.next()) {
			DocumentLogEntity e = resultset2entity(rs);
			System.out.println(e.getOperation());
			pageData.add(e);
		}
		return total;
	}

	@Override
	protected DocumentLogEntity resultset2entity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub

		String operateorId = rs.getString("operator_id");
		EmployeeEntity ee = new EmployeeEntity();
		ee.setId(operateorId);
		
		String target = rs.getString("target");
		DocumentAttachmentEntity dae =new DocumentAttachmentEntity();
		dae.setId(target);
		
		DocumentLogEntity de = new DocumentLogEntity();
		de.setTarget(dae);
		de.setOperationTime(rs.getString("operationTime"));
		de.setOperation(rs.getInt("operation"));
		de.setOperator(ee);
		de.setContent(rs.getString("content"));

		return de;
	}

	protected java.sql.Date string2date(String time, String dateFormat) throws java.text.ParseException {
		if (time == null||time.length() ==0) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = sdf.parse(time);

		return new java.sql.Date(date.getTime());

	}

}
