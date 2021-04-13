package com.example.intentexam1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EventListenerAndXmlActivity extends AppCompatActivity {
    public void xmlOnClick (View v){
        Button btn = (Button) v;
        btn.setText("wow");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_and_xml);

        FrameLayout frame = findViewById(R.id.touch_listener_frame);
        Frame_touch ftch =new Frame_touch();
        frame.setOnTouchListener(ftch);

        Button bt = findViewById(R.id.touch_listener_btn);
        Button_touch btch = new Button_touch();
        bt.setOnTouchListener(btch);

        Long_click bLclick = new Long_click();
        bt.setOnLongClickListener(bLclick);
    }

    class Frame_touch implements ViewGroup.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.setBackgroundColor(Color.GREEN);
            Log.d("Frame_touch",""+ event.getAction());
            return false; // 누를 때마다 작동
        }
    }

    class Button_touch implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.setBackgroundColor(Color.BLUE);
            Log.d("button_touch",""+ event.getAction());
            Button btn = findViewById(R.id.touch_listener_btn);
            if(event.getAction() == 0){
                btn.setText("눌렸네?!");
            }
            else if(event.getAction()== 2){
                btn.setText("움직였네?!");
            }
            else
                btn.setText("누르시오");
            return false;
        }
    }

    class Long_click implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            Button btn = findViewById(R.id.touch_listener_btn);
            btn.setText("long");
            return false;
        }
    }
}

