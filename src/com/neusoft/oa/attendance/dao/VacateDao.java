package com.neusoft.oa.attendance.dao;

import java.util.List;


import com.neusoft.oa.attendance.entity.VacateEntity;
import com.neusoft.oa.core.dao.TemplateDao;

/**
 * @author 田梦源
 *
 */

public interface VacateDao  extends TemplateDao<VacateEntity> {
	int selectsByKey(String id,String key,int pageNo,int pageSize,List<VacateEntity> pageData)throws Exception;
}
