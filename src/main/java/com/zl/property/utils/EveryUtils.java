package com.zl.property.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EveryUtils {

    public static boolean isEmpty(List list){
        if(list == null || list.size()<1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取年份
     * @return
     */
    public static int getYear(){
        //使用Calendar
        Calendar now = Calendar.getInstance();
        System.out.println("年：" + now.get(Calendar.YEAR));
        return now.get(Calendar.YEAR);
    }

    /**
     * 获取当前时间
     * @param format
     * @return
     */
    public static String getNow(String format){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 对比时间
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
