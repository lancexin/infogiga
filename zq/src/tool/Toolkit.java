package tool;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import bean.ReportBean;
import bean.ResultBean;

/**
 * 工具包
 * @author ya
 */
public class Toolkit {
	
	private static final Logger log = Logger.getLogger(Toolkit.class.getName());
	
	/**
	 * 字符串转为字符数组流
	 * @param xmlMessage xml字符串
	 * @return 字符数组流
	 */
	public static InputStream String2Stream(String xmlMessage) {
		byte[] buffer = null;		
		if(!xmlMessage.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?>") && xmlMessage.length() < 40) {
			log.warn("xml文件格式不规范或长度不够");
			return null;
		}
		try {
			buffer = xmlMessage.getBytes("GBK");			
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换异常");
		}
		
		InputStream in = new ByteArrayInputStream(buffer);
		return in;
	}
	
	/**
	 * 编码转换
	 * @param sourceString 源字符串
	 * @param sourceCode  源编码
	 * @param destCode 目的编码
	 * @return 转码后的字符串
	 */
	public static String encode(String sourceString, String sourceCode, String destCode) {
		String destString = "";
		
		try {
			if("".equals(sourceCode)) {
				destString =  new String(sourceString.getBytes(), destCode);
			}
			else {
				destString =  new String(sourceString.getBytes(sourceCode), destCode);
			}
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换异常");
		}
		return destString;
	}
	
	/**
	 * 获取指定的编码的字符串
	 * @param sourceString 源字符串
	 * @param sourceCode 源编码
	 * @return 转码后的字符串
	 */
	public static String encode(String sourceString, String sourceCode) {
		String destString = "";
		
		try {
			destString =  new String(sourceString.getBytes(sourceCode));
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换异常");
		}
		return destString;
	}
	
	/**
	 * 当前时间转换为字符串
	 * @return 形如20090611190915625格式的字符串
	 */
	@SuppressWarnings("static-access")
	public static String getNow() {
		Calendar c = Calendar.getInstance();
		StringBuffer buffer = new StringBuffer(c.get(c.YEAR)+"");
		buffer.append(getResult(c.get(c.MONTH)+1));
		buffer.append(getResult(c.get(c.DAY_OF_MONTH)));
		buffer.append(getResult(c.get(c.HOUR_OF_DAY)));
		buffer.append(getResult(c.get(c.MINUTE)));
		buffer.append(getResult(c.get(c.SECOND)));
		buffer.append(getResult2(c.get(c.MILLISECOND)));
		return buffer.toString();
	}
	
	/**
	 * 获取统计结果集s
	 * @param list
	 * @return
	 */
	public static ArrayList<ResultBean> getHeaderList(ArrayList<ReportBean> list) {
		ArrayList<ResultBean> resultList = new ArrayList<ResultBean>();
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		Enumeration<String> e = null;
		String[] names = new String[list.size()];
		
		for(int i=0; i<list.size(); i++) {
			names[i] = list.get(i).getOperateName();
		}
		for(int i=0; i<list.size(); i++) {
			insertData(table, names[i]);
		}
		
		e = table.keys();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			resultList.add(new ResultBean(key, table.get(key)));
		}
		return resultList;
	}	
	
	/**
	 * 填充hashtable
	 * @param table
	 * @param name
	 */
	private static void insertData(Hashtable<String, Integer> table, String name) {		
		if (table.containsKey(name)) { //如果包含这个key，则他的值加1
			table.put(name, table.get(name)+1);
		}
		else {
			table.put(name, 1);
		}
	}
	
	/**
	 * 如果是null串，则转为-
	 * @param str 字符串
	 * @return 转换后的字符串
	 */
	public static String null2String(String str) {
		if(str == null) {
			return "-";
		}
		return str;
	}
	
	/**
	 * 如果是null串，则转为空串
	 * @param str 可能为null的字符串
	 * @return 转换后的字符串
	 */
	public static String null2Blank(String str) {
		if(str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 验证message的合法性
	 * @param message 消息字符串
	 * @return 是否合法
	 */
	public static boolean check(String message) {
		String[] values = message.split("-");
		
		if(values.length < 6
				|| values[0].length() != 12
				|| values[1].length() != 4
				|| values[2].length() != 4
				|| values[3].length() != 8
				|| values[4].length() != 7
				|| values[5].split("_")[1].equals("Close")
				|| values[5].split("_")[1].equals("Initializtion")) {
			return false;
		}
		return true;
	}
	
	/**
	 * 字符串转化为时间格式
	 * @param str 给定字符串
	 * @return 形如2009-06-11 19:09:16的字符串
	 */
	public static String String2Time(String str) {
		StringBuffer time = new StringBuffer("");
		
		if(str == null) {
			return "-";
		}
		time.append(str.substring(0, 4)).append("-").append(str.substring(4, 6)).append("-").append(
				str.substring(6, 8)).append(" ").append(str.substring(8, 10)).append(":").append(
						str.substring(10, 12)).append(":").append(str.substring(12, 14));
		return time.toString();
	}
	
	/**
	 * 从字符串中，截取指定字符串之后的字符串
	 * @param sourceString  待处理的字符串
	 * @param cutString   截掉的字符串
	 * @return 截取之后的字符串
	 */
	public static String cut(String sourceString, String cutString) {
		String destString = "";
		destString = sourceString.substring(sourceString.indexOf(cutString)+ cutString.length());
		return destString;
	}
	
	/**
	 * KB单位的流量格式化为MB或GB
	 * @param n 流量字符串
	 * @return 带单位的流量字符串
	 */
	public static String format(String n) {
		float flux = Float.parseFloat(n);
		String traffix = "";
		traffix = flux < 1024?flux+"KB":(
				flux < 1024*1024?keepCarry(flux/1024, 1)+"MB":(
						flux < 1024*1024*1024?keepCarry(flux/1024/1024, 1)+"GB":"NaN"));
		return traffix;
	}	
	
	/**
	 * 获取当前的年月日
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getToday() {
		Calendar c = Calendar.getInstance();
		StringBuffer buffer = new StringBuffer(c.get(c.YEAR)+ "年");
		buffer.append((c.get(c.MONTH)+1)+"月");
		buffer.append(c.get(c.DATE)+ "日");
		
		return buffer.toString();
	}
	
	/**
	 * 
	 * @param period 0:起始，1:终止
	 * @return
	 */
	public static String getToday(int period) {
		Calendar c = Calendar.getInstance();
		String date = "";
		StringBuffer buffer = new StringBuffer(c.get(Calendar.YEAR));
		buffer.append((c.get(Calendar.MONTH)+1));
		buffer.append(c.get(Calendar.DATE));
		
		switch (period) {
			case 0: date=buffer.append("000000000").toString();break;
			case 1: date=buffer.append("235959999").toString();break;
		}
		return date;
	}
	
	/**
	 * 保留n位的进位
	 * @param f  float值
	 * @param n  进位数
	 * @return 进位后的字符串
	 */
	private static String keepCarry(float f, int n) {		
		double param = Math.pow(10, (double)n);//10的n次方
		double value = Math.round(f * param)/param;
		return Double.toString(value);
	}

	/**
	 * 是个位数就加0
	 * @param n 数值
	 * @return 字符串
	 */
	private static String getResult(int n) {
		return n>9?n+"":"0"+n;
	}
	
	/**
	 * 毫秒，3位数
	 * @param n 数值
	 * @return 字符串
	 */
	private static String getResult2(int n) {
		return n>99?n+"":"0"+getResult(n);
	}
}
