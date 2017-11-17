package com.hseunghyun.mamycalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이번 달 데이터 달력.
        List<MyDate> dates = new ArrayList<>();

        // 지금
        Calendar calendar = Calendar.getInstance(); //여기에 달 연도 일 다 들어가 있음.
        calendar.set(Calendar.DATE, 1); // 이번 달 1일 ==> 캘린더데이트를 1로 설정.

        // 그러면 현재 calendar가 현재날짜가 아니라 1일! 그것에 요일을 알아보자!
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1일 요일 (일요일 = 1 , 토요일은 = 7)
        // 확실치 않다고 생각이 들면 로그를 찍어보자!
        Log.d(TAG, "onCreate: " + dayOfWeek); // 예상값 4 == 로그랑 같음
        Log.d(TAG, "onCreate: " + calendar.get(Calendar.MONTH)); // 예상값11월 == 10(달은 0부터 시작)

        // 그럼 이번달의 마지막 날을 구해보자
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막 날
        // 확실치 않다고 생각이 들면 로그를 찍어보자!
        Log.d(TAG, "onCreate: 마지막날" + lastDay); //예상 30 ==30

        // 2. 이제 리스트뷰에 넣어줄 데이터를 만들어주자!=======================
        // 요일 공백 dayoftheweek -1 해주면 공백이 생기겠지?
        for (int i = 0; i < dayOfWeek - 1; i++) {
            dates.add(null);
        }

        // 날짜 채우기
        for (int i = 0; i < lastDay; i++) {
            dates.add(new MyDate(calendar.getTime())); // 1로 세팅했으니까. 1부터 차근차근.
            calendar.add(Calendar.DATE, 1); // 다음날
        }
        //================================================================

//        Log.d(TAG, "onCreate: 룬의 아이를 찾습니다. " + dates.get(0).getLune());

        //데이터를 만들었으면 어뎁터를 만들어서 이걸 꽂아야 겠지?
        GridView gridView = findViewById(R.id.grid_view);
        CalendarAdapter adapter = new CalendarAdapter(dates);
        gridView.setAdapter(adapter);


//        Log.d(TAG, "onCreate: 메인에서도 안나오나?" + dates.get(0).getLune());

    }

//    public void prevMonth(View view) {
//        mCalendar.add(Calendar.MONTH, -1);
//        setUpAdapter();
//
//    }
//
//    public void nextMonth(View view) {
//        mCalendar.add(Calendar.MONTH, 1); //한달 후
//        setUpAdapter();
//    }

//    private void setUpAdapter() {
//        CalendarAdapter adapter = new CalendarAdapter(mCalendar);
//        GridView gridView = findViewById(R.id.grid_view);
//        gridView.setAdapter(adapter);
//    }
}
