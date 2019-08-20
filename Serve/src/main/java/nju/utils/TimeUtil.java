package nju.utils;

import java.util.Calendar;

/**
 * Description: 时间相关操作
 * Created by Hanxinhu at 8:46 2018/6/17/017
 */
public class TimeUtil {
    private int year;
    private int month;
    private int day;

    public TimeUtil() {
        Calendar calendar = Calendar.getInstance();
        this.day = calendar.get(Calendar.DATE);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.year = calendar.get(Calendar.YEAR);
    }

    public TimeUtil(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public TimeUtil(String date) {
        String[] param = date.split("-");
        year = Integer.parseInt(param[0]);
        month = Integer.parseInt(param[1]);
        day = Integer.parseInt(param[2]);
    }



    @Override
    public String toString() {
        return String.format("%4d-%02d-%02d", year, month, day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * 返回小时数
     *
     * @param newTime
     * @return
     */
    public int IntervalTime(TimeUtil newTime) {
        Calendar calendar = Calendar.getInstance();
        //第一个日期的秒值
        long firstSecond = 0;
        calendar.set(this.year
            , this.month - 1
            , this.day
            , 0
            , 0
            , 0);
        firstSecond = calendar.getTimeInMillis();
        //第二个日期的秒值
        long secondSecond = 0;
        calendar.set(newTime.year
            , newTime.month - 1
            , newTime.day
            , 0
            , 0
            , 0);
        secondSecond = calendar.getTimeInMillis();
        //
        calendar.setTimeInMillis(secondSecond);
        return (int) ((secondSecond - firstSecond) / (1000 * 60 * 60));
    }

    /**
     * @param another
     * @return 返回相隔的天数
     */
    public int IntervalDay(TimeUtil another) {
        return IntervalTime(another) / 24;
    }

    public TimeUtil addDay(int day) {
        Calendar calendar = Calendar.getInstance();
        //第一个日期的秒值
        long firstSecond = 0;
        calendar.set(this.year
            , this.month - 1
            , this.day
            , 0
            , 0
            , 0);
        firstSecond = calendar.getTimeInMillis();
        long minus = 1000 * 60 * 60* 24;
        minus *= day;
        firstSecond += minus;
        calendar.setTimeInMillis(firstSecond);
        int day2 = calendar.get(Calendar.DATE);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        int year2 = calendar.get(Calendar.YEAR);
        return new TimeUtil(year2, month2, day2);
    }
    public static void main(String[] args) {

        System.out.println(new TimeUtil().addDay(-30).toString());
    }
    public TimeUtil minusDay(int day) {
        return addDay(-1 * day);
    }

}
