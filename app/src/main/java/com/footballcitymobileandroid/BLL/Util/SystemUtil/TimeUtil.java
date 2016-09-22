package com.footballcitymobileandroid.BLL.Util.SystemUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smartlab on 16/5/7.
 */
public class TimeUtil {
    /**
     * 获取系统当前日期
     **/
    public static String getSystemCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        SimpleDateFormat sDateFormats = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 将String转换成Date
     * @param dateString
     * @return
     */
    public static Date getData(String dateString)
    {
//    字符串形式    String dateString = "2016-5-07 ";
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            Date date = sdf.parse(dateString);
            return date;
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 将Date转换成String
     * @param date
     * @return
     */
    public static String getString(Date date)
    {
        return  (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }
}
