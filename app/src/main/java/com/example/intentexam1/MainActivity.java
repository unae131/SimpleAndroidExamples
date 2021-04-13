package com.example.intentexam1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ArrayList<ImageView> imageViews = new ArrayList<>();
    ArrayList<Integer> a = new ArrayList<>();
    ArrayList<Integer> b = new ArrayList<>();
    String input = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews.add(findViewById(R.id.img_0));
        imageViews.add(findViewById(R.id.img_1));
        imageViews.add(findViewById(R.id.img_2));
        imageViews.add(findViewById(R.id.img_3));
        imageViews.add(findViewById(R.id.img_4));
        imageViews.add(findViewById(R.id.img_5));
        imageViews.add(findViewById(R.id.img_6));
        imageViews.add(findViewById(R.id.img_7));
        imageViews.add(findViewById(R.id.img_8));
        imageViews.add(findViewById(R.id.img_9));
        imageViews.add(findViewById(R.id.img_ac));
        imageViews.add(findViewById(R.id.img_plus));
        imageViews.add(findViewById(R.id.img_minus));
        imageViews.add(findViewById(R.id.img_equal));

        a.add(R.drawable.a0);
        a.add(R.drawable.a1);
        a.add(R.drawable.a2);
        a.add(R.drawable.a3);
        a.add(R.drawable.a4);
        a.add(R.drawable.a5);
        a.add(R.drawable.a6);
        a.add(R.drawable.a7);
        a.add(R.drawable.a8);
        a.add(R.drawable.a9);
        a.add(R.drawable.ac);
        a.add(R.drawable.ap);
        a.add(R.drawable.am);
        a.add(R.drawable.ae);

        b.add(R.drawable.b0);
        b.add(R.drawable.b1);
        b.add(R.drawable.b2);
        b.add(R.drawable.b3);
        b.add(R.drawable.b4);
        b.add(R.drawable.b5);
        b.add(R.drawable.b6);
        b.add(R.drawable.b7);
        b.add(R.drawable.b8);
        b.add(R.drawable.b9);
        b.add(R.drawable.bc);
        b.add(R.drawable.bp);
        b.add(R.drawable.bm);
        b.add(R.drawable.be);

        for (int i = 0; i < 14; i++) {
            imageViews.get(i).setOnTouchListener(new Button_touch());

        }
    }

    class Button_touch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView img = (ImageView) v;
            TextView tv = findViewById(R.id.calc_tv);

            int idx = imageViews.indexOf(img);
            if (event.getAction() == 0) { // push
                img.setImageResource(b.get(idx));
            } else if (event.getAction() == 1) {
                img.setImageResource(a.get(idx));

                if (idx < 10) {
                    input += Integer.toString(idx);
                    tv.setText(input);
                } else if (idx == 11) {
                    input += "+";
                    tv.setText(input);
                } else if (idx == 12) {
                    input += "-";
                    tv.setText(input);
                } else if (idx == 10) {
                    input = "";
                    tv.setText(input);
                } else { // =
                    String[] plus = input.split("\\+");
                    int sum = 0;
                    for (String p : plus) {
                        String[] minus = p.split("-");
                        if (minus.length >= 2) {
                            int min = Integer.parseInt(minus[0]);
                            for (int i = 1; i < minus.length; i++) {
                                min -= Integer.parseInt(minus[i]);
                            }
                            sum += min;
                        } else { // no minus
                            sum += Integer.parseInt(minus[0]);
                        }
                    }
                    input = Integer.toString(sum);
                    tv.setText(input);
                }
            }
            return true;
        }
    }
}
