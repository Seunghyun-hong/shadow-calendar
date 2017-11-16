package com.hseunghyun.mamycalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Thunder on 2017-11-13.
 */

public class MyDate {

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM.dd", Locale.getDefault());

    private Date date;
    private String datelune;
    private Date datelun;
    private Date lune;
    private int dateYear;
    private int dateMonth;
    private int dateDayOfMonth;

    public MyDate(Date date) {
        this.date = date;

        // 음력 계산
        // lun =
        convertDateToLune();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLune() {
        return lune;
    }

    public void setLune(Date lune) {
        this.lune = lune;
    }

    public String getDatelune() {
        return datelune;
    }

    public void setDatelune(String datelune) {
        this.datelune = datelune;
    }

    public Date getDatelun() {
        return datelun;
    }

    public void setDatelun(Date datelun) {
        this.datelun = datelun;
    }

    // 여기서 음력 변환
    private void convertDateToLune() {

//        lune =

        Calendar c = Calendar.getInstance();
        c.setTime(date);

//        date.getTime(); // 이게 롱값이래...
//        mSimpleDateFormat.format(date.getTime());  // 이건 스트링
//        datelun.setTime(date.getTime());

        dateYear = c.get(Calendar.YEAR);
        dateMonth = c.get(Calendar.MONTH);
        dateDayOfMonth = c.get(Calendar.DAY_OF_MONTH);



    }


    @Override
    public String toString() {
        return "MyDate{" +
                "mSimpleDateFormat=" + mSimpleDateFormat +
                ", date=" + date +
                ", datelune='" + datelune + '\'' +
                ", datelun=" + datelun +
                ", lune=" + lune +
                '}';
    }
}
