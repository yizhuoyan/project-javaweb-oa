package com.neusoft.oa.message.dao;

import java.util.List;

import com.neusoft.oa.message.entity.AddressBookEntity;
import com.neusoft.oa.message.entity.EmailEntity;

public interface EmailDao {
  void insertEmail(EmailEntity emailEntity) throws Exception;
  List<AddressBookEntity> selectEmailAddressBookByKey(String key)throws Exception;
  int selectsByKey(String key, int pageNo, int pageSize, List<AddressBookEntity> pageData) throws Exception;
}
