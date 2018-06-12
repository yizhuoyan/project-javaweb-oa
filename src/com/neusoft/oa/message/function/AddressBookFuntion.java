package com.neusoft.oa.message.function;

import java.util.List;

import com.neusoft.oa.message.entity.AddressBookEntity;

public interface AddressBookFuntion {
	List<AddressBookEntity> searchEmailAddressBookByKey(String key)throws Exception;
	int searchTotal(String key, int pageNo, int pageSize, List<AddressBookEntity> pageData)throws Exception;
}
