/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.core.dao.TemplateDao;

public interface AdminSetDateDao extends TemplateDao<AdminSetDateEntity>{

	void updateTime(String ufield,Object value,Map<String, Object> columns)throws Exception;

	int selectsByKey(String key, int pageNo, int pageSize, List<AdminSetDateEntity> pageData)throws Exception;


}
