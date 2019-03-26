package com.demo.javase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 */
public final class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	public static final String BUNDLE_KEY = "ApplicationResources";
	public static final String FORMAT_STRING_24 = "yyyy-MM-dd HH:mm:ss";//24小时制
	public static final String FORMAT_STRING_12 = "yyyy-MM-dd hh:mm:ss";//12小时制
	public static final String FORMAT_YYYYDD = "yyyyMM";//年月
    public static final String FMT_DATE = "yyyy-MM-dd";
    public static final String FMT_DATE_ = "yyyyMMdd";
    public static final String FMT_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FMT_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FMT_MONTH = "yyyy-MM";
    public static final String FMT_SECOND_INT = "yyyyMMddHHmmss";

    private static Map<String, Integer> workDayMap = new HashMap<String, Integer>();//月工作日天数
    private static List<String> holidayList = new ArrayList<String>();//节假日list
    private static List<String> dayOffList = new ArrayList<String>();//调休list

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(BUNDLE_KEY, locale)
					.getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

    /**
     * 将日期格式化指定字符类型
     */
    public static String getDateByType(Date aDate,String type) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(type);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 * @see SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static Date convertDate(String strDate) {
		Date date=null;
		try {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.parse(strDate);
		} catch (ParseException pe) {
			log.error("ParseException: " + pe);
		}
		return (date);
	}
    /**
     * 将指定字符类型格式化成日期
     */
    public static Date convertDateByType(String strDate,String type) {
        Date date=null;
        try {

            SimpleDateFormat df = new SimpleDateFormat(type);
            date = df.parse(strDate);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
        }
        return (date);
    }






    /**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * @see SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}



	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}




    /**
     * 从一个日期取得间隔一段时间的另一个日期
     * @param date
     *           参照日期，从该日期开始计算
     * @param distance
     *           间隔时间量，- 表示之前，+ 表示之后
     * @param type
     *           间隔单位：年(year)，月(month)，日(date)，周(week)
     * @return date
     */
    public static Date addDate(Date date,Integer distance,String type) {
    	try {
    		if (null == date || null == distance) {
    			return null;
    		}
    		Calendar cal = Calendar.getInstance();
        	cal.setTime(date);
        	if ("date".equalsIgnoreCase(type)) {
        		cal.add(Calendar.DATE, distance);
        	}
        	if ("month".equalsIgnoreCase(type)) {
        		cal.add(Calendar.MONTH, distance);
        	}
        	if ("year".equalsIgnoreCase(type)) {
        		cal.add(Calendar.YEAR, distance);
        	}
        	if ("week".equalsIgnoreCase(type)) {
        		cal.add(Calendar.DATE, distance*7);
        	}
        	return cal.getTime();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static String sufix(int i){return i>9?(i+""):("0"+i);}

	public interface day{public String max();public String min();public String month();public String year();public String days();}
	/**
     * 根据类型和间隔获取时间范围值
     * @param t 1天，2周，3月，4年，默认1
     * @param distance 间隔时间量，- 表示之前，+ 表示之后
     * @return
     */
	public static day getDay(int t,int distance){return getDay(t,distance,null);}
	/**
     * 根据类型和间隔获取时间范围值
     * @param t 1天，2周，3月,4年，默认1
     * @param distance 间隔时间量，- 表示之前，+ 表示之后
     * @return
     */
	public static day getDay(int t,int distance,Date date){
		if(t<1 || t>5){t=1;}
		class rdate implements day{
			private String min;private String max;private String month;private String year;private String days;
			@Override
			public String max() {return max;}
			@Override
			public String min() {return min;}
			@Override
			public String month() {return month;}
			@Override
			public String year() {return year;}
			@Override
			public String days() {return days;}
			public void setMax(String max){this.max=max;}
			public void setMin(String min){this.min=min;}
			public void setMonth(String month){this.month=month;}
			public void setYear(String year){this.year=year;}
			public void setDays(String days){this.days=days;}
		}
		rdate r=new rdate();String v=null;
		Calendar cal = Calendar.getInstance();
		if(date!=null){cal.setTime(date);}
		if(t==1){
			cal.add(Calendar.DATE, distance);  //加减天数
			v=cal.get(Calendar.YEAR)+"-"+sufix(cal.get(Calendar.MONTH)+1)+"-"+sufix(cal.get(Calendar.DATE));
			r.setMin(v+" 00:00:00");r.setMax(v+" 23:59:59");r.setDays(v);
			r.setMonth((cal.get(Calendar.MONTH)+1)+"");r.setYear(cal.get(Calendar.YEAR)+"");
		}else if(t==2){
			int w=cal.get(Calendar.DAY_OF_WEEK);
			w = w==1?7:w-1;//星期1为一周的开始
			w=(w>0?w-1:w);w=(w<=0?0:w-w*2)+(7*distance);
			cal.add(Calendar.DATE, w);  //设置到上周或本周的第一天
			v=cal.get(Calendar.YEAR)+"-"+sufix(cal.get(Calendar.MONTH)+1)+"-"+sufix(cal.get(Calendar.DATE));
			r.setMin(v+" 00:00:00");r.setMonth((cal.get(Calendar.MONTH)+1)+"");r.setYear(cal.get(Calendar.YEAR)+"");
			cal.add(Calendar.DATE, 6);  //再加6天为一周最后一天
			v=cal.get(Calendar.YEAR)+"-"+sufix(cal.get(Calendar.MONTH)+1)+"-"+sufix(cal.get(Calendar.DATE));
			r.setMax(v+" 23:59:59");
		}else if(t==3){
			cal.add(Calendar.MONTH, distance);
			r.setMin(cal.get(Calendar.YEAR)+"-"+sufix(cal.get(Calendar.MONTH)+1)+"-01 00:00:00");
			r.setMax(cal.get(Calendar.YEAR)+"-"+sufix(cal.get(Calendar.MONTH)+1)+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH)+" 23:59:59");
			r.setMonth((cal.get(Calendar.MONTH)+1)+"");r.setYear(cal.get(Calendar.YEAR)+"");
		}else if(t==4){
			cal.add(Calendar.YEAR, distance);
			cal.set(Calendar.MONTH, 11);
			r.setMin(cal.get(Calendar.YEAR)+"-01-01 00:00:00");r.setYear(cal.get(Calendar.YEAR)+"");
			r.setMax(cal.get(Calendar.YEAR)+"-12-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH)+" 23:59:59");
		}else if(t==5){ //季度暂不处理 间隔，可以通过调用月份的间隔，来实现间隔。只获取季度开始和最后一天。
		    int newMonth=1;
            int month = cal.get(Calendar.MONTH) + 1;
            int quarter = 0;
            if (month >= 1 && month <= 3) {
                quarter = 1;
                newMonth=1;
            } else if (month >= 4 && month <= 6) {
                quarter = 2;
                newMonth=4;
            } else if (month >= 7 && month <= 9) {
                quarter = 3;
                newMonth=7;
            } else {
                quarter = 4;
                newMonth=10;
            }
            r.setMonth((cal.get(Calendar.MONTH)+1)+"");
            r.setYear(cal.get(Calendar.YEAR)+"");
            if (month < 10) {
                r.setMin(cal.get(Calendar.YEAR) + "-0" + newMonth + "-01 00:00:00");
                if(quarter==1){
                    r.setMax(cal.get(Calendar.YEAR) + "-0" + (newMonth +2)+"-31 23:59:59");
                }else{
                    r.setMax(cal.get(Calendar.YEAR) + "-0" + (newMonth +2)+"-30 23:59:59");
                }
            } else {
                r.setMin(cal.get(Calendar.YEAR) + "-" + newMonth + "-01 00:00:00");
                r.setMax(cal.get(Calendar.YEAR) + "-" + (newMonth +2)+"-31 23:59:59");
            }
        }
		return r;
	}

    //根据传入的日期获取所在月份所有日期,若传入日期为本月，则获取月初到当前日期(不包括当前日期)的所有天
    public static List<String> getAllDaysMonthByDate(Date d){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        List<String> list = new ArrayList<String>();
        Date date = getMonthStart(d);
        Date monthEnd = null;
        if (sdf2.format(date).equals(sdf2.format(new Date()))) {//日期在本月内
            Calendar calendar = Calendar.getInstance();
            monthEnd = calendar.getTime();
        } else {
            monthEnd = getMonthEnd(d);
        }
        while (!date.after(monthEnd)) {
            list.add(sdf1.format(date));
            date = getNextDay(date);
        }
        return list;
    }

    //根据传入的日期获取所在月份的开始日期
    private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    //根据传入的日期获取所在月份的截止日期
    private static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    //根据传入的日期获取下一天
    private static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
//		int dateType=1;
//		System.out.println(dateType/2+(dateType%2==0?0:1));
//		 dateType=2;
//		System.out.println(dateType/2+(dateType%2==0?0:1));
//		 dateType=3;
//		System.out.println(dateType/2+(dateType%2==0?0:1));
//		 dateType=4;
//		System.out.println(dateType/2+(dateType%2==0?0:1));
//		 dateType=5;
//		System.out.println(dateType/2+(dateType%2==0?0:1));
//		 dateType=6;
//		System.out.println(dateType/2+(dateType%2==0?0:1));
//        List<Map<String, Object>> list = getInitialPersonalDailyDetailList("2017-12-01");
//        for (Map<String, Object> map: list) {
//            System.out.println(map);
//        }
       // System.out.println(getWorkdayByMonth("2017-11-01"));
        //Calendar cal = Calendar.getInstance();
       // System.out.println(2017 == cal.get(Calendar.YEAR));
    	
    }


	/**
	 * 获取当前年月(如：201706)
	 * @author zhangys
	 * @date 2017年6月7日 下午7:45:56
	 * @return
	 */
	public static Integer getCurrentYearMonth() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		String months = null;
		if (month < 10) {
			months = year + "0" + month;
		} else {
			months = year + "" + month;
		}
		return Integer.parseInt(months);
	}

	static {
	    //每月工作日天数map
        workDayMap.put("2017-01", 19);workDayMap.put("2018-01", 22);
        workDayMap.put("2017-02", 19);workDayMap.put("2018-02", 16);
        workDayMap.put("2017-03", 23);workDayMap.put("2018-03", 22);
        workDayMap.put("2017-04", 19);workDayMap.put("2018-04", 19);
        workDayMap.put("2017-05", 21);workDayMap.put("2018-05", 22);
        workDayMap.put("2017-06", 22);workDayMap.put("2018-06", 20);
        workDayMap.put("2017-07", 21);workDayMap.put("2018-07", 22);
        workDayMap.put("2017-08", 23);workDayMap.put("2018-08", 23);
        workDayMap.put("2017-09", 22);workDayMap.put("2018-09", 20);
        workDayMap.put("2017-10", 17);workDayMap.put("2018-10", 19);
        workDayMap.put("2017-11", 22);workDayMap.put("2018-11", 22);
        workDayMap.put("2017-12", 21);workDayMap.put("2018-12", 21);

        //节假日list
        holidayList.add("2017-01-01");holidayList.add("2018-01-01");
        holidayList.add("2017-01-02");holidayList.add("2018-02-15");
        holidayList.add("2017-01-27");holidayList.add("2018-02-16");
        holidayList.add("2017-01-28");holidayList.add("2018-02-17");
        holidayList.add("2017-01-29");holidayList.add("2018-02-18");
        holidayList.add("2017-01-30");holidayList.add("2018-02-19");
        holidayList.add("2017-01-31");holidayList.add("2018-02-20");
        holidayList.add("2017-02-01");holidayList.add("2018-02-21");
        holidayList.add("2017-04-02");holidayList.add("2018-04-05");
        holidayList.add("2017-04-03");holidayList.add("2018-04-06");
        holidayList.add("2017-04-04");holidayList.add("2018-04-07");
        holidayList.add("2017-04-29");holidayList.add("2018-04-29");
        holidayList.add("2017-04-30");holidayList.add("2018-04-30");
        holidayList.add("2017-05-01");holidayList.add("2018-05-01");
        holidayList.add("2017-05-28");holidayList.add("2018-06-16");
        holidayList.add("2017-05-29");holidayList.add("2018-06-17");
        holidayList.add("2017-05-30");holidayList.add("2018-06-18");
        holidayList.add("2017-10-01");holidayList.add("2018-09-22");
        holidayList.add("2017-10-02");holidayList.add("2018-09-23");
        holidayList.add("2017-10-03");holidayList.add("2018-09-24");
        holidayList.add("2017-10-04");holidayList.add("2018-10-01");
        holidayList.add("2017-10-05");holidayList.add("2018-10-02");
        holidayList.add("2017-10-06");holidayList.add("2018-10-03");
        holidayList.add("2017-10-07");holidayList.add("2018-10-04");
        holidayList.add("2017-10-08");holidayList.add("2018-10-05");
        holidayList.add("2017-12-30");holidayList.add("2018-10-06");
        holidayList.add("2017-12-31");holidayList.add("2018-10-07");

        //调休List
        dayOffList.add("2017-01-22");
        dayOffList.add("2017-02-04");
        dayOffList.add("2017-04-01");
        dayOffList.add("2017-06-27");
        dayOffList.add("2018-02-11");
        dayOffList.add("2018-02-24");
        dayOffList.add("2018-04-08");
        dayOffList.add("2018-04-28");
        dayOffList.add("2018-09-29");
        dayOffList.add("2018-09-30");
    }

    /**
     * @param date yyyy-MM-dd格式
     * @return
     */
    public static Integer getWorkdayByMonth(String date) throws NumberFormatException {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        String yearMonth = date.substring(0, 7);
        Calendar calendar = Calendar.getInstance();
        if (year != calendar.get(Calendar.YEAR) || calendar.get(Calendar.MONTH) != (month - 1)) {//非本月，直接返回数据
            return workDayMap.get(yearMonth) == null ? 0 : workDayMap.get(yearMonth);
        } else {//去除周六周日
            int count = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            int today = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DATE, 1);

            while (cal.get(Calendar.DAY_OF_MONTH) < today) {
                int day = cal.get(Calendar.DAY_OF_WEEK);

                if (holidayList.contains(sdf.format(cal.getTime()))) {//跳过节假日
                } else if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
                    if (dayOffList.contains(sdf.format(cal.getTime()))) {//为调休日，天数+1
                        count++;
                    }
                } else {
                    count++;
                }
                cal.add(Calendar.DATE, 1);
            }
            return count;
        }
    }

    public static List<Map<String, Object>> getInitialPersonalDailyDetailList(String date) throws ParseException {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Date d2=DateUtil.convertStringToDate("yyyy-MM-dd",date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d2);
        cal.set(Calendar.DATE, 1);
        List<String> list =DateUtil.getAllDaysMonthByDate(d2);
        for(String dateStr: list) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            Map<String, Object> map = new HashMap<String, Object>();
            if (holidayList.contains(dateStr)) {//节假日
                map.put("type", 3);
            } else if (day == Calendar.SUNDAY || day == Calendar.SATURDAY){//周末
                if (dayOffList.contains(dateStr)) {//调休日
                    map.put("type", 2);//未填写
                } else {
                    map.put("type", 3);//假期
                }
            } else {
                map.put("type", 2);//未填写
            }
            map.put("date", dateStr);
            map.put("dayOfWeek", day-1);
            map.put("work", null);
            mapList.add(map);
            cal.add(Calendar.DATE, 1);
        }
        return mapList;
    }
}
