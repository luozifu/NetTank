package com.miaoxing.nettank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Administrator
 * @Date : 2018/10/14 0014
 */
public class DateTimeUtils {

    static SimpleDateFormat format;

    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式：yyyy-MM-dd HH:mm **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /** 日期格式：yyyy-MM-dd **/
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

    /** 日期格式：HH:mm:ss **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";

    /** 日期格式：HH:mm **/
    public static final String DF_HH_MM = "HH:mm";

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     * @param date 日期
     * @param formater
     * @return
     */
    public static String formatDateTime(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    public static long getTime(String time,String formater) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.parse(time).getTime();
    }

}
