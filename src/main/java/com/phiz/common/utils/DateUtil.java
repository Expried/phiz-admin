/**
 * 
 */
package com.phiz.common.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author xiao.ai
 *
 */
public class DateUtil {

	private static final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static final String DATE_FORMAT_STR = "dd/MM/yyyy HH:mm";
	
	public static final String MM_DD_YYYY_HH_MM_STR = "MM-dd-yyyy HH:mm";

	// 默认显示日期的格式1
	public static final String DATAFORMAT_STR = "yyyy-MM-dd";

	// 默认显示日期的格式1
	public static final String DATAFORMAT_STRS = "HH:mm";

	public static final String MM_DD_YYYY_STR = "MM/dd/YYYY";
	public static final String MM_DD_YY_STR = "ddMMYY";
	
	public static final String YYYY_MM_DD_HH_MMDATAFROMAT_STR = "yyyyMMddHHmm";

	// 默认显示日期的格式
	public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";

	// 默认显示日期时间的格式
	public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATATIMEF_STRS = "yyyyMMddHHmmss";

	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATAFORMAT_STR = "yyyy年MM月dd日";

	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR = "yyyy年MM月dd日HH时mm分ss秒";

	public static final String ZH_DATATIMEF_STR = "yyyyMMdd";

	public static final String YMDHM = "yyyy-MM-dd HH:mm";
	
	public static final String YMDHMS = "MM/dd/yyyy HH:mm";
	
	public static final String YMDHMES = "MM/dd/yyyy";

	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR_4yMMddHHmm = "yyyy年MM月dd日HH时mm分";


	public static DateFormat getDateFormat(String formatStr) {
		if (formatStr.equalsIgnoreCase(DATAFORMAT_STR)) {
			return new SimpleDateFormat(DATAFORMAT_STR);
		} else if (formatStr.equalsIgnoreCase(DATATIMEF_STR)) {
			return new SimpleDateFormat(DATATIMEF_STR);
		} else if (formatStr.equalsIgnoreCase(ZHCN_DATAFORMAT_STR)) {
			return new SimpleDateFormat(ZHCN_DATAFORMAT_STR);
		} else if (formatStr.equalsIgnoreCase(ZHCN_DATATIMEF_STR)) {
			return new SimpleDateFormat(ZHCN_DATATIMEF_STR);
		} else if (formatStr.equalsIgnoreCase(ZH_DATATIMEF_STR)) {
			return new SimpleDateFormat(ZH_DATATIMEF_STR);
		} else if (formatStr.equalsIgnoreCase(YMDHM)) {
			return new SimpleDateFormat(YMDHM);
		} else {
			return new SimpleDateFormat(formatStr);
		}
	}

	// 返回当前时间,若传入格式为空，则默认为yyyy-MM-dd HH:mm:ss
	public static String getNowDate(String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formart = new SimpleDateFormat(pattern);
		return formart.format(new Date());
	}

