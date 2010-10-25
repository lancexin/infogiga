package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.infogiga.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;

public class TestDate {
	public static void main(String[] args) throws ParseException {
		/*String time = "2010-1-1";
		SimpleDateFormat  dateFormat=new SimpleDateFormat ("yyyy-MM-dd");

			Date dateNow=dateFormat.parse(time);
		 System.out.println(dateNow.toLocaleString());*/
		
	//	DateUtils du = DateUtils.
		
		/*Date date = new Date();
		Date d = DateUtil.getSunDay(date);
		Date dd = DateUtil.getSaturDay(date);
		System.out.println(d.toLocaleString());
		System.out.println(dd.toLocaleString());*/
		
		/*
		Date date = new Date();
		Date d = DateUtil.getFirstDateOfMonth(date);
		Date dd = DateUtil.getLastDateOfMonth(date);
		System.out.println(d.toLocaleString());
		System.out.println(dd.toLocaleString());*/
	/*	String time = "2010-1";
		SimpleDateFormat  dateFormat=new SimpleDateFormat ("yyyy-MM");
		Date dateNow=dateFormat.parse(time);
		System.out.println(dateNow.toLocaleString());*/
		
		/*String time = "2010-2-28";
		SimpleDateFormat  dateFormat=new SimpleDateFormat ("yyyy-MM-dd");
		Date date=dateFormat.parse(time);
		System.out.println(date.getDay());*/
		
		//Date date = new Date();
		/*String time = "2010-1-2";
		Date date = DateUtil.stringToDate(time, DateUtil.NOW_DATE);
		int weekCount = DateUtil.getWeekCountOfMonth(date);
		System.out.println(weekCount);
		
		int weekNumber = DateUtil.getWeekNumberOfMonth(date);
		System.out.println(weekNumber);
		
		int dayNumber = DateUtil.getDayNumberOfWeek(date);
		System.out.println(dayNumber);*/
		Date date = new Date();
		
		System.out.println(date.getTime());
	}
}
