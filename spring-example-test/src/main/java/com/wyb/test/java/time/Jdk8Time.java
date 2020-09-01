package com.wyb.test.java.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Marcher丶
 */
public class Jdk8Time {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2020-8-30 19:03:00");
        System.out.println(getTime(date));
    }

    public static String getTime(Date date) {
        long delta = System.currentTimeMillis() - date.getTime();
        String sb = "";
        long[] itimes = new long[] { 365 * 24 * 60 * 60 * 1000L, 30 * 24 * 60 * 60 * 1000L, 24 * 60 * 60 * 1000L,
                60 * 60 * 1000L, 60 * 1000, 1000L };
        String[] sunits = new String[] { "年前", "月前", "天前", "小时前", "分钟前", "秒前" };
        for (int i = 0, len = itimes.length; i < len; i++) {
            long itemp = itimes[i];
            if (delta < itemp) {
                continue;
            }
            long temp2 = delta / itemp;
            if (temp2 > 0) {
                sb = temp2 + sunits[i];
                break;
            }
        }
        return sb;
    }
}
