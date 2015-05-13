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
import java.util.Random;

import java.util.ArrayList;
import java.util.List;


public class Zygzak extends Activity {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path p = new Path();
    Paint WzorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path Wzor = new Path();

    List<Point> UserPt = new ArrayList<Point>();
    List<Point> PatternPt = new ArrayList<Point>();

    Wzory Wzor1 = new Wzory();

    public double Error(){

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
        int rozmiar=0;
        int rand=0;
       // Random r = new Random();
     //   int znak=0;





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
           // int op=0;
               //Losowanie wzorow z bazy
        //    rand = r.nextInt(2);
            /*switch(rand){
                case 0:
                   Wzor1.RysujWzor1();
                   rozmiar = 5;
                    break;
                case 1:
                    Wzor1.RysujWzor2();
                    rozmiar = 25;
                    break;
            }
            */
            Wzor1.RysujWzor1(); //Wyrysowanie wzoru metoda z klasy Wzory.
            rozmiar = 5;   //WZOR ZMIEN W PETLI!!!

            canvas.drawRGB(80, 80, 80);
            canvas.drawPath(Wzor, WzorPaint);  //rysowanie wzoru
            canvas.drawPath(p, paint);      //rysowanie ruchu uzytkownika

        }

        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            double poprawnosc;
            double BladGlowny = 0;


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:   // Wykryto  nowy dotyk

                    p.moveTo(eventX, eventY);
                    return true;

                case MotionEvent.ACTION_MOVE:   // Przesunięcie palca

                    UserPt.add(new Point(eventX, eventY));
                    p.lineTo(eventX, eventY);
                    return true;

                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                    Context contextT = getApplicationContext();
                    rozmiar = PatternPt.size();
                    //sprawdzenie poprawnosci wykorzystania//

                    if (rozmiar == 5) {
                        BladGlowny = Error();
                        Log.d("nazwa", "Blad ostateczny:: " + BladGlowny);  //podglad sumy odleglosci od wszystkich punktow w pikselach
                        Log.d("nazwa1", "rozmiar wzoru: " + PatternPt.size()); //podglad rozmiaru wzoru

                        poprawnosc = 100 - BladGlowny / rozmiar;            //NASZA SKALA OCENIANIA Ilosc pikseli/5;
                        poprawnosc *= 100; // pi = pi * 100;    ///zaokraglenie liczby do dwoch miejsc po przecinku
                        poprawnosc = Math.round(poprawnosc);
                        poprawnosc /= 100; // pi = pi / 100;
                        String message = "Poprawność: " + (poprawnosc) + "% dla " + PatternPt.size() + " badanych punktow , Suma Pikseli: " + BladGlowny;  // komunikat o uzyskanym wyniku
                        Toast toast = Toast.makeText(contextT, message, Toast.LENGTH_LONG);
                        toast.show();

/*
                        znak=rand;
                        rozmiar = 0;
                        do {
                            rand = r.nextInt(2);
                        }while(znak!=rand);
*/
                            break;
                     }
                    else {
                        Log.d("nazwa1", "rozmiar wzoru: " + PatternPt.size()); //podglad rozmiaru wzoru
                        String ZlyRozmiar = "Oderwales palec od ekranu. Sprobuj jeszcze raz! Rozmiar: " + rozmiar +"dla rand: "+rand;  // komunikat o uzyskanym wyniku
                        Toast toast = Toast.makeText(contextT, ZlyRozmiar, Toast.LENGTH_LONG);
                        toast.show();
  /*                      znak=rand;
                        rozmiar = 0;
                        do {
                            rand = r.nextInt(2);
                        }while(znak!=rand);
*/
                        break;
                    }
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
            Wzor.lineTo(150, 150);
            PatternPt.add(new Point(150, 150));
            Wzor.lineTo(200, 200);
            PatternPt.add(new Point(200, 200));
            Wzor.lineTo(250, 125);
            PatternPt.add(new Point(250, 125));
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
            Wzor.lineTo(125, 450);
            PatternPt.add(new Point(125, 450));
            Wzor.lineTo(100, 550);
            PatternPt.add(new Point(100, 550));
            Wzor.lineTo(175, 575);
            PatternPt.add(new Point(175, 575));
            Wzor.lineTo(250, 600);
            PatternPt.add(new Point(250, 600));
            Wzor.lineTo(300, 650);
            PatternPt.add(new Point(300, 650));
            Wzor.lineTo(350, 700);
            PatternPt.add(new Point(350, 700));
            Wzor.lineTo(300, 750);
            PatternPt.add(new Point(300, 750));
            Wzor.lineTo(250, 700);
            PatternPt.add(new Point(250, 700));
            Wzor.lineTo(175, 750);
            PatternPt.add(new Point(175, 750));
            Wzor.lineTo(100, 700);
            PatternPt.add(new Point(100, 700));
            Wzor.lineTo(275, 650);
            PatternPt.add(new Point(275, 650));
            Wzor.lineTo(400, 600);
            PatternPt.add(new Point(400, 600));
            Wzor.lineTo(375, 550);
            PatternPt.add(new Point(375, 550));
            Wzor.lineTo(350, 500);
            PatternPt.add(new Point(350, 500));
            };


            public Wzory(){}
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
