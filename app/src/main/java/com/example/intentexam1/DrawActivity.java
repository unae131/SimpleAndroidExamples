package com.example.intentexam1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DrawActivity extends AppCompatActivity {
    public void drawBtnOnClick(View view) {
        LinearLayout linear = findViewById(R.id.draw_linear);
        DrawView dv = new DrawView(this);
        linear.addView(dv);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
    }

    class DrawView extends View{

        public DrawView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas){
            Paint pnt = new Paint();
            pnt.setColor(Color.GREEN);
            pnt.setStrokeWidth(10f);
            pnt.setAntiAlias(true);
            pnt.setStyle(Paint.Style.FILL_AND_STROKE);

            canvas.drawColor(Color.WHITE);
            canvas.drawPoint(100f,200f,pnt);
            canvas.drawLine(0f,0f,100f,100f,pnt);
            canvas.drawCircle(400f,400f,150f,pnt);

            pnt.setStrokeWidth(10f);
            pnt.setStyle(Paint.Style.FILL);
            pnt.setTextSize(200f);
            String str = "abcde";
            canvas.drawText(str,400f,700f,pnt);
            Resources res = getResources();
            BitmapDrawable bd = (BitmapDrawable) res.getDrawable(R.drawable.circle, null);
            Bitmap bit = bd.getBitmap();
            canvas.drawBitmap(bit,400f,1000f,pnt);

            Path path = new Path();
            pnt.setStyle(Paint.Style.FILL_AND_STROKE);
            path.moveTo(400f,1400f);
            path.lineTo(600f,1400f);
            path.lineTo(800f,1800f);
            path.addCircle(800f,1800f,100f,Path.Direction.CW);
            canvas.drawPath(path,pnt);
        }
    }
}