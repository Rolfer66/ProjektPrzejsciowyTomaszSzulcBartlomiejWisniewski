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


import java.util.ArrayList;
import java.util.List;


public class Zygzak extends Activity {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path p = new Path();
    Paint WzorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path Wzor = new Path();
    List<Point> UserPt = new ArrayList<Point>();
    List<Point> PatternPt = new ArrayList<Point>();
    List<Double> Poprawnosc = new ArrayList<Double>();
    Wzory Wzor1 = new Wzory();
    double error=0;
    double Min=2000;
    double error1=0;
    int rozmiar =5;

    public double Error(){
        //UserPt.toArray();
        for(int i=0; i<PatternPt.size();i++){
             for(int j=0; j<UserPt.size();j++)
                 if(Math.sqrt(Math.pow(UserPt.get(j).getX() - PatternPt.get(i).getX(), 2) + Math.pow(UserPt.get(j).getY()-PatternPt.get(i).getY(),2)) < Min){
                    Min = Math.sqrt(Math.pow(UserPt.get(j).getX() - PatternPt.get(i).getX(), 2) + Math.pow(UserPt.get(j).getY() - PatternPt.get(i).getY(), 2));
                     Log.d("LOL","Blad: "+Min);
                 }
                 error = error + Min;
                Min=2000;
        }
        return error;
    };

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
            paint.setStrokeWidth(11f);
            paint.setColor(Color.RED);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            WzorPaint.setStyle(Paint.Style.STROKE);
            WzorPaint.setStrokeWidth(11f);
            WzorPaint.setColor(Color.YELLOW);
            WzorPaint.setStrokeJoin(Paint.Join.ROUND);
            WzorPaint.setStrokeCap(Paint.Cap.ROUND);
            WzorPaint.setPathEffect(new CornerPathEffect(15));
        }

        @Override
        public void onDraw(Canvas canvas) {

            //paint.setARGB(255,125,235,56);
            Wzor1.RysujWzor1();


            canvas.drawRGB(80, 80, 80);

            canvas.drawPath(Wzor, WzorPaint);



            canvas.drawPath(p, paint);

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
                    UserPt.add(new Point(eventX, eventY));

                    p.lineTo(eventX, eventY);
                    return true;
                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                    // TODO reakcja na zabranie palca
                    if(rozmiar==5) //sprawdzenie poprawnosci wykorzystania
                        error1=Error();
                    Log.d("dupa","Rozmiar: "+error1);
                    Log.d("lol","rozmiar wzoru: "+PatternPt.size());
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

    public class Wzory{
            public void RysujWzor1(){
                Wzor.moveTo(100,100); PatternPt.add(new Point(100,100));
                Wzor.lineTo(350,200); PatternPt.add(new Point(350, 200));
                Wzor.lineTo(150,350); PatternPt.add(new Point(150,350));
                Wzor.lineTo(350,500); PatternPt.add(new Point(350, 500));
                Wzor.lineTo(150,600); PatternPt.add(new Point(150,600));


              /*  Wzor.moveTo(100,100); PatternPt.add(new Point(100,100));
                Wzor.lineTo(150, 150); PatternPt.add(new Point(150, 150));
                Wzor.lineTo(200, 200); PatternPt.add(new Point(200, 200));
                Wzor.lineTo(250, 125); PatternPt.add(new Point(250, 125));
                Wzor.lineTo(300, 150); PatternPt.add(new Point(300, 150));
                Wzor.lineTo(350, 225); PatternPt.add(new Point(350, 225));
                Wzor.lineTo(400, 300); PatternPt.add(new Point(400, 300));
                Wzor.lineTo(350, 350); PatternPt.add(new Point(350, 350));
                Wzor.lineTo(300, 400); PatternPt.add(new Point(300, 400));
                Wzor.lineTo(240, 375); PatternPt.add(new Point(240, 375));
                Wzor.lineTo(150, 350); PatternPt.add(new Point(150, 350));
                Wzor.lineTo(125, 450); PatternPt.add(new Point(125, 450));
                Wzor.lineTo(100, 550); PatternPt.add(new Point(100, 550));
                Wzor.lineTo(175, 575); PatternPt.add(new Point(175, 575));
                Wzor.lineTo(250, 600); PatternPt.add(new Point(250, 600));
                Wzor.lineTo(300, 650); PatternPt.add(new Point(300, 650));
                Wzor.lineTo(350, 700); PatternPt.add(new Point(350, 700));
                Wzor.lineTo(300, 750); PatternPt.add(new Point(300, 750));
                Wzor.lineTo(250, 700); PatternPt.add(new Point(250, 700));
                Wzor.lineTo(175, 750); PatternPt.add(new Point(175, 750));
                Wzor.lineTo(100, 700); PatternPt.add(new Point(100, 700));
                Wzor.lineTo(275, 650); PatternPt.add(new Point(275, 650));
                Wzor.lineTo(400, 600); PatternPt.add(new Point(400, 600));
                Wzor.lineTo(375, 550); PatternPt.add(new Point(375, 550));
                Wzor.lineTo(350, 500); PatternPt.add(new Point(350,500)); */
            };
            public Wzory(){}
    }


/*

for (int i=0; i<= points.size(); i++) {
    int MinX=500, MinY=500;           //zakladamy maxymalny blad dla wsp x i y

    if(points[i].getX()-Punkty<MinX)         // sprawdzamy ktory punkt z naszego przebiegu jest najblizszy punktowi ze wzoru.
        MinX = points[i].getX();        // przypisujemy odpowiednie wartosci punktu
    if(points[i].getY()<MinY)
        MinY = points[i].getY();



}
 */

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
