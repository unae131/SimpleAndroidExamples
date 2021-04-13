package com.example.intentexam1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JavaOnlyActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout.LayoutParams linearpr = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams pr = new LinearLayout.LayoutParams(400, 200);

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundColor(Color.GRAY);

        TextView tv = new TextView(this);
        tv.setText("textview");
        tv.setBackgroundColor(Color.parseColor("#FCAD2C"));

        ImageView img = new ImageView(this);
        img.setImageResource(R.mipmap.ic_launcher);

        Button bt1 = new Button(this);
        bt1.setText("abc");

        EditText editText = new EditText(this);
        editText.setText("please input message");
        editText.setWidth(300);

        linear.addView(tv, pr);
        linear.addView(img, pr);
        linear.addView(bt1, pr);
        linear.addView(editText, pr);

        setContentView(linear, linearpr);

    }
}
