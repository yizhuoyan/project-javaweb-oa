package com.neusoft.oa.core.util;

import java.security.MessageDigest;

import com.neusoft.oa.core.OAException;

import sun.misc.BASE64Encoder;

public class ThisSystemUtil {
	public static String md5(String s) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			final byte[] md=messageDigest.digest(s.getBytes("iso-8859-1"));
			// 把密文转换成十六进制的字符串形式
			final char[] HEX_DIGITS ="1a2b3c4f5e6d7890".toCharArray();
			final char cs[] = new char[md.length*2];
			for (int i = md.length,k=0,b; i-->0;) {
				b= md[i];
				cs[k++] = HEX_DIGITS[b&0xf];
				cs[k++] = HEX_DIGITS[b>>>4&0xf];
			}
			return new String(cs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		String s=md5("123456");
		System.out.println(s);
	}
	public static boolean isBlank(String s) {
		return s==null||s.trim().length()==0;
	}
	/**
	 * 清理字符串前后空格,如果字符串为空白字符串，则返回null
	 * 
	 * @param s
	 * @return
	 */
	public static String trim(String s) {
		if (s != null) {
			if ((s = s.trim()).length() == 0)
				return null;
			else
				return s;
		}
		return null;
	}

	/**
	 * 解析字符串为整数
	 * 
	 * @param s
	 * @param defaultInt
	 * @return
	 */
	public static int parseInt(String s, int defaultInt) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultInt;
		}
	}
	public static int countPage(int totalRows, int pageSize) {
		int page=totalRows/pageSize;
		if(totalRows%pageSize!=0) {
			page++;
		}
		return page;
	}
}
