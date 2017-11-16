package com.hseunghyun.mamycalendar;

import android.util.Log;

import com.hseunghyun.mamycalendar.CalculaterLuna.model.Runa;
import com.hseunghyun.mamycalendar.CalculaterLuna.retrofit.RunaApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Thunder on 2017-11-13.
 */

public class MyDate {

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM.dd", Locale.getDefault());
    private String TAG = MainActivity.class.getSimpleName();

    private Date date;
    private Date lune;
    private int dateYear;
    private int dateMonth;
    private int dateDayOfMonth;
    private RunaApi mApiService;

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


        // 년 월 일 따로 담는다.
        final String year = String.valueOf(dateYear);
        String month = null;
        if (dateMonth + 1 < 10) {
            month = "0" + String.valueOf(dateMonth);
        } else {
            month = String.valueOf(dateMonth);
        }
        String day = null;
        if (dateDayOfMonth < 10) {
            day = "0" + String.valueOf(dateDayOfMonth);
        } else {
            day = String.valueOf(dateDayOfMonth);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RunaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(RunaApi.class);

        mApiService.caculaterDay(year,month,day).enqueue(new Callback<Runa>() {
            @Override
            public void onResponse(Call<Runa> call, Response<Runa> response) {
               int lunDayOfMonth = response.body().getResponse().getBody().getItems().getItem().getLunDay();
               int lunMothe= response.body().getResponse().getBody().getItems().getItem().getLunMonth();
               int lunYear= response.body().getResponse().getBody().getItems().getItem().getLunYear();

               Date imsidate = new Date(lunYear,lunMothe,lunDayOfMonth);
               lune = imsidate;
            }

            @Override
            public void onFailure(Call<Runa> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
