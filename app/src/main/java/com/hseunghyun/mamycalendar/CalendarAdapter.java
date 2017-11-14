package com.hseunghyun.mamycalendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Thunder on 2017-11-13.
 */

public class CalendarAdapter extends BaseAdapter {

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM.dd", Locale.getDefault());

//    private Calendar mCalender = Calendar.getInstance();

    private List<MyDate> mItems = new ArrayList<>();

    public CalendarAdapter(Calendar calendar) {
//        this.mCalender = mCalender;

        calendar.set(Calendar.DATE, 1); // 이번 달 1일
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1일 요일 (일요일 = 1 , 토요일은 = 7)
//        Log.d(TAG, "onCreate: " + dayOfWeek);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막 날

        // 요일 공백
        for (int i = 0; i < dayOfWeek - 1; i++) {
            mItems.add(null);
        }

        // 날짜 채우기
        for (int i = 0; i < lastDay; i++) {
            mItems.add(new MyDate(calendar.getTime())); // 1로 세팅했으니까.
            calendar.add(Calendar.DATE, 1); // 다음날
        }
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            // 항상 똑같은 코드
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);

            // 뷰홀더에 데이터(위치)를 넣어준다.
            holder.date = convertView.findViewById(R.id.day_text_view);
            holder.lune = convertView.findViewById(R.id.lune_text_view);

            //커ㅓㅂ트 뷰에있는 데이텍스트 뷰를 담아준다.
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MyDate myDate = mItems.get(position);

        if (myDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(myDate.getDate());

            holder.date.setText("" + c.get(Calendar.DATE));

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // 요일   (일요일 = 1, ~ 토요일 = 7)
            if (dayOfWeek == 7) {
                holder.date.setTextColor(Color.BLUE);
            } else if (dayOfWeek == 1) {
                holder.date.setTextColor(Color.RED);
            } else {
                holder.date.setTextColor(Color.BLACK);
            }

            c.setTime(myDate.getLune());
            holder.lune.setText("" + mSimpleDateFormat.format(c.get(Calendar.DATE)));

        } else {
            holder.date.setText("");
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView date;
        TextView lune;
    }
}
