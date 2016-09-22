package com.footballcitymobileandroid.DAL.Data.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhoudi on 16/7/18.
 */
public class StringUtils {
    public static int getAge(String birthday)
    {
        String[] birthdayArray = birthday.split("-");
        for (int i = 0; i < birthdayArray.length; i++) {
            LogUtils.e(birthdayArray[i]);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        int brith_year=0,now_year=0;
        int brith_mouth=0,now_mouth=0;
        int brith_day=0,now_day=0;
        try {
            brith_year = Integer.parseInt(birthdayArray[0]);
            brith_mouth = Integer.parseInt(birthdayArray[1]);
            brith_day = Integer.parseInt(birthdayArray[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        LogUtils.e(df.format(new Date()));

        String now=df.format(new Date());
        String[] nowArray = now.split("-");
        for (int i = 0; i < nowArray.length; i++) {
            LogUtils.e(nowArray[i]);
        }

        try {
            now_year = Integer.parseInt(nowArray[0]);
            now_mouth = Integer.parseInt(nowArray[1]);
            now_day = Integer.parseInt(nowArray[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int age=now_year-brith_year;
        LogUtils.e(""+now_year);
        LogUtils.e(""+brith_year);
        if (age<=0)
        {
            return 0;
        }else {

            if (now_mouth>brith_mouth)
            {
                return age;
            }else if (now_mouth==brith_mouth)
            {
                if (now_day>=brith_day)
                {
                    return age;
                }else
                {
                    return age+1;
                }
            }else
            {
                return age+1;
            }
        }

    }

    public static String AtyField(String x)
    {
        String str="";

        if (x.equals("1"))
        {
            str="金州体育场";
        }
        if (x.equals("2"))
        {
            str="大连市体育馆";

        }
        if (x.equals("3"))
        {
            str="大连市体育中心足球场";

        }
        if (x.equals("4"))
        {
            str="大连凌云室内足球场";

        }
        if (x.equals("5"))
        {
            str="奥克体育场";

        }
        return str;
    }

    public static String playPlace(String x)
    {
        String str="未设置";

        if (x.equals("1"))
        {
            str="前锋";
        }
        if (x.equals("2"))
        {
            str="中锋";

        }
        if (x.equals("3"))
        {
            str="后卫";

        }
        if (x.equals("4"))
        {
            str="门将";

        }

        return str;
    }


    public static String costMode(String x)
    {
        String str="";

        if (x.equals("0"))
        {
            str="主场支付";
        }
        if (x.equals("1"))
        {
            str="AA制";

        }

        return str;
    }
}
