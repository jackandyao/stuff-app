package com.qbao.aisr.app.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理s
     * @author liaijun
     * 创建时间：2017年2月17日 下午5:38:16  
     * 描述：
     * $LastChangedBy: wangjigang $
     * 修改时间：2017年2月17日 下午5:38:16
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm) */
    public final static String DATEANDTIME_PATTERN = "yyyy-MM-dd HH:mm";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static String getYesday(String pattern) {
        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -1);    //得到前一天
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getTimeByType(String type) {
        String time = "09:00";
        if("1".equals(type)){
            time="09:00";
        }else if("2".equals(type)){
            time="13:00";
        }else if("3".equals(type)){
            time="19:00";
        }
        return time;
    }

    public static String getDateTimeByType(String type) {
        String time = format(new Date(),DATE_PATTERN)+" 09:00";
        if("1".equals(type)){
            time=format(new Date(),DATE_PATTERN)+" 09:00";
        }else if("2".equals(type)){
            time=format(new Date(),DATE_PATTERN)+" 13:00";
        }else if("3".equals(type)){
            time=format(new Date(),DATE_PATTERN)+" 19:00";
        }else{
            time="1970-01-01 00:00";
        }
        return time;
    }

    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat(DATEANDTIME_PATTERN);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() >= dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    int ______________________________________________;
    
    
	public static Date parse(String dateStr) {
		return parse(dateStr, DATE_TIME_PATTERN);
	}
	 
    public static Date parse(String dateStr, String pattern) {
        if(dateStr != null){
        	pattern = pattern.substring(0,dateStr.length());
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
				return df.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
        }
        return null;
    }
    
	public static Date lastDay(int days){
		return new Date(System.currentTimeMillis()-1000*3600*24*days);
	}
	
	public static Date recent(int seconds){
		return new Date(System.currentTimeMillis()-1000*seconds);
	}

	@Deprecated
	public static Date allday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	public static Date dayStart(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date dayEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dayStart(date));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	public static Date lastDayStart(){
		return lastDayStart(1);
	}
	
	public static Date lastDayStart(int n){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_MONTH, n*-1);
		return dayStart(calendar.getTime());
	}
	
	public static Date lastMonthStart(){
		return lastMonthStart(1);
	}
	
	public static Date lastMonthStart(int n){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.add(Calendar.MONTH, n*-1);
		return monthStart(calendar.getTime());
	}
	
	public static Date monthStart(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date monthEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(monthStart(date));
		calendar.add(Calendar.MONTH, 1);
	    calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}
    
}
