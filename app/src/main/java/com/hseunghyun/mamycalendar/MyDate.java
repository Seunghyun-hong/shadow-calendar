package com.hseunghyun.mamycalendar;

import java.util.Date;

/**
 * Created by Thunder on 2017-11-13.
 */

public class MyDate {
    private Date date;
    private Date lune;

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


    // 여기서 음력 변환
    private void convertDateToLune(){
        lune = date;
    }
}
