package org.josvaldor.prospero.helios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Measurement {
	public Calendar calendar;
	Map<String,String> map;
	private static String defaultTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	public Measurement(Calendar calendar, String key, String value){
		this.calendar = calendar;
		this.map = new HashMap<String,String>();
		this.map.put(key, value);
	}
	
	public Measurement(String dateTime, String key, String value){
		this.calendar = this.getCalendar(null, dateTime);
		this.map = new HashMap<String,String>();
		this.map.put(key, value);
	}
	
	public GregorianCalendar getCalendar(String format, String time) {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = this.getDate(format, time);
		if (date != null)
			calendar.setTime(date);
		return calendar;
	}
	
	public Date getDate(String format, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat((format == null) ? defaultTimeFormat : format);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
