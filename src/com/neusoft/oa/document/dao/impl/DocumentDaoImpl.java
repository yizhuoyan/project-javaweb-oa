package com.neusoft.oa.document.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentEntity;

public class DocumentDaoImpl implements DocumentDao{

	@Override
	public void insert(DocumentEntity t) throws Exception {
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
	public DocumentEntity select(String ufield, Object value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentEntity> selectAllByColumn(String ufield, Object value, String... orderbys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DocumentEntity> selectAll(String... orderbys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<DocumentEntity> pageData) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
