package com.neusoft.oa.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class IDCard {
	private  String idcard;
	private  Boolean male;
	private  LocalDate birthDay;
	private  int age=-1;
	private IDCard(String idcard) {
		super();
		this.idcard = idcard;
	}
	
	public static IDCard of(String idcard) {
		if(!isValid(idcard)) {
			throw new IllegalArgumentException("dicard无效");
		}
		return new IDCard(idcard);
	}
	 
	private static final int[] MODULUS17= {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
	public static boolean isValid(String idcard) {
		if(idcard.length()!=18)return false;
		//1-17位系数
		//求1-17位每一位和系数相乘之和
		int sum=0;
		for(int i=idcard.length()-1;i-->0;){
			sum+=(idcard.charAt(i)-'0')*MODULUS17[i];
		}
		//除11求余验证
		return "10X98765432".charAt(sum%11)==idcard.charAt(17);
	}

	public boolean isMale() {
		if(male==null) {
			male=(idcard.charAt(16)-'0')%2!=0;
		}
		return male;
	}
	


	public LocalDate getBirthDay() {
		if(birthDay==null) {
			String birthString=idcard.substring(6, 6+8);
			birthDay=LocalDate.parse(birthString, DateTimeFormatter.BASIC_ISO_DATE);
		}
		return birthDay;
	}


	public int getAge() {
		if(age==-1) {
			long liveTime=TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())-this.getBirthDay().toEpochDay();
			age=(int)(liveTime/365);
		}
		return age;
	}





	public String getIdcard() {
		return idcard;
	}
}
