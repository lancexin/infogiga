package cindy.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	/**
	 * yyyy-MM-dd HH:mm:ss example：2010-11-22 13:14:55
	 */
	public static SimpleDateFormat NOW_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat NOW_TIME2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * yyyy-MM-dd example：2010-01-02,2010-1-2
	 */
	public static SimpleDateFormat NOW_DATE = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * yyyyMMddHHmmsssss example：20101122134455666
	 */
	public static SimpleDateFormat STR_TIME = new SimpleDateFormat("yyyyMMddHHmmsssss");
	/**
	 * yyyy-MM example：2010-2
	 */
	public static SimpleDateFormat STR_NODATE = new SimpleDateFormat("yyyy-MM");
	/**
	 * EEE example：星期一
	 */
	public static SimpleDateFormat WEEK_TIME = new SimpleDateFormat("EEE");
	
	public static SimpleDateFormat TIME = new SimpleDateFormat("HH:mm:ss");
	
	public static SimpleDateFormat TIME_NOHOURS = new SimpleDateFormat("mm:ss");
	
	
	
	/**
	 * 获得Date的字符串显示
	 * @param format:NOW_TIME,NOW_DATE,STR_TIME,WEEK_TIME in DateUtil
	 * @param date Date
	 * @return
	 */
	public static String getDateString(Date date,SimpleDateFormat sdf){
		return sdf.format(date);
	}
	
	/**
	 * 获得当天Date的字符串显示
	 * @param format
	 * @return
	 */
	public static String getDateString(SimpleDateFormat format){
		Date date = new Date();
		return format.format(date);
	}
	
	/**
	 * 字符串显示转换为Date对象
	 * @param dateStr
	 * @param type
	 * @return
	 */
	public static Date stringToDate(String dateStr,SimpleDateFormat type){
		Date date = null;
		try {
			date = type.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return date;
		}
	}
	/**
	 * 得到这周周日的Date
	 * @param d
	 * @return
	 */
	public static Date getSunDay(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		return c.getTime();
	}
	
	/**
	 * 得到周日的Date
	 * @param d
	 * @return
	 */
	public static Date getSunDay(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		return c.getTime();
	}
	/**
	 * 得到周日的Date
	 * @param d
	 * @return
	 */
	public static Date getSunDay(String dateStr,SimpleDateFormat format){
		Date d = stringToDate(dateStr,format);
		return getSunDay(d);
	}
	/**
	 * 得到周六的Date
	 * @param d
	 * @return
	 */
	public static Date getSaturDay(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY); 
		return c.getTime();
	}
	/**
	 * 得到周六的Date
	 * @param d
	 * @return
	 */
	public static Date getSaturDay(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY); 
		return c.getTime();
	}
	/**
	 * 得到周六的Date
	 * @param d
	 * @return
	 */
	public static Date getSaturDay(String dateStr,SimpleDateFormat format){
		Date d = stringToDate(dateStr,format);
		return getSaturDay(d);
	}
	/**
	 * 得到月的第一天的Date
	 * @param d
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DATE,1);		
		return c.getTime();
	}
	/**
	 * 得到月的第一天的Date
	 * @param d
	 * @return
	 */
	public static Date getFirstDateOfMonth(String dateStr,SimpleDateFormat format){
		Date d = stringToDate(dateStr,format);
		return getFirstDateOfMonth(d);
	}
	
	/**
	 * 得到当月的最后一天的Date
	 * @param d
	 * @return
	 */
	public static Date getLastDateOfMonth(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE,1);
		c.add(Calendar.MONTH,1);//加一个月，变为下月的1号  
		c.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
		return c.getTime();
	}
	/**
	 * 得到月的最后一天的Date
	 * @param d
	 * @return
	 */
	public static Date getLastDateOfMonth(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DATE,1);
		c.add(Calendar.MONTH,1);//加一个月，变为下月的1号  
		c.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
		return c.getTime();
	}
	/**
	 * 得到月的最后一天的Date
	 * @param d
	 * @return
	 */
	public static Date getLastDateOfMonth(String dateStr,SimpleDateFormat format){
		Date d = stringToDate(dateStr,format);
		return getLastDateOfMonth(d);
	}
	
	

	
	/**
	 * 一天的开始
	 * @return
	 */
	public static Date getStartOfDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 得到一天的开始
	 * @param date  指定的天
	 * @return
	 */
	public static Date getStartOfDay(Date date) {		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
		
	/**
	 * 一天的结束
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**
	 * 一天的结束
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**
	 * 第二天的开始
	 * @return
	 */
	public static Date getNextDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	/**
	 * 第二天的开始
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	/**
	 * 一周的开始
	 * @param date
	 * @return
	 */
	public static Date getStartOfWeek(Date date) {
		Date startOfWeek = getSunDay(date);
		return getStartOfDay(startOfWeek);		
	}
	
	/**
	 * 一周的结束
	 * @param date
	 * @return
	 */
	public static Date getEndOfWeek(Date date) {
		Date endOfWeek = getSaturDay(date);
		return getEndOfDay(endOfWeek);
	}
	
	/**
	 * 上周的今天
	 * @return
	 */
	public static Date getLastWeek() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		return c.getTime();
	}
	
	/**
	 * 一月的开始
	 * @param date
	 * @return
	 */
	public static Date getStartOfMonth(Date date) {
		Date startOfMonth = getFirstDateOfMonth(date);
		return getStartOfDay(startOfMonth);
	}
	
	/**
	 * 一月的结束
	 * @param date
	 * @return
	 */
	public static Date getEndOfMonth(Date date) {
		Date endOfMonth = getEndOfMonth(date);
		return getEndOfDay(endOfMonth);
	}
	
	/**
	 * 得到从指定日期开始，到现在的天数。指定日期必须早于现在。
	 * @param year	年份
	 * @param month	月份，从1开始
	 * @param date	日期，从1开始
	 * @return  间隔的天数
	 */
	public static int computeDaysFrom(int year, int month, int date) {
		Calendar baseDay = Calendar.getInstance(); //基础日期
		Calendar now = Calendar.getInstance();//当前日期
		int betweenDays = 0;  //间隔天数
		int betweenYears = 0; //间隔年数
		baseDay.set(year, month-1, date);
		
		if(now.compareTo(baseDay) < 0) {
			return betweenDays;
		}
		
		betweenYears = now.get(Calendar.YEAR) - baseDay.get(Calendar.YEAR);
		betweenDays = now.get(Calendar.DAY_OF_YEAR) - baseDay.get(Calendar.DAY_OF_YEAR);
		
		for(int index = 0; index < betweenYears; index++) {
			betweenDays += baseDay.getActualMaximum(Calendar.DAY_OF_YEAR);			
			baseDay.set(Calendar.YEAR, year+1);
		}
		
		return betweenDays;
	}
	
	public static int getWeekCountOfMonth(Date d){
		Date startOfMonth = getFirstDateOfMonth(d);
		Date ehdOfMonth = getLastDateOfMonth(d);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startOfMonth);
		int startWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.setTime(ehdOfMonth);
		int endWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		return endWeek - startWeek+1;
	}
	
	public static int getWeekCountOfMonth(String dateStr,SimpleDateFormat format){
		return getWeekCountOfMonth(stringToDate(dateStr, format));
	}
	
	public static int getWeekNumberOfMonth(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public static int getWeekNumberOfMonth(String dateStr,SimpleDateFormat format){
		return getWeekNumberOfMonth(stringToDate(dateStr, format));
	}
	
	public static int getDayNumberOfWeek(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getDayNumberOfWeek(String dateStr,SimpleDateFormat format){
		return getDayNumberOfWeek(stringToDate(dateStr, format));
	}
	
}
