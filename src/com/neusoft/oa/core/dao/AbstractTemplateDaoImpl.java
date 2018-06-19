package com.neusoft.oa.core.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractTemplateDaoImpl<T> extends SQLExecutor implements TemplateDao<T> {

	public Connection getCurrentConnection() throws SQLException {
		return DBUtil.getConnection();
	}

	public Connection getNewConnection() throws SQLException {
		return DBUtil.getNewConnection();
	}
	
}
