package com.example.intentexam1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SnowAniActivity extends AppCompatActivity {
    public void snowOnClick(View v){
        FrameLayout.LayoutParams wrapParam = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);

        FrameLayout frame = findViewById(R.id.snow_frame);

        for(int i = 0; i < 50; i++){
            FrameLayout snowFrame = new FrameLayout(this);
            snowFrame.setX((float)Math.random()*1200 - 100);
            snowFrame.setY(-400f);

            ImageView iv = new ImageView(this);
            iv.setScaleX(0.15f);
            iv.setScaleY(0.15f);

            long wait_time = (long) (Math.random()*10000);
            AnimationSet aniSet = new AnimationSet(true);

            boolean isStar = false;
            if(Math.round(Math.random())%2 == 0){
                iv.setImageResource(R.drawable.white_circle);
            }else {
                iv.setImageResource(R.drawable.white_star);
                isStar = true;
            }

            snowFrame.addView(iv, wrapParam);
            frame.addView(snowFrame,wrapParam);

            // 별이면 회전
            if(isStar){
                RotateAnimation rotAni = new RotateAnimation(0,350,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotAni.setStartOffset(wait_time);
                rotAni.setDuration(5000);
                rotAni.setRepeatCount(-1);
                rotAni.setInterpolator(new DecelerateInterpolator());
//                aniSet.addAnimation(rotAni);
                iv.startAnimation(rotAni);
            }

            // 수평 이동
            TranslateAnimation transAni = new TranslateAnimation(0,0,0,2000);
//            transAni.setStartOffset(wait_time);
//            transAni.setDuration(5000);
//            transAni.setFillAfter(true);
            transAni.setRepeatCount(-1);
//            transAni.setInterpolator(new DecelerateInterpolator());
            aniSet.addAnimation(transAni);

            // 투명도
            AlphaAnimation alphaAni = new AlphaAnimation(1.0f,0.0f);
//            alphaAni.setStartOffset(wait_time);
//            alphaAni.setDuration(5000);
//            alphaAni.setFillAfter(true);
            alphaAni.setRepeatCount(-1);// 애니메이션 set에 설정하면 무시된다
            aniSet.addAnimation(alphaAni);

            //
            aniSet.setStartOffset(wait_time);
            aniSet.setDuration(5000);
            aniSet.setFillAfter(true);
            aniSet.setInterpolator(new DecelerateInterpolator());
            snowFrame.startAnimation(aniSet);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_snow);

        Intent intent = getIntent();
        String toastMsg = intent.getStringExtra("TOAST");
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
