package com.example.intentexam1;

import android.content.ContentQueryMap;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

class Student {
    String name;
    String number;
}

public class ArrayViewActivity extends AppCompatActivity {
    BaseAdapterEx baseAdapterEx;

    public void arrayviewMoveOnClick(View v){
        startActivity(new Intent(this, SnowAniActivity.class));
    }
    public void arrayviewOnclick(View v) {
        baseAdapterEx.add();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrayview);

        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            students.add(new Student());
            students.get(i).name = "student";
            students.get(i).number = "" + i;
        }

        baseAdapterEx = new BaseAdapterEx(this, students);
        ListView listView = findViewById(R.id.array_listview);
        listView.setAdapter(baseAdapterEx);

        listView.setDivider(new ColorDrawable(Color.GRAY)); // 리스트 선들
        listView.setDividerHeight(3); // 리스트 행 높이
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // adapterView : listView, view : 하나의 item, position: index, id : 일종의 id(어댑터가 관리)
                Toast.makeText(ArrayViewActivity.this, // arrayViewActivity 꼭 써줘야함
                        "Click "+position+"번째 "+",id: "+id, LENGTH_LONG).show();
            }
        });
    }
}

class BaseAdapterEx extends BaseAdapter {
    Context mContext;
    ArrayList<Student> mData;
    LayoutInflater mLayoutInflater;

    public BaseAdapterEx(Context context, ArrayList<Student> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext); // 여기서 가져오겠다
    }

    public void add() {
        mData.add(new Student());
        mData.get(mData.size() - 1).name = "newStudent";
        mData.get(mData.size() - 1).number = "" + (mData.size() - 1);
        notifyDataSetChanged(); // 데이터가 바뀌면 항상 이것을 실행해줘야 한다.
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView name_tv;
        TextView number_tv;
    }

    //정해진 method로 매 줄(1줄)을 화면에 보여주는 역할을 한다.(스크롤중에도 항상 실행)
    //
    //여러개의 list 중 일부만 화면에 보일 수 있는 것이 보이는 개수만큼 횟수의 이 함수가 시행되면서 뷰들이 보이게 되는 것.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // position에 포함되어 있는 애들만 보여준다
        // converView = 한 번 봤던 view인지

        // 이렇게 함으로써 메모리 줄일 수 있다
        View itemLayout = convertView;
        ViewHolder viewHolder = null;

        if (itemLayout == null) {
            itemLayout = mLayoutInflater.inflate(R.layout.list_view_layout, null);
            // 이렇게 만든 view 직접 layout에 add할 수도 있다

            viewHolder = new ViewHolder();
            viewHolder.name_tv = itemLayout.findViewById(R.id.list_name_tv);
            viewHolder.number_tv = itemLayout.findViewById(R.id.list_number_tv);
            itemLayout.setTag(viewHolder); // 다시 볼 수 있도록
        } else {
            viewHolder = (ViewHolder) itemLayout.getTag(); // 저장해놓은 tag가져옴
        }

        // 아래 findViewById느 id를 처음부터 모두 검색하여 일치하는 것을 찾는다 -> 비효율적
//        TextView name = itemLayout.findViewById(R.id.list_name_tv);
//        TextView number = itemLayout.findViewById(R.id.list_number_tv);

        viewHolder.name_tv.setText(mData.get(position).name);
        viewHolder.number_tv.setText(mData.get(position).number);

        return itemLayout;
    }
}
