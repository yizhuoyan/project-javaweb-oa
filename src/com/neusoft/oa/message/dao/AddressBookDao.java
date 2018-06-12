package com.neusoft.oa.message.dao;

import java.util.List;
import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.message.entity.AddressBookEntity;

public interface AddressBookDao extends TemplateDao<AddressBookEntity>{
	 List<AddressBookEntity> selectEmailAddressBookByKey(String key)throws Exception;
	 int selectsByKey(String key, int pageNo, int pageSize, List<AddressBookEntity> pageData) throws Exception;
}
