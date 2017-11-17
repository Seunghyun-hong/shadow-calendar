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
    private int luneint;
    private String luneString;
    private int dateYear;
    private int dateMonth;
    private int dateDayOfMonth;
    private RunaApi mApiService;

    public MyDate(Date date) {
        this.date = date;

        // 음력 계산
        // lun =
        convertDateToLune();
        Log.d(TAG, "MyDate: 4. 음력 잘 만들어 지고 있지?" + lune);  // 이건 계속 도는데 콜백이 안오네..ㅠㅠ
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            month = String.valueOf(dateMonth+1);
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

        Log.d(TAG, "convertDateToLune: 1. 레트로핏은 작동하니?" + mApiService);


        //                                           execute 가 동기라서 하고 싶은데 안드로이드에선 터진데...
        mApiService.getCaculaterDay(year, month, day).enqueue(new Callback<Runa>() {
            @Override
            public void onResponse(Call<Runa> call, Response<Runa> response) {
                int lunDayOfMonth = response.body().getResponse().getBody().getItems().getItem().getLunDay();
                int lunMonth = response.body().getResponse().getBody().getItems().getItem().getLunMonth();
                int lunYear = response.body().getResponse().getBody().getItems().getItem().getLunYear();

//                Date imsidate = new Date(lunYear, lunMonth, lunDayOfMonth);
//                lune = mSimpleDateFormat.format(imsidate);
                Calendar c = Calendar.getInstance();
                c.set(lunYear, lunMonth, lunDayOfMonth);
                lune = c.getTime();
//                luneString = mSimpleDateFormat.format(c.getTime());

                Log.d(TAG, "onResponse: 2. 음력 잘 나오나?" + lune); // 잘 작동하는데...
            }

            @Override
            public void onFailure(Call<Runa> call, Throwable t) {
                Log.d(TAG, "onFailure: 음력받아오기 실패 " + t.getLocalizedMessage());
                lune = date;
            }
        });

        Log.d(TAG, "convertDateToLune: 3. 음력 최종 (잘되면 요일도나옴/안되면 널이나 숫자)  " + lune);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "mSimpleDateFormat=" + mSimpleDateFormat +
                ", TAG='" + TAG + '\'' +
                ", date=" + date +
                ", lune=" + lune +
                ", dateYear=" + dateYear +
                ", dateMonth=" + dateMonth +
                ", dateDayOfMonth=" + dateDayOfMonth +
                ", mApiService=" + mApiService +
                '}';
    }
}
