package com.neusoft.oa.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface TemplateDao<T> {

	void insert(T t)throws Exception;
	void delete(String ufield,Object value)throws Exception;
	void update(Serializable id,Map<String,Object> columns)throws Exception;
	void update(Serializable id,String column,Object value)throws Exception;
	T select(String ufield,Object value)throws Exception;
	List<T> selectAllByColumn(String ufield,Object value,String... orderbys)throws Exception;
	boolean exist(String ufield,Object value)throws Exception;
	List<T> selectAll(String... orderbys)throws Exception;
	
	
}
