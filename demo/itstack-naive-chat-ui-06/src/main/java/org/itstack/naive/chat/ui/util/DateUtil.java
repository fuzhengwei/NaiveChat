package org.itstack.naive.chat.ui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class DateUtil {

    SimpleDateFormat nowBegin = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    SimpleDateFormat nowEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static String simpleDate(Date date) {
        boolean today = isToday(date);
        if (today) return new SimpleDateFormat("HH:mm").format(date);
        return new SimpleDateFormat("yy/MM/dd").format(date);
    }

    private static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return year1 == year2 && month1 == month2 && day1 == day2;
    }

}
