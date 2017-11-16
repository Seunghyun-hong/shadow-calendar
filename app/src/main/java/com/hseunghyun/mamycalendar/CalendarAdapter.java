package com.hseunghyun.mamycalendar;

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

    private List<MyDate> mItems = new ArrayList<>(); //실제 데이터를 받아온다

    // 데이터를 받는 방법이 없어서 생성자를 만들어서 데이터를 받자!
    public CalendarAdapter(List<MyDate> mItems) {
        this.mItems = mItems;
    }

    // 데이터 갯수 // 그래서 mItem.size() 받아오면 우리는 사이즈로 배열의 크기를 알 수 있지.
    @Override
    public int getCount() {
        return mItems.size(); // 그러면 데이터가 잘 나옴.. 이거 0 으로 해버리면 아예 안나옴
    }

    // position(위치 0,1,2,3,,,,) 번째에 어떤 데이터가 있는지 알려줘야 함.
    @Override
    public Object getItem(int position) {
        return position;
    }

    // 데이터베이스 id // 데이터베이스 없으면 걍 포지션으로..
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position번째의 레이아웃 완성 해서 알려줘야 함
    // convertView - position번째의 레이아웃의 레퍼런스
    // parent - 이 어댑터가 붙을 부모의 레퍼런스 (ListView나 GridView)
    @Override // 여기만 바뀐다.
    public View getView(int position, View convertView, ViewGroup parent) {
        // 여기서 View getView 로 하는건 어째는 리스트뷰든 그리드뷰든 각각 하나의 뷰를 모아둔 것이기 때문에
        // 결과물은 뷰!! 로 줘야 한다. 그래서 convertView를 리턴 해주는거 같다.

        // position 은 해당 위치인거고.

        // 여기서 View convertView 는 이 컨버터뷰가 레퍼런스를 줍니다. 그거로 리턴되는 애가 다시 view convertView 에 들어온데..
        // 뭔말이야 하면 getView가 해당번째의 뷰를 만들어 주는 역할을 하고 있잖아?!
        // 그러면 화면에 리스트뷰형태가 5개가 있다면 getView 는 5번이 불린거다.
        // 그러고 밑으로 내리면 새로 뷰가 만들어 질꺼고... 그러고 다시 위로 올리면 아까 만들었던 뷰가 convertView 에 들어오는거래..
        // 그래서 최초엔 null이래.. 기존에 생긴게 없었으니까.

        // ViewGroup parent 는 실제로 이 어뎁터가 어디에 붙을지는 모르는거다.
        // 그 부모, 그 부모는 뷰그룹이기 때문에 그 부모의 레퍼런스를 가져오는거다.
        // 만약 그 부모의 속성을 쓰고 싶을때 parent를 캐스팅해서 쓰면 된다.

        ViewHolder holder;
        if (convertView == null) {
//==================================================================================
            // 최초 ==항상 쓰는 패턴. ====================
            // convertView 가 null 이니까 뷰를 붙여줘야되.. 최초엔.
            // 그래서 우리가 만든 아이템뷰를 붙어줄꺼야.
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false); // 레이아웃 가지고 오기
            // convertView 에 뷰를 엮어야 하는데 레이아웃인플레이터.프롬 해서 context를 주고,
            //(리소스를 가지고 올때 레이아웃인플레이터를 쓴다)
            // 거기에 inflate('붙이겠다')가 있고 그 안에 레이아웃을 지정해주고,
            // 얘가 어디에 붙을지 부모의 레퍼런스를 적어주고 ,
            // 이게 루트엘리먼트(root-Element=최상위 엘리먼트/최상위 객체?!) 인지 아닌지 알려주면 끝! (최상위 레이아웃이 아니기 때문에)
            // 그런데 이 getView는  모두 차일드(자식)이기 때문에 false
//===================================================================================
            // 이제 데이터수정하고 하려면 아이템뷰에 있는 얘들의 레퍼런스를 알고 있어야 하는데
            // 그래서 우리는 id를 지정해서 왔지.
            // 그런데 그냥 findViewById가 없다. 루트레이아웃이 없어서.. 그런데 이역할을 convertView 가 해줌.
            // convertView.findViewById(R.id.day_text_view);

            // 레이아웃 가지고 오기 =======================
//            TextView dayTextView = (TextView) convertView.findViewById(R.id.day_text_view);
//            TextView luneTextView = (TextView) convertView.findViewById(R.id.lune_text_view);
//            holder.date = dayTextView;
//            holder.lune = luneTextView;
            //홀더 쓰고 있으니까. 바로 홀더에 넣어주자
            holder = new ViewHolder();
            holder.date = convertView.findViewById(R.id.day_text_view);
            holder.lune = convertView.findViewById(R.id.lune_text_view);




            // 그러고 컨버터뷰에 셋테그에 연결해주자.=================
            convertView.setTag(holder); //이 태그는 별다른 기능없이 약간에 꼬리표 붙여주는 개념? 저장개념이라고 생각하면 되려나?
            // 그래서 이걸 써줌으로써 태그 들렁오니까 재활용 할때도 이용할 수 있지.


        } else {
            //재사용 (레이아웃인플레이터 여러번 쓰면 성능이 엄청 떨어진데.. 그래서 재활용!)
            holder = (ViewHolder) convertView.getTag();
        }

        // 데이터 가져와서 뿌리기!!==============================
        // data  (데이터는 뭐 건들지 않았음.)
        MyDate myDate = mItems.get(position); // 해당번째의 데이터를 가져온다.
//        MyDate myDate = (MyDate) getItem(position); // 해당번째의 데이터를 가져온다. 2가지 방법이 있다.

        // 그런데 우리는 1일 요일이 일요일이 아니면 공백을 넣어줬기 때문에 분기타줘야한다.
        if (myDate != null) {
            Calendar c = Calendar.getInstance(); // 날짜를 가져온다
            c.setTime(myDate.getDate()); // 그 c값을 해당번째 날짜로 바꿔준다.

            // 뿌리기  (뿌릴땐 holder가 들고 있으니까 홀더로 바꿔주면 됨.)
            holder.date.setText(""+c.get(Calendar.DATE));
            // c가 해당번째 날짜(만약 2라면)로 바뀌었으니까 그아이의 캘린더.데이트는 해당일(2)이 나오겠지.

            // 같은 방식으로 음력도 넣어준다.
            c.setTime(myDate.getLune());
            holder.lune.setText(""+mSimpleDateFormat.format(c.get(Calendar.DATE)));
        } else {
            //데이터가 없다면..
            holder.date.setText("");
            holder.lune.setText("");
        }

        return convertView;
    }

    // 뷰홀더를 만들어 준다.
    private static class ViewHolder {
        TextView date;
        TextView lune;
    }
}
