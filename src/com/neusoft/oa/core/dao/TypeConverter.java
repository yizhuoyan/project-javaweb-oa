package com.neusoft.oa.core.dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public interface TypeConverter {
	

	
	default Timestamp utilDate2timestamp(java.util.Date d ) {
		if (d != null) {
			return  new Timestamp(d.getTime());
		} 
		return null;
	}
	default Timestamp instant2timestamp(Instant instant) {
		if (instant != null) {
			return  new Timestamp(instant.getEpochSecond()*1000);
		} 
		return null;
	}
	default Instant timestamp2Instant(Timestamp timestamp ) {
		if (timestamp != null) {
			return  timestamp.toInstant();
		} 
		return null;
	}
	default LocalDate sqlDate2LocalDate(java.sql.Date d) {
		if (d != null) {
			return  d.toLocalDate();
		} 
		return null;
	}
	default java.sql.Date localDate2sqlDate(LocalDate localDate) {
		if (localDate != null) {
			return  java.sql.Date.valueOf(localDate);
		} 
		return null;
	}
	default LocalTime  sqlTime2LocalTime(java.sql.Time time) {
		if(time!=null) {
			return time.toLocalTime();
		}
		return null;
	}
	default java.sql.Time  localTime2sqlTime(LocalTime time) {
		if(time!=null) {
			return Time.valueOf(time);
		}
		return null;
	}
	
}
