package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
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
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;




public class Zygzak extends Activity {

    Path Wzor = new Path();
    Path drawPath = new Path();
    List<Point> PatternPt = new ArrayList<Point>();
    List<Path> paths = new ArrayList<Path>();
    Tory Tor1 = new Tory();
    int rand=0;
    Random r = new Random();

    /*public double Error(){
        double Min=2000;
        double error=0;
        for(int i=0; i<PatternPt.size();i++){
             for(int j=0; j<UserPt.size();j++)
                 if(Math.sqrt(Math.pow(UserPt.get(j).getX() - PatternPt.get(i).getX(), 2) + Math.pow(UserPt.get(j).getY()-PatternPt.get(i).getY(),2)) < Min){
                    Min = Math.sqrt(Math.pow(UserPt.get(j).getX() - PatternPt.get(i).getX(), 2) + Math.pow(UserPt.get(j).getY() - PatternPt.get(i).getY(), 2));
                     Log.d("Blad","Blad z petli: "+Min);
                 }
                 error = error + Min;
                Min=2000;
        }
        return error;
    };*/

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

    public class CanvasView extends View implements Serializable {

        Paint drawPaint, WzorPaint;
        Canvas drawCanvas;

        public CanvasView(Context context) {
            super(context);
            setupDrawing();
        }

        void setupDrawing()
        {
            WzorPaint = new Paint();
            drawPaint = new Paint();
            drawCanvas = new Canvas();

            WzorPaint.setStyle(Paint.Style.STROKE);
            WzorPaint.setStrokeWidth(11f);
            WzorPaint.setColor(Color.YELLOW);
            WzorPaint.setStrokeJoin(Paint.Join.ROUND);
            WzorPaint.setStrokeCap(Paint.Cap.ROUND);
            WzorPaint.setPathEffect(new CornerPathEffect(15));

            drawPaint.setStyle(Paint.Style.STROKE);
            drawPaint.setStrokeWidth(11f);
            drawPaint.setColor(Color.RED);
            drawPaint.setStrokeJoin(Paint.Join.ROUND);
            drawPaint.setStrokeCap(Paint.Cap.ROUND);

        }

        @Override
        public void onDraw(Canvas canvas) {
            //Losowanie wzorow z bazy

            switch(rand){
                case 0:
                   Tor1.RysujWzor1();
                    break;
                case 1:
                    Tor1.RysujWzor2();
                    break;
                case 2:
                    Tor1.RysujWzor1();
                    break;
            }

            //Tor1.RysujWzor2(); //Wyrysowanie wzoru metoda z klasy Wzory.

            canvas.drawRGB(80, 80, 80);
            canvas.drawPath(Wzor, WzorPaint);  //rysowanie wzoru
            canvas.drawPath(drawPath, drawPaint);
        }

        //@Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:   // Wykryto  nowy dotyk

                    drawPath.moveTo(eventX, eventY);
                   return true;

                case MotionEvent.ACTION_MOVE:   // Przesunięcie palca

                    drawPath.lineTo(eventX, eventY);
                    //drawCanvas.drawPath(drawPath, drawPaint);
                    //drawPath.moveTo(eventX, eventY);
                    return true;

                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                 //  drawCanvas.drawPath(drawPath, drawPaint);
                   // paths.add(drawPath);
                    Log.d("cos", "Liczba sciezek: " + paths.size());
                    drawPath.reset();
                    Wzor.reset();
                    rand+=1;

                    Log.d("cos", "rand: " + rand);
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

    public class Tory{

            public void RysujWzor1() {
                Wzor.moveTo(100, 100);
                PatternPt.add(new Point(100, 100));
                Wzor.lineTo(350, 200);
                PatternPt.add(new Point(350, 200));
                Wzor.lineTo(150, 350);
                PatternPt.add(new Point(150, 350));
                Wzor.lineTo(350, 500);
                PatternPt.add(new Point(350, 500));
                Wzor.lineTo(150, 600);
                PatternPt.add(new Point(150, 600));
            }

             public void RysujWzor2() {
            Wzor.moveTo(100, 100);
            PatternPt.add(new Point(100, 100));
            Wzor.lineTo(200, 200);
            PatternPt.add(new Point(200, 200));
            Wzor.lineTo(300, 150);
            PatternPt.add(new Point(300, 150));
            Wzor.lineTo(350, 225);
            PatternPt.add(new Point(350, 225));
            Wzor.lineTo(400, 300);
            PatternPt.add(new Point(400, 300));
            Wzor.lineTo(350, 350);
            PatternPt.add(new Point(350, 350));
            Wzor.lineTo(300, 400);
            PatternPt.add(new Point(300, 400));
            Wzor.lineTo(240, 375);
            PatternPt.add(new Point(240, 375));
            Wzor.lineTo(150, 350);
            PatternPt.add(new Point(150, 350));
            Wzor.lineTo(100, 550);
            PatternPt.add(new Point(100, 550));
            Wzor.lineTo(250, 600);
            PatternPt.add(new Point(250, 600));
            Wzor.lineTo(300, 650);
            PatternPt.add(new Point(300, 650));
            Wzor.lineTo(300, 750);
            PatternPt.add(new Point(300, 750));
            Wzor.lineTo(175, 750);
            PatternPt.add(new Point(175, 750));
            Wzor.lineTo(100, 700);
            PatternPt.add(new Point(100, 700));
            Wzor.lineTo(400, 600);
            PatternPt.add(new Point(400, 600));
            Wzor.lineTo(350, 500);
            PatternPt.add(new Point(350, 500));
            };
            public Tory(){}
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
