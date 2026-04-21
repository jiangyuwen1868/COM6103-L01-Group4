package com.ruoyi.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public final class DateUtils {
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_DATE_TIME_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final String format(String pattern, Date date) {
        if (date == null) {
            return "";
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    public static final Date parseDate(String pattern, String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDate.parse(s, dateTimeFormatter).atStartOfDay();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static final Date parseDateTime(String pattern, String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(s, dateTimeFormatter);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
    
    public static final Date parseDate(String dateStr) throws ParseException{
    	
    	List<String> DATE_FORMATS = Arrays.asList(
    			DEFAULT_DATE_TIME_FORMAT,
    			DEFAULT_DATE_FORMAT,
    			FORMAT_YYYYMMDDHHMMSSSSS,
    			FORMAT_YYYYMMDDHHMMSS,
    			FORMAT_DATE_TIME_MS,
    			FORMAT_YYYYMMDD
    	    );
    	if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new ParseException("日期字符串为空", 0);
        }
    	
    	dateStr = dateStr.trim();
    	
    	for (String format : DATE_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false); // 严格模式
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                // 继续尝试下一个格式
                continue;
            }
        }
        
        // 如果所有格式都失败，抛出异常
        throw new ParseException("无法解析日期字符串: " + dateStr, 0);
    }

    public static final String format(String pattern, long timeMillis) {
        return format(pattern, new Date(timeMillis));
    }

    public static final String format(Date date) {
        return format(DEFAULT_DATE_FORMAT, date);
    }

    public static final String formatDateTime(Date date) {
        return format(DEFAULT_DATE_TIME_FORMAT, date);
    }

    public static final String formatYYYYMMDDHHMMSSSSS(Date date) {
        return format(FORMAT_YYYYMMDDHHMMSSSSS, date);
    }

    public static final String formatYYYYMMDDHHMMSSSSS(long timeMillis) {
        return format(FORMAT_YYYYMMDDHHMMSSSSS, timeMillis);
    }

    public static final String formatYYYYMMDDHHMMSS(Date date) {
        return format(FORMAT_YYYYMMDDHHMMSS, date);
    }

    public static final String formatYYYYMMDDHHMMSS(long timeMillis) {
        return format(FORMAT_YYYYMMDDHHMMSS, timeMillis);
    }

    public static final String formatDateTimeWithMS(Date date) {
        return format(FORMAT_DATE_TIME_MS, date);
    }

    public static final String formatDateTimeWithMS(long timeMillis) {
        return format(FORMAT_DATE_TIME_MS, timeMillis);
    }

    public static final Date parseYYYYMMDDHHMMSS(String date) {
        return parseDateTime(FORMAT_YYYYMMDDHHMMSS, date);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