	// 将Sat Jun 21 00:00:00 CST 2014格式转为yyyy-MM-dd
	public static String convertDateToYmd(String date) {
		String res = null;
		try {
			res = ymdFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 按照默认显示日期时间的格式"yyyy-MM-dd HH:mm:ss"，转化dateTimeStr为Date类型 dateTimeStr必须是
	 * "yyyy-MM-dd HH:mm:ss"的形式
	 * 
	 * @param dateTimeStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr) {
		return getDate(dateTimeStr, DATAFORMAT_STR);
	}

	public static Date getDateType(String dateTimeStr, String dateType) {
		return getDate(dateTimeStr, dateType);
	}

	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 * 
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			if (dateTimeStr.indexOf("/") > -1) {// 2015/02/01
				dateTimeStr = dateTimeStr.replace("/", "-");
			}
			DateFormat sdf = getDateFormat(formatStr);
			return sdf.parse(dateTimeStr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	  /**
     * @param src
     * @return java.lang.String
     * @author qyj
     * @date 2018/4/25 9:26
     * @Description 将yyyyMMdd转化为ddMMMyy类型   j
     */
    public static String coverDateToDocs(String src) {
        if(src.length() != 8){
            throw new RuntimeException("乘机人中时间格式有误");
        }
        String year = src.substring(2, 4);
        String month = src.substring(4, 6);
        String day = src.substring(6);

        String[] monthArr = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        int i;
        try{
            i = Integer.parseInt(month);
        }   catch (Exception e) {
            throw new RuntimeException("乘机人中时间格式有误");
        }

        month = monthArr[i - 1];
        return day + month + year;
    }
	/**
	 * 将YYYYMMDD转换成Date日期 @param date @return @throws
	 */
	public static Date transferDate(String date) throws Exception {
		if (date == null || date.length() < 1)
			return null;

		if (date.length() != 8)
			throw new Exception("日期格式错误");
		String con = "-";

		String yyyy = date.substring(0, 4);
		String mm = date.substring(4, 6);
		String dd = date.substring(6, 8);

		int month = Integer.parseInt(mm);
		int day = Integer.parseInt(dd);
		if (month < 1 || month > 12 || day < 1 || day > 31)
			throw new Exception("日期格式错误");

		String str = yyyy + con + mm + con + dd;
		return DateUtil.getDate(str, DateUtil.DATAFORMAT_STR);
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToDateString(Date date) {
		return dateToDateString(date, DATATIMEF_STR);
	}

	/**
	 * 将Date转换成formatStr格式的字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToDateString(Date date, String formatStr) {
		DateFormat df = getDateFormat(formatStr);
		return df.format(date);
	}

	/**
	 * 返回一个yyyy-MM-dd HH:mm:ss 形式的日期时间字符串中的HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getTimeString(String dateTime) {
		return getTimeString(dateTime, DATATIMEF_STR);
	}

	/**
	 * 返回一个formatStr格式的日期时间字符串中的HH:mm:ss
	 * 
	 * @param dateTime
	 * @param formatStr
	 * @return
	 */
	public static String getTimeString(String dateTime, String formatStr) {
		Date d = getDate(dateTime, formatStr);
		String s = dateToDateString(d);
		return s.substring(DATATIMEF_STR.indexOf('H'));
	}

	/**
	 * 返回一个formatStr中的时分秒
	 * 
	 * @param dateTime
	 * @param formatStr
	 * @return
	 */
	public static String getTimeStr(String dateTime, String formatStr) {
		Date d = getDate(dateTime, formatStr);
		String s = dateToDateString(d, formatStr);
		return s.substring(DATATIMEF_STR.indexOf('H'));
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 * 
	 * @return
	 */
	public static String getCurDate() {
		// return dateToDateString(new Date(),DATAFORMAT_STR);
		return dateToDateString(Calendar.getInstance().getTime(), DATAFORMAT_STR);
	}

	/**
	 * 获取昨天的这个时候
	 * 
	 * @return
	 */
	public static Date getYesterdayTime() {
		return getInternalDateByDay(new Date(), -1);
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 * 
	 * @return
	 */
	public static String getYesterdayDate() {
		return getInternalDateByLastDay(new Date(), -1);
	}

	/**
	 * 获取当前日期yyyy年MM月dd日的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDate() {
		return dateToDateString(new Date(), ZHCN_DATAFORMAT_STR);
	}

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 * 
	 * @return
	 */
	public static String getCurDateTime() {
		return dateToDateString(new Date(), DATATIMEF_STR);
	}

	/**
	 * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDateTime() {
		return dateToDateString(new Date(), ZHCN_DATATIMEF_STR);
	}

	/**
	 * 获取日期d的days天后的一个Date
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date getInternalDateByDay(Date d, int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	public static Date getInternalDateByMon(Date d, int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}

	public static Date getInternalDateByYear(Date d, int years) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.YEAR, years);
		return now.getTime();
	}

	public static Date getInternalDateBySec(Date d, int sec) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.SECOND, sec);
		return now.getTime();
	}

	public static Date getInternalDateByMin(Date d, int min) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MINUTE, min);
		return now.getTime();
	}

	public static Date getInternalDateByHour(Date d, int hours) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.HOUR_OF_DAY, hours);
		return now.getTime();
	}

	/**
	 * 根据一个日期字符串，返回日期格式，目前支持4种 如果都不是，则返回null
	 * 
	 * @param DateString
	 * @return
	 */
	public static String getFormateStr(String DateString) {
		String patternStr1 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}"; // yyyy-MM-dd
		String patternStr2 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"; // yyyy-MM-dd
																									// HH:mm:ss;
		String patternStr3 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";// yyyy年MM月dd日
		String patternStr4 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[0-9]{1,2}时[0-9]{1,2}分[0-9]{1,2}秒";// yyyy年MM月dd日HH时mm分ss秒

		Pattern p = Pattern.compile(patternStr1);
		Matcher m = p.matcher(DateString);
		boolean b = m.matches();
		if (b)
			return DATAFORMAT_STR;
		p = Pattern.compile(patternStr2);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATATIMEF_STR;

		p = Pattern.compile(patternStr3);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATAFORMAT_STR;

		p = Pattern.compile(patternStr4);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATATIMEF_STR;
		return null;
	}

	/**
	 * 将一个"yyyy-MM-dd HH:mm:ss"字符串，转换成"yyyy年MM月dd日HH时mm分ss秒"的字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getZhCNDateTime(String dateStr) {
		Date d = getDate(dateStr);
		return dateToDateString(d, ZHCN_DATATIMEF_STR);
	}

	/**
	 * 将一个"yyyy-MM-dd"字符串，转换成"yyyy年MM月dd日"的字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getZhCNDate(String dateStr) {
		Date d = getDate(dateStr, DATAFORMAT_STR);
		return dateToDateString(d, ZHCN_DATAFORMAT_STR);
	}

	/**
	 * 将dateStr从fmtFrom转换到fmtTo的格式
	 * 
	 * @param dateStr
	 * @param fmtFrom
	 * @param fmtTo
	 * @return
	 */
	public static String getDateStr(String dateStr, String fmtFrom, String fmtTo) {
		Date d = getDate(dateStr, fmtFrom);
		return dateToDateString(d, fmtTo);
	}

	/**
	 * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,time2-time1
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long compareDateStr(String time1, String time2) {
		Date d1 = getDate(time1);
		Date d2 = getDate(time2);
		return d2.getTime() - d1.getTime();
	}

	/**
	 * 将小时数换算成返回以毫秒为单位的时间
	 * 
	 * @param hours
	 * @return
	 */
	public static long getMicroSec(BigDecimal hours) {
		BigDecimal bd;
		bd = hours.multiply(new BigDecimal(3600 * 1000));
		return bd.longValue();
	}

	/**
	 * 获取Date中的分钟
	 * 
	 * @param d
	 * @return
	 */
	public static int getMin(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取Date中的小时(24小时)
	 * 
	 * @param d
	 * @return
	 */
	public static int getHour(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date中的秒
	 * 
	 * @param d
	 * @return
	 */
	public static int getSecond(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.SECOND);
	}

	/**
	 * 获取xxxx-xx-xx的日
	 * 
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月份，1-12月
	 * 
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取19xx,20xx形式的年
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 将yyyymmdd 转成 yyyy-mm-dd
	 * 
	 */
	public static String formatDate(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 将yyyyMMddHHmm日期格式
	 * 
	 */
	public static Date formatDate2(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmm");

		Date date = null;
		try {
			date = sf1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将ddMMyyHHmm日期格式
	 * 
	 */
	public static Date formatStr(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("ddMMyyHHmm");
		Date date = null;
		try {
			date = sf1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将 yyyy-mm-dd HH:mm转成yyyymmdd
	 * 
	 */
	public static String formatDateStr(String str) {
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}
	/**
	 * 将 yyyymmdd转成mm/dd/yyyy
	 * 
	 */
	public static String convertDateStr(String str) {
		SimpleDateFormat sf2 = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyymmdd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}
	/**
	 * ddMMyyHHmm 转date
	 * 
	 * @param str
	 * @return
	 */
	public static Date convertDate(String str) {
		SimpleDateFormat sf = new SimpleDateFormat("ddMMyyHHmm");
		SimpleDateFormat _sf = new SimpleDateFormat("yyMMddHHmm");
		String sfstr = "";
		Date date = null;
		try {
			sfstr = _sf.format(sf.parse(str));
			date = _sf.parse(sfstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 得到d的上个月的年份+月份,如200505
	 * 
	 * @return
	 */
	public static String getYearMonthOfLastMon(Date d) {
		Date newdate = getInternalDateByMon(d, -1);
		String year = String.valueOf(getYear(newdate));
		String month = String.valueOf(getMonth(newdate));
		return year + month;
	}

	/**
	 * 得到当前日期的年和月如200509
	 * 
	 * @return String
	 */
	public static String getCurYearMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String DATE_FORMAT = "yyyyMM";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	public static Date getNextMonth(String year, String month) {
		String datestr = year + "-" + month + "-01";
		Date date = getDate(datestr, DATAFORMAT_STR);
		return getInternalDateByMon(date, 1);
	}

	public static Date getLastMonth(String year, String month) {
		String datestr = year + "-" + month + "-01";
		Date date = getDate(datestr, DATAFORMAT_STR);
		return getInternalDateByMon(date, -1);
	}

	/**
	 * 得到日期d，按照页面日期控件格式，如"2001-3-16"
	 * 
	 * @param d
	 * @return
	 */
	public static String getSingleNumDate(Date d) {
		return dateToDateString(d, DATAFORMAT_STR);
	}

	/**
	 * 得到d半年前的日期,"yyyy-MM-dd"
	 * 
	 * @param d
	 * @return
	 */
	public static String getHalfYearBeforeStr(Date d) {
		return dateToDateString(getInternalDateByMon(d, -6), DATAFORMAT_STR);
	}

	/**
	 * 得到当前日期D的月底的前/后若干天的时间,<0表示之前，>0表示之后
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static String getInternalDateByLastDay(Date d, int days) {

		return dateToDateString(getInternalDateByDay(d, days), DATAFORMAT_STR);
	}

	/**
	 * 日期中的年月日相加
	 * 
	 * @param field
	 *            int 需要加的字段 年 月 日
	 * @param amount
	 *            int 加多少
	 * @return String
	 */
	public static String addDate(int field, int amount) {
		int temp = 0;
		if (field == 1) {
			temp = Calendar.YEAR;
		}
		if (field == 2) {
			temp = Calendar.MONTH;
		}
		if (field == 3) {
			temp = Calendar.DATE;
		}

		String Time = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			cal.add(temp, amount);
			Time = sdf.format(cal.getTime());
			return Time;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获得系统当前月份的天数
	 * 
	 * @return
	 */
	public static int getCurentMonthDay() {
		Date date = Calendar.getInstance().getTime();
		return getMonthDay(date);
	}

	/**
	 * 获得指定日期月份的天数
	 * 
	 * @return
	 */
	public static int getMonthDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 获得指定日期月份的天数 yyyy-mm-dd
	 * 
	 * @return
	 */
	public static int getMonthDay(String date) {
		Date strDate = getDate(date, DATAFORMAT_STR);
		return getMonthDay(strDate);

	}

	public static String getStringDate(Calendar cal) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(cal.getTime());
	}

	/**
	 * 计算两个日期相差天数，仅计算yyyy-MM-dd部分
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDateDay(Date date1, Date date2) {
		long differ = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1 = sdf.parse(sdf.format(date1));
			date2 = sdf.parse(sdf.format(date2));
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			differ = (time1 < time2) ? time2 - time1 : time1 - time2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (differ / (1000 * 60 * 60 * 24));
	}

	/**
	 * 计算两个日期相差小时数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDateHour(Date date1, Date date2) {
		long differ = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date1 = sdf.parse(sdf.format(date1));
			date2 = sdf.parse(sdf.format(date2));
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			differ = (time1 < time2) ? time2 - time1 : time1 - time2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (differ / (1000 * 60 * 60));
	}

	/**
	 * 计算两个日期相差分钟数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDateMinute(Date date1, Date date2) {
		long differ = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date1 = sdf.parse(sdf.format(date1));
			date2 = sdf.parse(sdf.format(date2));
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			differ = (time1 < time2) ? time2 - time1 : time1 - time2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (differ / (1000 * 60));
	}

	/**
	 * 计算两个日期相差秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDateSecond(Date date1, Date date2) {
		long differ = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date1 = sdf.parse(sdf.format(date1));
			date2 = sdf.parse(sdf.format(date2));
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			differ = (time1 < time2) ? time2 - time1 : time1 - time2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (differ / 1000);
	}

	/**
	 * 计算两个时间相差分数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDatemin(Date date1, Date date2) {
		long differ = 0;
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		differ = (time1 < time2) ? time2 - time1 : time1 - time2;
		return (int) (differ / 60000);
	}

	public static boolean ifSameDate(Date date1, Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s1 = sdf.format(date1);
		String s2 = sdf.format(date2);
		if (s1.equals(s2))
			return true;
		else
			return false;
	}

	/**
	 * 判断日期是星期几
	 * 
	 * @param date
	 * @param fmt
	 *            格式，0表示返回数字格式，1表示返回中文格式
	 * @return
	 */
	public static String getDayOfWeek(Date date, int fmt) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = (c.get(Calendar.DAY_OF_WEEK) == 1) ? 7 : c.get(Calendar.DAY_OF_WEEK) - 1;
		String dayOfWeekStr = null;
		switch (dayOfWeek) {
		case 1:
			dayOfWeekStr = (0 == fmt) ? "1" : "一";
			break;
		case 2:
			dayOfWeekStr = (0 == fmt) ? "2" : "二";
			break;
		case 3:
			dayOfWeekStr = (0 == fmt) ? "3" : "三";
			break;
		case 4:
			dayOfWeekStr = (0 == fmt) ? "4" : "四";
			break;
		case 5:
			dayOfWeekStr = (0 == fmt) ? "5" : "五";
			break;
		case 6:
			dayOfWeekStr = (0 == fmt) ? "6" : "六";
			break;
		case 7:
			dayOfWeekStr = (0 == fmt) ? "7" : "日";
			break;
		}
		return dayOfWeekStr;
	}

	/**
	 * 在某一个日期上加上或减去一个时间量
	 * 
	 * @param date
	 *            日期
	 * @param field
	 *            Calendar类中的日历字段
	 * @param amount
	 *            要加上或减去的时间量
	 * @return
	 */
	public static Date calendarAdd(Date date, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return c.getTime();
	}

	/**
	 * 在某一个日期上加上或减去一个时间量
	 * 
	 * @param date
	 *            日期
	 * @param field
	 *            Calendar类中的日历字段
	 * @param amount
	 *            要加上或减去的时间量
	 * @return
	 */
	public static String calendarAddToString(Date date, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return DateUtil.dateToDateString(c.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取小编的特定日期格式
	 * 
	 * @param dateformt
	 * @return
	 */
	public static String getSmallPNRFormatDate(String dateformt) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateformt);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		SimpleDateFormat format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
		String datestr = format.format(date);
		return datestr;
	}

	/**
	 * 获取今天开始时间 yyyy-MM-dd 00:00:00
	 * 
	 * @return
	 */
	public static Date getTodayBeginTime() {
		String begin_time = getCurDate() + " 00:00:00";
		return getDate(begin_time, DateUtil.DATATIMEF_STR);
	}

	/**
	 * 获取今天结束时间 yyyy-MM-dd 23:59:59
	 * 
	 * @return
	 */
	public static Date getTodayEndTime() {
		String end_time = getCurDate() + " 23:59:59";
		return getDate(end_time, DateUtil.DATATIMEF_STR);
	}

	/**
	 * 获取字符串时间加上或者减去多少个小时，减去用负数来表示
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static String getInternalDateByHour(String date, int hours) {
		Date d = DateUtil.getDate(date, DATATIMEF_STR);
		System.out.println(d);
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.HOUR_OF_DAY, hours);
		Date rDate = now.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATATIMEF_STR);
		String datestr = format.format(rDate);
		return datestr;
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param format
	 *            时间表现形式，例如："yyyy-MM-dd"，"yyyy-MM-dd HH:mm:ss"等
	 * @param date
	 *            待格式化的日期
	 * @return 返回格式化后的日期字符串
	 */
	public static String getFormatDate(Date date, String format) {
		String formatStr = "";
		if (format == null) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			formatStr = simpleDateFormat.format(date);
		}
		return formatStr;
	}

	/**
	 * 格式化日期字符串
	 */
	public static String getDateFormatString(String date, String format) {
		DateFormat sdf = getDateFormat(format);
		Date d = null;
		try {
			d = sdf.parse(date);
			System.out.println(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(d);
	}

	/**
	 * 2014111195231124 方法描述: 获取精确到毫秒的时间戳 作 者： liguihua 日 期：
	 * 2014年11月11日-上午9:36:47
	 * 
	 * @return 返回类型： String
	 */
	public static String getYYYYMMDDHHNNMI() {
		Calendar CD = Calendar.getInstance();
		String YY = CD.get(Calendar.YEAR) + "";
		String MM = (CD.get(Calendar.MONTH) + 1) + "";
		String DD = CD.get(Calendar.DATE) + "";
		String HH = CD.get(Calendar.HOUR) + "";
		String NN = CD.get(Calendar.MINUTE) + "";
		String SS = CD.get(Calendar.SECOND) + "";
		String MI = CD.get(Calendar.MILLISECOND) + "";
		String str = YY + MM + DD + HH + NN + SS + MI;

		return str;
	}

	/**
	 * 一个日期减n天后的日期，返回String(yyyy-mm-dd)
	 * 
	 * @param basicDate
	 *            日期
	 * @param n
	 *            减去的天数
	 * @return
	 */
	public static String nDaysAfterOneDateString(Date basicDate, int n) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long nDay = (basicDate.getTime() / (24 * 60 * 60 * 1000) + 1 - n) * (24 * 60 * 60 * 1000);
		date.setTime(nDay);
		return df.format(date);
	}

	/**
	 * 比较两个日期的大小1表示第一个日期大 0表示同一天 -1表示第二个日期大
	 * 
	 * @param sdate1
	 * @param args
	 * @return
	 */
	public static int comparisonDate(String date, String args) {
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = sdf.parse(date);
			Date date2 = sdf.parse(args);
			if (date1.getTime() - date2.getTime() > 0)
				i = 1;
			else if (date1.getTime() - date2.getTime() < 0)
				i = -1;
		} catch (ParseException e) {
			System.out.println("日期格式出错,判断两个日期大小出错:" + date + "/" + args);
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 比较两个日期的大小1表示第一个日期大 0表示同一天 -1表示第二个日期大
	 * 
	 * @param sdate1
	 * @param args
	 * @return
	 */
	public static int comparisonDate(Date date, Date args) {
		String _date = dateToDateString(date);
		String _args = dateToDateString(args);
		return comparisonDate(_date,_args);
	}

	/**
	 * @Title: isLastDayOfMonth
	 * @Description: 判断日期是不是当月最后一天 true 是 false不是
	 * @param date
	 * @return
	 * @return: boolean
	 */
	public static boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 组装传入数据的开始时间 date 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		String startDate = dateToDateString(date, DATAFORMAT_STR) + " 00:00:00";
		return getDate(startDate, DateUtil.DATATIMEF_STR);
	}

	/**
	 * 组装传入数据的结束时间 date 23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		String endDate = dateToDateString(date, DATAFORMAT_STR) + " 23:59:59";
		return getDate(endDate, DateUtil.DATATIMEF_STR);
	}

	/**
	 * 获取第一个时间至第二个时间，年龄是多少
	 * 
	 * @param birthday
	 *            出生日期，第一个时间 格式：yyyy-MM-dd
	 * @param someDay
	 *            第二个时间 格式：yyyy-MM-dd
	 * @return int 年龄
	 */
	public static int getAgeByOneDay(String birthday, String someDay) {
		if ("".equals(someDay)) {
			someDay = getYesterdayDate();
		}
		Date someDayDate = getDate(someDay, DATAFORMAT_STR);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(someDayDate);

		Date csrq = getDate(birthday, DATAFORMAT_STR);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(csrq);
		int age = calendar.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR) - 1;
		if (calendar.get(Calendar.MONTH) > calendar2.get(Calendar.MONTH)) {
			age++;
		} else if (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) {
			if (calendar.get(Calendar.DATE) >= calendar2.get(Calendar.DATE)) {
				age++;
			}
		}
		return age;
	}

	// 获得两个指定日期格式的时间差,以S为单位
	public static int getDiffer(String time1, String time2, String timeFormat) {
		return (int) (getDate(time1, timeFormat).getTime() - getDate(time2, timeFormat).getTime()) / 1000;
	}

	// java获取当前月的天数
	public static int getDayOfMonth() {
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day = aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}

	public static Calendar getCalendar(String repeatDate, String dateFormat) {
		SimpleDateFormat dft = new SimpleDateFormat(dateFormat);
		Calendar calendar = Calendar.getInstance();
		try {

			if (StringUtil.isEmpty(repeatDate)) {
				calendar.setTime(dft.parse(repeatDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}

	// java获取获取指定日期所在月的日期
	public static List<String> getDayListOfMonth(String repeatDate, String dateFormat) {
		Calendar calendar = getCalendar(repeatDate, dateFormat);
		List<String> list = new ArrayList<String>();
		int year = calendar.get(Calendar.YEAR);// 年份
		int month = calendar.get(Calendar.MONTH) + 1;// 月份
		int day = calendar.getActualMaximum(Calendar.DATE);
		for (int i = 1; i <= day; i++) {
			String aDate = String.valueOf(year) + "/" + month + "/" + i;
			list.add(aDate);
		}
		return list;
	}

	//
	/**
	 * 获取任意时间的月的最后一天 描述:<描述函数实现的功能>.
	 * 
	 * @param repeatDate
	 * @return
	 */
	public static String getMaxMonthDate(String repeatDate, String dateFormat) {
		SimpleDateFormat dft = new SimpleDateFormat(dateFormat);
		Calendar calendar = getCalendar(repeatDate, dateFormat);
		// calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 获取任意时间的月第一天 描述:<描述函数实现的功能>.
	 * 
	 * @param repeatDate
	 * @return
	 */
	public static String getMinMonthDate(String repeatDate, String dateFormat) {
		SimpleDateFormat dft = new SimpleDateFormat(dateFormat);
		Calendar calendar = getCalendar(repeatDate, dateFormat);
		// calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 判断两个日期是否同一月
	 * 
	 * @param args
	 */
	public static boolean isSameMonth(String date1, String date2) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDate(date1, DATAFORMAT_STR));
		int month1 = c.get(Calendar.MONTH);
		c.setTime(getDate(date2, DATAFORMAT_STR));
		int month2 = c.get(Calendar.MONTH);
		return month1 == month2;
	}

	/**
	 * 计算两个日期相隔天数(date2-date1)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int diffDays(String date1, String date2) {
		return (int) ((getDate(date2, DATAFORMAT_STR).getTime() - getDate(date1, DATAFORMAT_STR).getTime())/ (1000 * 3600 * 24));
	}

//	public static void main(String[] args) {
//		String  time = "00:24";
//		Date date = DateUtil.getDate(time, DateUtil.DATAFORMAT_STRS);
//		System.out.println(DateUtil.getMin(date));
////		System.out.println(getDiffer("2018-03-10 18:25", "2018-03-10 18:26", "yyyy-MM-dd HH:mm"));
//	}

	public static String getTimeStr(String dateStr) {
		String temp = dateStr.substring(8, 10);
		String temp2 = dateStr.substring(10, 12);

		String newStr = temp + ":" + temp2;
		return newStr;
	}

	public static String getDateStr(String dateStr) {
		String temp = dateStr.substring(0, 4);
		String temp2 = dateStr.substring(4, 6);
		String temp3 = dateStr.substring(6, 8);
		String newStr = temp + "-" + temp2 + "-" + temp3;
		return newStr;
	}

	public static String getDateFormatStr(String dateStr) {
		String temp = dateStr.substring(4, 6);
		String temp2 = dateStr.substring(6, 8);
		String temp3 = dateStr.substring(8, 10);
		String temp4 = dateStr.substring(10, 12);
		String newStr = temp + "/" + temp2 + " " + temp3 + ":" + temp4;
		return newStr;
	}
	/** 
     * Java根据用户生日计算年龄 
     */  
    public static int getAgeByBirthday(Date birthday) {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthday)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH) + 1;  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
  
        cal.setTime(birthday);  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH) + 1;  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                // monthNow==monthBirth   
                if (dayOfMonthNow < dayOfMonthBirth) {  
                    age--;  
                }  
            } else {  
                // monthNow>monthBirth   
                age--;  
            }  
        }  
        return age;  
    }  
    
    public static String getUTC(){
  	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		 
	  Date localDate;
        localDate= new Date();
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());
        String format = sdf.format(utcDate);
        return format;
    }
	public static String getAmaduesDate(String str) {
		String day = str.substring(0, 2);
		String month = str.substring(2, 4);
		String years = str.substring(4, 6);
		return "20"+years + month + day;
	}

	public static void main(String[] args) {
		SimpleDateFormat _sf = new SimpleDateFormat("ddMMyy");
		String format = _sf.format(new Date());
		System.out.println(format);

	}
}
