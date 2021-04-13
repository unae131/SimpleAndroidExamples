package com.example.intentexam1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


class CalcActivity extends AppCompatActivity {
    public void mainOnClick(View view) {
        // 명시적 인텐트
//        // 방법 1 잘 안씀
//        Intent intent = new Intent();
//        ComponentName comName = new ComponentName("com.example.intentexam1", "com.example.intentexam1.SnowAniActivity");
//        intent.setComponent(comName);
//        startActivity(intent); // intent 실행

        // 방법 2
//        startActivity(new Intent(this, SnowAniActivity.class));

        // 암시적 인텐트: 매니페스트에 컴포넌트가 등록되어 있어야한다.
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SNOW");
        //// 액티비티간 데이터 전달
        intent.putExtra("TOAST", "from MainActivity...");
        startActivity(intent);
    }

    // 암시적 인텐트
    public void mainMapOnClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN); // 화면을 띄우겠다
        intent.addCategory(Intent.CATEGORY_APP_MAPS);
        startActivity(intent);
    }

    // 쓰레드 테스트1
    int count = 0;
    public void mainThreadClick(View v){ // 이 방법 쓰면 누를 때의 count를 보여줌
        TextView tv = findViewById(R.id.main_counter_tv);
        tv.setText("count "+ count);
    }

    // 쓰레드 테스트2
    Handler handler = new Handler(); // onCreate 바깥에 생성

    // 쓰레드 테스트3
    int click_num = 0;
    float x_pos = 0;
    float y_pos = 0;
    //    int frameId = -1;
//    Handler handler = new Handler(); // onCreate 바깥에 생성
    ArrayList<FrameLayout> frameLayouts = new ArrayList<>();
    ArrayList<Float> velocities = new ArrayList<>();
    FrameLayout.LayoutParams framePr = new FrameLayout.LayoutParams(50,50);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 잡 쓰레드 테스트2
//        Thread thr = new Thread("Counter") { // 안에 이름 써줘도 되고, 안써줘도 됨
//            @Override
//            public void run() { // 필수
//                for(int i = 0; i < 30; i++){
//                    count++;
//
//                    // 아래는 정석적인 버전
////                    // 실행코드가 담긴 객체 하나를 생성(정해진 form)
////                    Runnable runnable = new Runnable() {
////                        @Override
////                        public void run() { // job
////                            TextView tv = findViewById(R.id.main_counter_tv);
////                            tv.setText("count " + count);
////                        }
////                    };
////                    // 메세지 큐에 담을 메세지 생성
////                    Message msg = Message.obtain(handler, runnable);
////                    // 핸들러를 통해 메세지를 메세지큐로 보냄
////                    handler.sendMessage(msg);
//
//                    // 왼쪽 절차가 함축된 버전. 이것을 쓰자
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            TextView tv = findViewById(R.id.main_counter_tv);
//                            tv.setText("count " + count);
//                        }
//                    });
//
//                    try{
//                        Thread.sleep(1000); // 1초마다 count++
//                    } catch (InterruptedException ie){
//                        ie.printStackTrace();
//                    }
//                }
//            }
//        };
//        thr.start(); // 쓰레드 실행. 필수!

        // 잡 쓰레드 테스트3
        Thread thr1 = new Thread("moving squares"){
            @Override
            public void run() {
                FrameLayout frame = findViewById(R.id.main_frame);
                Frame_touch frame_touch = new Frame_touch();
                frame.setOnTouchListener(frame_touch);

                for(;;){// 각 프레임(1/1000초에 나타나는 장면)
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView xTv = findViewById(R.id.main_x_tv);
                            TextView yTv = findViewById(R.id.main_y_tv);
                            TextView clickTv = findViewById(R.id.main_click_tv);

                            xTv.setText(""+x_pos);
                            yTv.setText(""+y_pos);
                            clickTv.setText(""+click_num);

                            move();
                        }
                    });

                    try{
                        Thread.sleep(1); // 1초마다 count++
                    } catch (InterruptedException ie){
                        ie.printStackTrace();
                    }

                }
            }
        };
        thr1.start();

    }
    public void move(){
        for(int i = 0; i < frameLayouts.size();i++){
            velocities.set(i, velocities.get(i) + 0.01f);
            frameLayouts.get(i).setX(frameLayouts.get(i).getX() + velocities.get(i)); // 가속
        }
    }

    class Frame_touch implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            click_num = event.getAction();
            if(click_num == 0) { // 눌렀을 때
                x_pos = event.getX();
                y_pos = event.getY();

                // 새로운 frame 생성
                FrameLayout frame = new FrameLayout(CalcActivity.this);
                frame.setBackgroundColor(Color.GREEN);
                frame.setX(x_pos - framePr.width/2);
                frame.setY(y_pos - framePr.height/2);
                frameLayouts.add(frame);
                velocities.add(0.0f);

                // 기존의 부모 frame 가져오기
                FrameLayout parentFrame = findViewById(R.id.main_frame);
                parentFrame.addView(frame, framePr);
            }
            return true; // true여야 계속 반복
        }
    }

}