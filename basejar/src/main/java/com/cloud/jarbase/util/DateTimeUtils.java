package com.cloud.jarbase.util;


import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateTimeUtils {
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT_HHMMSS = "HHmmss";
    public static final String DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATETIME_FORMAT_YYYYMMDDHH = "yyyyMMddHH";
    public static final String DATETIME_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String DATETIME_FORMAT_YYYY = "yyyy";
    public static final String DATETIME_FORMAT_YYYY_MM_DD_CN = "yyyy年MM月dd日";
    public static final String DATETIME_FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String DATE_FORMAT_YYYY_MM_DD_FTP = "/yyyy/MM/dd/";
    public static final String DATE_TIMEZONE = "GMT+8";
    private static final Log log = LogFactory.getLog(DateTimeUtils.class);

    public DateTimeUtils() {
    }

    public static Date getSpecifiedDayAfter(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(5);
        c.set(5, day + num);
        return c.getTime();
    }

    public static boolean isAfterInMill(Date begin, Date end, long timeinmill) {
        long begininmill = begin.getTime();
        long endinmill = end.getTime();
        return endinmill - begininmill > timeinmill;
    }

    public static int compareTwoDate(Date first, Date second) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(first);
        c2.setTime(second);
        return c1.compareTo(c2);
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    public static Date addHH(Date date, int i) {
        if (date == null) {
            return null;
        } else {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(10, i);
            return c.getTime();
        }
    }

    public static Date addMM(Date date, int i) {
        if (date == null) {
            return null;
        } else {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(12, i);
            return c.getTime();
        }
    }

    public static Date addSS(Date date, int i) {
        if (date == null) {
            return null;
        } else {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(13, i);
            return c.getTime();
        }
    }

    public static Date addDate(Date date, int i) {
        if (date == null) {
            return null;
        } else {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(5, i);
            return c.getTime();
        }
    }

    public static Date addMonth(Date date, int i) {
        if (date == null) {
            return null;
        } else {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(2, i);
            return c.getTime();
        }
    }

    public static Date addYear(Date date, int i) {
        if (date == null) {
            return null;
        } else {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            c.add(1, i);
            return c.getTime();
        }
    }

    public static String getNowDateStr(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(getNowDate());
    }

    public static Date getNowDate() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public static String getDateTimeToString(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static Date getStringToDateTime(String dateStr, String formatStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        date = format.parse(dateStr);
        return date;
    }

    public static Date getStringToDateTimeExceptionNull(String dateStr, String formatStr) {
        Date date = null;
        if (StringUtils.isBlank(dateStr)) {
            log.warn("解析的日期字符串为空");
            return date;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);

            try {
                date = format.parse(dateStr);
                return date;
            } catch (ParseException var5) {
                log.error("日期格式错误：" + dateStr, var5);
                return null;
            }
        }
    }

    public static boolean isDate(String dttm, String format) {
        if (dttm != null && !dttm.isEmpty() && format != null && !format.isEmpty()) {
            SimpleDateFormat formatter;
            if (format.replaceAll("'.+?'", "").indexOf("y") < 0) {
                format = format + "/yyyy";
                formatter = new SimpleDateFormat("/yyyy");
                dttm = dttm + formatter.format(new Date());
            }

            formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(dttm, pos);
            if (date != null && pos.getErrorIndex() <= 0) {
                if (pos.getIndex() != dttm.length()) {
                    return false;
                } else {
                    return formatter.getCalendar().get(1) <= 9999;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String addMM(int i) {
        Date currTime = addMM(getNowDate(), i);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(currTime);
    }

    public static String dateAddMM(Date date, int i) {
        Date currTime = addMM(date, i);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(currTime);
    }

    public static Date getBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date getEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public static boolean isBeforeToday(Date theDay) {
        Calendar cNow = Calendar.getInstance();
        int iYear = cNow.get(1);
        int iDay = iYear * 1000 + cNow.get(6);
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(theDay);
        int iTheYear = cDay.get(1);
        int iTheDay = iTheYear * 1000 + cDay.get(6);
        return iTheDay < iDay;
    }

    public static boolean isToday(Date theDay) {
        Calendar cNow = Calendar.getInstance();
        int iYear = cNow.get(1);
        int iDay = cNow.get(6);
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(theDay);
        int iTheYear = cDay.get(1);
        int iTheDay = cDay.get(6);
        return iTheYear == iYear && iTheDay == iDay;
    }

    public static boolean isToday240000(Date theDay) {
        Date tomorrow = getSpecifiedDayAfter(getNowDate(), 1);
        String strDate = getDateTimeToString(tomorrow, "yyyyMMdd");
        strDate = strDate + "000000";
        Date target = getStringToDateTimeExceptionNull(strDate, "yyyyMMddHHmmss");
        return compareTwoDate(theDay, target) == 0;
    }

    public static int daysBetween(Date smdate, Date bdate) {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / 86400000L;
            result = Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException var11) {
            log.error("日期转化异常", var11);
        } catch (Exception var12) {
            log.error("格式转化异常", var12);
        }

        return result;
    }

    public static int secondsBetween(Date smdate, Date bdate) {
        int result = 0;

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_min = (time2 - time1) / 1000L;
            result = Integer.parseInt(String.valueOf(between_min));
        } catch (Exception var10) {
            log.error("格式转化异常", var10);
        }

        return result;
    }

    public static Date getTimestampToDate(long seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(seconds);
        return calendar.getTime();
    }

    public static Date getStartTimeOfDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, day);
        return cal.getTime();
    }

    public static Date getClockTimeOfDay(Date date, int day, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, hour);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, day);
        return cal.getTime();
    }

    public static Date parseDate(String date, String format) {
        Date dd = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat(format);

        try {
            dd = fmt.parse(date);
        } catch (ParseException var5) {
            log.info("日期转化失败");
        }

        return dd;
    }

    public static Date formatBitsDate(Date date) {
        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();

        try {
            d = (Date)f.parseObject(f.format(date));
            return d;
        } catch (ParseException var4) {
            var4.printStackTrace();
            log.info("去除date毫秒失败");
            return d;
        }
    }

    public static int getNowTimeStamp() {
        long time = System.currentTimeMillis();
        int nowTimeStamp = (int)(time / 1000L);
        return nowTimeStamp;
    }

    public static String TimeStampToDate(Integer timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString.toString()) * 1000L;
        String date = (new SimpleDateFormat(formats)).format(new Date(timestamp));
        return date;
    }

    public static Integer DateToTimeStamp(Date date) {
        return (int)(date.getTime() / 1000L);
    }

    public static Date TimeStampToDateType(Integer timestampInt) {
        Long timestamp = Long.parseLong(timestampInt.toString()) * 1000L;
        return new Date(timestamp);
    }

    public static String DateTimeZoneConvert(String dateStr, String beforeTimeZone, String afterTimeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(beforeTimeZone));

        Date date;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException var6) {
            log.error("日期格式转化异常", var6);
            return null;
        }

        SimpleDateFormat afterDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        afterDateFormat.setTimeZone(TimeZone.getTimeZone(afterTimeZone));
        return afterDateFormat.format(date);
    }

//    public static void main(String[] args) {
//        System.out.println("04:00:01".compareTo("04:00:00"));
//    }
}
