package com.hseunghyun.mamycalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 이번 달 데이터 달력.
//        List<MyDate> dates = new ArrayList<>();
//
//        // 지금
//        mCalendar = Calendar.getInstance();
//        mCalendar.set(Calendar.DATE, 1); // 이번 달 1일
//        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK); // 1일 요일 (일요일 = 1 , 토요일은 = 7)
//        Log.d(TAG, "onCreate: " + dayOfWeek);
//        int lastDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막 날
//
//        // 요일 공백
//        for (int i = 0; i < dayOfWeek - 1; i++) {
//           dates.add(null);
//        }
//
//        // 날짜 채우기
//        for (int i = 0; i < lastDay; i++) {
//            dates.add(new MyDate(mCalendar.getTime())); // 1로 세팅했으니까.
//            mCalendar.add(Calendar.DATE, 1); // 다음날
//        }



    }

    public void prevMonth(View view) {
        mCalendar.add(Calendar.MONTH, -1);
        setUpAdapter();

    }

    public void nextMonth(View view) {
        mCalendar.add(Calendar.MONTH, 1);
        setUpAdapter();
    }

    private void setUpAdapter() {
        CalendarAdapter adapter = new CalendarAdapter(mCalendar);
        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
    }
}
