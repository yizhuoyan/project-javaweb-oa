package com.neusoft.oa.message.dao;


import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.message.entity.EmailEntity;

public interface EmailDao extends TemplateDao<EmailEntity>{
  void insertEmail(EmailEntity emailEntity) throws Exception;
  int deletEmailToRecycleBin(String emailId)throws Exception;
  int updateRecoverEmail(String emailId)throws Exception;
  int updateCompletelyDelete(String emailId)throws Exception;
  int selectsByKey(String userId,String key, int pageNo, int pageSize, List<EmailEntity> pageData) throws Exception;
  public int selectsSentAndReceivedEmailByKey(String user, String key,int pageNo,int pageSize,List<EmailEntity> pageData)throws Exception;
  public int selectsSentEmailByKey(String sender, String key,int pageNo,int pageSize,List<EmailEntity> pageData)throws Exception;

  public int selectsReceivedEmailByKey(String recipient, String key,int pageNo,int pageSize,List<EmailEntity> pageData)throws Exception;
  public int selectsAllEmailByKey(String key,int pageNo,int pageSize,List<EmailEntity> pageData)throws Exception;
  int selectsEmailInDraftBinsByKey(String recipient,String key, int pageNo, int pageSize, List<EmailEntity> pageData) throws Exception;
  EmailEntity selectUnreadEmail(String emailId)throws Exception;
  int updateSendEmailById(String emailId)throws Exception;
  EmailEntity selectEmailById(String emailId)throws Exception;
  String selectEmailSaveAddress(String emailID) throws Exception;

} 
