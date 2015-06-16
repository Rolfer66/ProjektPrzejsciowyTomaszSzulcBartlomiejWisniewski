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
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpHost;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import android.widget.EditText;
import android.view.MenuItem;
import android.view.View.OnClickListener;



public class Zygzak extends ActionBarActivity {



    Path Wzor = new Path();
    Path drawPath = new Path();
    List<Point> PatternPt = new ArrayList<Point>();
    List<Point> PathPoints = new ArrayList<Point>();
    Tory Tor1 = new Tory();
    int rand=0;
    int Licznik=1;    // zmienna do liczenia wystąpień rysowanych wzorów
    Random r = new Random();
    int flaga=0;

    String user;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new CanvasView(this));
        Intent t = getIntent();
        user = t.getStringExtra("ImieUzytkownika");//pobranie nazwy uzytkownika z edittext


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
                    Tor1.RysujWzor3();
                    break;
                case 3:
                    Tor1.RysujWzor4();
                    break;
                case 4:
                    Tor1.RysujWzor5();
                    break;
                case 5:
                    Tor1.RysujWzor6();
                    break;
                case 6:
                    Tor1.RysujWzor7();
                    break;
                case 7:
                    Tor1.RysujWzor8();
                    break;
                case 8:
                    Tor1.RysujWzor9();
                    break;
                case 9:
                    Tor1.RysujWzor10();
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
                    PathPoints.add(new Point(eventX,eventY));
                    return true;

                case MotionEvent.ACTION_MOVE:   // Przesunięcie palca

                    drawPath.lineTo(eventX, eventY);
                    PathPoints.add(new Point(eventX, eventY));
                    //drawCanvas.drawPath(drawPath, drawPaint);
                    //drawPath.moveTo(eventX, eventY);
                    return true;

                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                    drawPath.lineTo(eventX, eventY);
                    PathPoints.add(new Point(eventX, eventY));
                    drawPath.reset();
                    Wzor.reset();                       //usuniecie z pamieci poprzedniego

                    File file1;
                    FileOutputStream outputStream;
                    String FileName ="User: "+user+", Podejście: "+Licznik+" dla ścieżki nr: "+rand+".txt";

                        try {
                            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Baza danych/"+user+"/");
                            //TODO dokladna nazwa pliku
                            dir.mkdirs();
                            file1 = new File(dir, FileName);
                            System.out.println("Zapisano w: " + dir.toString());
                            outputStream = new FileOutputStream(file1);
                            System.out.println(PathPoints.size());
                            System.out.println("Zapis rozpoczety");


                            for (int i = 0; i < PathPoints.size(); i++) {
                                float FBuffX = PathPoints.get(i).getX();
                                float FBuffY = PathPoints.get(i).getY();

                                FBuffX= Math.round(FBuffX);
                                FBuffY= Math.round(FBuffY);

                                String SBuff = "Punkt nr: "+i+"  X: "+ FBuffX + ", Y: " + FBuffY + "\r\n";
                                System.out.println(SBuff);
                                outputStream.write(SBuff.getBytes());

                            }
                            System.out.println("Zapis zakonczony");
                            outputStream.close();
                            System.out.println("Plik zamkniety");
                        } catch (IOException e) {
                            System.out.println("nie dalo rady Panie");
                            e.printStackTrace();
                        }

                    PathPoints.clear();   //wyczysz


                    int poprzedni=rand;  //przypisanie obecnie wykonanego wzoru
                    do{
                        rand = r.nextInt(10);                   //losowanie nowego wzoru ze sprawdzeniem czy nie wyswietli obecnego
                    }while(rand==poprzedni);

                    if(Licznik==5) {
                                     //TODO//zliczanie ilosci wykonanych wzorow i komunikat o koncu !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        rand = 50;
                        Context contextT = getApplicationContext();
                        String message = "DZIĘKI ZA WSPÓLNĄ ZABAWĘ :)";
                        Toast toast = Toast.makeText(contextT, message,Toast.LENGTH_LONG);
                        toast.show();
                        Licznik=0;
                        flaga=1;

                        System.out.println("Prawie...");

                     //   setContentView(R.layout.activity_koniec);
                        finish();
                        Intent Koniec = new Intent(getApplicationContext(), Koniec.class);
                        Koniec.putExtra("ImieUzytkownikaKoniec",user);
                        startActivity(Koniec);
                        System.out.println("..jest");
                        /*
                        O

*/
                    }
                    Licznik++;

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
            Wzor.moveTo(100, 100); PatternPt.add(new Point(100, 100));
            Wzor.lineTo(350, 200); PatternPt.add(new Point(350, 200));
            Wzor.lineTo(150, 350); PatternPt.add(new Point(150, 350));
            Wzor.lineTo(350, 500); PatternPt.add(new Point(350, 500));
            Wzor.lineTo(150, 600); PatternPt.add(new Point(150, 600));
        }

        public void RysujWzor2() {
            Wzor.moveTo(100, 100); PatternPt.add(new Point(100, 100));
            Wzor.lineTo(200, 200); PatternPt.add(new Point(200, 200));
            Wzor.lineTo(300, 150); PatternPt.add(new Point(300, 150));
            Wzor.lineTo(350, 225); PatternPt.add(new Point(350, 225));
            Wzor.lineTo(400, 300); PatternPt.add(new Point(400, 300));
            Wzor.lineTo(350, 350); PatternPt.add(new Point(350, 350));
            Wzor.lineTo(300, 400); PatternPt.add(new Point(300, 400));
            Wzor.lineTo(240, 375); PatternPt.add(new Point(240, 375));
            Wzor.lineTo(150, 350); PatternPt.add(new Point(150, 350));
            Wzor.lineTo(100, 550); PatternPt.add(new Point(100, 550));
            Wzor.lineTo(250, 600); PatternPt.add(new Point(250, 600));
        }

        public void RysujWzor3(){
            Wzor.moveTo(250, 100); PatternPt.add(new Point(250, 100));
            Wzor.lineTo(100, 200); PatternPt.add(new Point(100, 200));
            Wzor.lineTo(50, 400);  PatternPt.add(new Point(50, 400));
            Wzor.lineTo(100, 600); PatternPt.add(new Point(100, 600));
            Wzor.lineTo(200, 650); PatternPt.add(new Point(200, 650));
            Wzor.lineTo(300, 500); PatternPt.add(new Point(300, 500));
            Wzor.lineTo(200, 400); PatternPt.add(new Point(200, 400));
        }

        public void RysujWzor4(){
            Wzor.moveTo(50, 100);  PatternPt.add(new Point(50, 100));
            Wzor.lineTo(100, 250); PatternPt.add(new Point(100, 250));
            Wzor.lineTo(150, 200); PatternPt.add(new Point(150, 200));
            Wzor.lineTo(200, 350); PatternPt.add(new Point(200, 350));
            Wzor.lineTo(250, 300); PatternPt.add(new Point(250, 300));
            Wzor.lineTo(300, 450); PatternPt.add(new Point(300, 450));
        }

        public void RysujWzor5(){
            Wzor.moveTo(175, 600); PatternPt.add(new Point(175, 600));
            Wzor.lineTo(175, 550); PatternPt.add(new Point(175, 650));
            Wzor.lineTo(25, 550);  PatternPt.add(new Point(25, 550));
            Wzor.lineTo(125, 450); PatternPt.add(new Point(125, 450));
            Wzor.lineTo(75, 450);  PatternPt.add(new Point(75, 450));
            Wzor.lineTo(175, 350); PatternPt.add(new Point(175, 350));
            Wzor.lineTo(125, 350); PatternPt.add(new Point(125, 350));
            Wzor.lineTo(225, 250); PatternPt.add(new Point(225, 250));
            Wzor.lineTo(325, 350); PatternPt.add(new Point(325, 350));
            Wzor.lineTo(275, 350); PatternPt.add(new Point(275, 350));
            Wzor.lineTo(375, 450); PatternPt.add(new Point(375, 450));
            Wzor.lineTo(325, 450); PatternPt.add(new Point(325, 450));
            Wzor.lineTo(425, 550); PatternPt.add(new Point(425, 550));
            Wzor.lineTo(275, 550); PatternPt.add(new Point(275, 550));
            Wzor.lineTo(275, 600); PatternPt.add(new Point(275, 620));
        }
        public void RysujWzor6() {
            Wzor.moveTo(150, 500); PatternPt.add(new Point(150, 500));
            Wzor.lineTo(350, 500); PatternPt.add(new Point(350, 500));
            Wzor.lineTo(150, 350); PatternPt.add(new Point(150, 350));
            Wzor.lineTo(350, 350); PatternPt.add(new Point(350, 350));
            Wzor.lineTo(250, 250); PatternPt.add(new Point(250, 250));
            Wzor.lineTo(150, 350); PatternPt.add(new Point(150, 350));
            Wzor.lineTo(150, 500); PatternPt.add(new Point(150, 500));
            Wzor.lineTo(350, 350); PatternPt.add(new Point(350, 350));
            Wzor.lineTo(350, 500); PatternPt.add(new Point(350, 500));

}
        public void RysujWzor7(){
            Wzor.moveTo(130, 500); PatternPt.add(new Point(130, 500));
            Wzor.lineTo(180, 350); PatternPt.add(new Point(180, 350));
            Wzor.lineTo(30, 300);  PatternPt.add(new Point(30, 300));
            Wzor.lineTo(180, 300); PatternPt.add(new Point(180, 300));
            Wzor.lineTo(230, 150); PatternPt.add(new Point(230, 150));
            Wzor.lineTo(280, 300); PatternPt.add(new Point(280, 300));
            Wzor.lineTo(430, 300); PatternPt.add(new Point(430, 300));
            Wzor.lineTo(280, 350); PatternPt.add(new Point(280, 350));
            Wzor.lineTo(330, 500); PatternPt.add(new Point(330, 500));
            Wzor.lineTo(230, 400); PatternPt.add(new Point(230, 400));
            Wzor.lineTo(130, 500); PatternPt.add(new Point(130, 500));
        }

        public void RysujWzor8(){
            Wzor.moveTo(100, 500); PatternPt.add(new Point(100, 500));
            Wzor.lineTo(50, 300);  PatternPt.add(new Point(50, 300));
            Wzor.lineTo(150, 450); PatternPt.add(new Point(150, 450));
            Wzor.lineTo(200, 250); PatternPt.add(new Point(200, 250));
            Wzor.lineTo(250, 450); PatternPt.add(new Point(250, 450));
            Wzor.lineTo(300, 250); PatternPt.add(new Point(300, 250));
            Wzor.lineTo(350, 450); PatternPt.add(new Point(350, 450));
            Wzor.lineTo(450, 300); PatternPt.add(new Point(450, 300));
            Wzor.lineTo(400, 500); PatternPt.add(new Point(400, 500));
            Wzor.lineTo(100, 500); PatternPt.add(new Point(100, 500));
        }
        public void RysujWzor9(){
            Wzor.moveTo(100, 700); PatternPt.add(new Point(100, 700));
            Wzor.lineTo(100, 100); PatternPt.add(new Point(100, 100));
            Wzor.lineTo(450, 100); PatternPt.add(new Point(450, 100));
            Wzor.lineTo(200, 250); PatternPt.add(new Point(200, 250));
            Wzor.lineTo(450, 400); PatternPt.add(new Point(450, 400));
            Wzor.lineTo(100, 400); PatternPt.add(new Point(100, 400));
        }
        public void RysujWzor10(){
            Wzor.moveTo(250, 700); PatternPt.add(new Point(250, 700));
            Wzor.lineTo(250, 450); PatternPt.add(new Point(250, 450));
            Wzor.lineTo(150, 400); PatternPt.add(new Point(150, 400));
            Wzor.lineTo(50, 300);  PatternPt.add(new Point(50, 300));
            Wzor.lineTo(180, 350); PatternPt.add(new Point(180, 350));
            Wzor.lineTo(250, 450); PatternPt.add(new Point(250, 450));
            Wzor.lineTo(250, 250); PatternPt.add(new Point(250, 250));
            Wzor.lineTo(200, 200); PatternPt.add(new Point(200, 200));
            Wzor.lineTo(150, 100); PatternPt.add(new Point(150, 100));
            Wzor.lineTo(200, 150); PatternPt.add(new Point(200, 150));
            Wzor.lineTo(225, 100); PatternPt.add(new Point(225, 100));
            Wzor.lineTo(250, 150); PatternPt.add(new Point(250, 150));
            Wzor.lineTo(275, 100); PatternPt.add(new Point(275, 100));
            Wzor.lineTo(300, 150); PatternPt.add(new Point(300, 150));
            Wzor.lineTo(350, 100); PatternPt.add(new Point(350, 100));
            Wzor.lineTo(300, 200); PatternPt.add(new Point(300, 200));
            Wzor.lineTo(250, 250); PatternPt.add(new Point(250, 250));
            Wzor.lineTo(250, 450); PatternPt.add(new Point(250, 450));
            Wzor.lineTo(350, 400); PatternPt.add(new Point(350, 400));
            Wzor.lineTo(450, 300); PatternPt.add(new Point(450, 300));
            Wzor.lineTo(330, 350); PatternPt.add(new Point(330, 350));
            Wzor.lineTo(250, 450); PatternPt.add(new Point(250, 450));
        }
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