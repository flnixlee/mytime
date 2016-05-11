package com.lift.framework.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 时间转换类
 * 
 * @author 
 * 
 */
public final class DateTimeUtil {
	private DateTimeUtil() {
	}

	public static String formatDate = "yyyy-MM-dd";
	public static String formatDateTime = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 字符串时间 转换成时间类型yyyy-MM-dd
	 *  
	 * 
	 * @return 
	 */
	public static Date getFormatDate(String time,String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 时间按照字符串格式输出
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getDateTime(Date time,String format){  
		if(time!=null && format!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);  
			String strTime=dateFormat.format(time); 
			return strTime;  
		}else{
			return null;  
		}
	}  
	
	/**
	 * 时间计算，获取yyyy-mm-dd(date) 之后day天的日期
	 * @param datestr
	 * @param day
	 * @return
	 */
	public static java.util.Date getBeforeAfterDateDay(Date date, int day) {  
       
        Calendar cal = new GregorianCalendar();  
        cal.setTime(date);  
  
        int Year = cal.get(Calendar.YEAR);  
        int Month = cal.get(Calendar.MONTH);  
        int Day = cal.get(Calendar.DAY_OF_MONTH);  
  
        int NewDay = Day + day;  
  
        cal.set(Calendar.YEAR, Year);  
        cal.set(Calendar.MONTH, Month);  
        cal.set(Calendar.DAY_OF_MONTH, NewDay);  
  
        return new Date(cal.getTimeInMillis());  
    }
	
	/**
	 * 时间计算，获取yyyy-mm-dd(date) 之后12月
	 * @param datestr
	 * @param day
	 * @return
	 */
	public static java.util.Date getBeforeAfterDateMonth(Date date, int month) {  
       
        Calendar cal = new GregorianCalendar();  
        cal.setTime(date);  
  
        int Year = cal.get(Calendar.YEAR);  
        int Month = cal.get(Calendar.MONTH);  
        int Day = cal.get(Calendar.DAY_OF_MONTH);  
  
        int NewMonth = Month + month;  
  
        cal.set(Calendar.YEAR, Year);  
        cal.set(Calendar.MONTH, NewMonth);  
        cal.set(Calendar.DAY_OF_MONTH, Day);  
  
        return new Date(cal.getTimeInMillis());  
    }
	/**
	 * 计算二个时间间隔时间（天）保留一位小数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String getQuot(Date startDate, Date endDate) {
		String retQuot = "";
		try {
			long timeout = endDate.getTime() - startDate.getTime();
			double quot = 0.0;
			quot = ((double)timeout) / 1000 / 60 / 60 / 24;
			
			DecimalFormat formater = new DecimalFormat();
	        formater.setMaximumFractionDigits(1);
	        formater.setGroupingSize(0);
	        formater.setRoundingMode(RoundingMode.FLOOR);
	        retQuot = formater.format(quot);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retQuot;
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	public static void main(String[] args) {
		
	}
}