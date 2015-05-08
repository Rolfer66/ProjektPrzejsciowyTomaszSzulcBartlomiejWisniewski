package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Canvas;
import android.view.View;

import java.util.Random;


public class Zygzak extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasView(this));
        /* ustawienie pelnego ekranu
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
         */
    }

    class CanvasView extends View {
        Random random = new Random();

        public CanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(80, 80, 80);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            //paint.setARGB(255,125,235,56);
            Path p = new Path();
            p.moveTo(400,500);
            p.lineTo(150, 300);
            p.lineTo(250,350);
            //p.close();
            canvas.drawPath(p,paint);

            //canvas.drawLine(0,0,500,890,paint);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zygzak, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
