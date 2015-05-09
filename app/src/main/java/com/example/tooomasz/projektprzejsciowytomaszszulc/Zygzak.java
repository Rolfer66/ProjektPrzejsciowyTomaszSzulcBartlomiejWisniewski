package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Zygzak extends Activity {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path p = new Path();
    List<Point> points = new ArrayList<Point>();




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
        //Random random = new Random();

        public CanvasView(Context context) {
            super(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(6f);
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(80, 80, 80);
            canvas.drawPath(p, paint);


            //paint.setARGB(255,125,235,56);


            /*
            p.moveTo(50, 50);
            p.lineTo(100, 50);
            p.lineTo(100, 100);
            p.lineTo(80, 100); // cos
            */




        }
        public boolean onTouchEvent(MotionEvent event)
        {
            float eventX = event.getX();
            float eventY = event.getY();

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:   // Wykryto  nowy dotyk
                    p.moveTo(eventX,eventY);
                    return true;
                case MotionEvent.ACTION_MOVE:   // Przesunięcie palca
                    points.add(new Point(eventX, eventY));

                    p.lineTo(eventX, eventY);
                    return true;
                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                    // TODO reakcja na zabranie palca
                    int d = points.size();
                    Log.d("dupa","Rozmiar: "+d);

                    break;
                default:
                    return false;
            }
            invalidate();
            return true;
        }

    }

    public class Point
    {
        private float x,y;
        public float getX() { return x;}
        public float getY() { return y;}
        public void setX(int x) { this.x=x;}
        public void setY(int y) { this.y=y;}

        public Point() {}
        public Point(float x,float y) { this.x=x; this.y=y;}
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
