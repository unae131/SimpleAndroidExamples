package com.example.intentexam1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SquareAniActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_square);

        FrameLayout frame = findViewById(R.id.sqrAni_frame);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout innerFrame = new FrameLayout(this);
        innerFrame.setX(300f);
        innerFrame.setY(300f);
        innerFrame.setBackgroundColor(Color.GREEN);

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.star);
        iv.setScaleX(0.7f);
        iv.setScaleY(0.7f);

        innerFrame.addView(iv, params);
        frame.addView(innerFrame, params);

//        AnimationSet aniSet = new AnimationSet(true);
        Animation transAni = new TranslateAnimation(200f, 300f, 0f, 1000f); // animation을 적용할 view의 X,Y가 set되어있다면 X + fromXDelta에서 시작하는 것.
        transAni.setStartOffset(2000);
        transAni.setDuration(3000);
        transAni.setFillAfter(true);
        transAni.setRepeatCount(0);
//        transAni.setFillBefore(false);

        LinearInterpolator linearInterpolator = new LinearInterpolator();
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();

        transAni.setInterpolator(accelerateInterpolator);
        innerFrame.startAnimation(transAni);
////        iv.startAnimation(transAni);
//
//        aniSet.addAnimation(transAni);
////        iv.startAnimation(aniSet);
//        innerFrame.startAnimation(aniSet);
//
        AnimationSet aniSet = new AnimationSet(true);
        Animation rotAni = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotAni.setDuration(3000);
        rotAni.setRepeatCount(-1);

        aniSet.addAnimation(rotAni);
////        transAni.setInterpolator(new AccelerateInterpolator());
//        iv.startAnimation(rotAni);
////        innerFrame.startAnimation(aniSet);

//        Animation scaleAni = new ScaleAnimation(1.0f,2.0f,1.0f,2.0f);
//        scaleAni.setDuration(3000);
//        aniSet.addAnimation(scaleAni);
//        iv.startAnimation(scaleAni);
////
        Animation alphaAni = new AlphaAnimation(1.0f,0.5f);
        alphaAni.setDuration(1000);
        alphaAni.setRepeatCount(5);
//        alphaAni.setFillAfter(true);
        aniSet.addAnimation(alphaAni);

//        iv.startAnimation(alphaAni); // 이 경우 마지막 것만 적용
        iv.startAnimation(aniSet);
    }
}
