package com.neusoft.oa.core.email;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

public class SendEmailUtil {
	private final static ExecutorService es=Executors.newCachedThreadPool();
	private static final Properties config=new Properties();
	static {
		init();
	}
	private static void loadConfigFile() {
		final String path="/system.config";
		try {
			System.getProperties().load(SendEmailUtil.class.getResourceAsStream(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void init() {
		
		Properties prop=System.getProperties();
		for(String key:prop.stringPropertyNames()) {
			if(key.startsWith("email.")) {
				config.setProperty(key.substring(6), prop.getProperty(key));
			}
		}
	}
	
	public static void sendTextEmail(TextEmail te) throws Exception{
		String account=config.getProperty("server.account");
		String password=config.getProperty("server.password");
		MimeMessage m=createEmail(account,password,te);
		Session session=m.getSession();
		session.setDebug(true);
		Transport.send(m);
	}
	public static void main(String[] args) throws Exception{
		loadConfigFile();
		TextEmail m=new TextEmail();
		m.setContent("xxx");
		m.setRecipientAccount("ikeepglove@136.com");
		m.setSubject("xxxx");
		sendTextEmail(m);
	}
	private static MimeMessage createEmail(final String account,final String password,TextEmail te)throws Exception {
		Session session=Session.getInstance(config,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				System.out.println(account);
				return new PasswordAuthentication(account, password);
			}
		});
		//1 创建邮件对象
		MimeMessage m=new MimeMessage(session);
		//2 设置发件人
		Address from=new InternetAddress(account,te.getFromName(),"utf-8");
		m.setFrom(from);
		//3 设置收件人
		m.setRecipient(RecipientType.TO,new InternetAddress(te.getRecipientAccount()));
		//4 设置超送人
		//m.setRecipient(RecipientType.CC,new InternetAddress("yizhuoyan1@139.com"));
		//5 设置密送人
		//m.setRecipient(RecipientType.BCC,new InternetAddress("yizhuoyan1@139.com"));
		//6 邮件的主题
		m.setSubject(te.getSubject(),"utf-8");
		//7 邮件正文
		m.setContent(te.getContent(), "text/plain;charset=utf-8");
		//8 发送时间
		m.setSentDate(new Date());
		//9确认设置完毕
		m.saveChanges();
		return m;
	}
}

