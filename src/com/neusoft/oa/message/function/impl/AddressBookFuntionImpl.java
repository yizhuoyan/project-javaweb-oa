package com.neusoft.oa.message.function.impl;

import java.util.List;

import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.message.dao.AddressBookDao;
import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.function.AddressBookFuntion;

public class AddressBookFuntionImpl implements AddressBookFuntion{

	@Override
	public List<AddressBookEntity> searchEmailAddressBookByKey(String key) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		AddressBookDao dao=DaoFactory.getDao(AddressBookDao.class);
		List<AddressBookEntity> AddressBook=dao.selectEmailAddressBookByKey(key);
		return AddressBook;
	}

	@Override
	public int searchTotal(String key, int pageNo, int pageSize, List<AddressBookEntity> pageData) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		AddressBookDao dao=DaoFactory.getDao(AddressBookDao.class);
		int total=dao.selectsByKey(key, pageNo, pageSize, pageData);
		return total;
	}

   
}
