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
import android.graphics.Region;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
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
    Region region = new Region();


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

        Paint drawPaint, WzorPaint, NinePointsPaint, NineCirclesPaint, drawCanvasPaint;
        Canvas drawCanvas;
        Path CirclePath;


        public CanvasView(Context context) {
            super(context);
            setupDrawing();
        }

        void setupDrawing()
        {
            WzorPaint = new Paint();
            drawPaint = new Paint();
            drawCanvas = new Canvas();
            NinePointsPaint = new Paint();
            drawCanvasPaint = new Paint();
            CirclePath = new Path();            //wyswietlani najechania nie dziala panie
            NineCirclesPaint = new Paint();



            //tu ustawiamy paint do kolka wwyswietlanego po wejsciu do regionu
            drawCanvasPaint.setColor(Color.WHITE);
            drawCanvasPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            drawCanvasPaint.setStrokeWidth(1f);
            drawCanvasPaint.setAntiAlias(true);



            NineCirclesPaint.setColor(Color.WHITE);
            NineCirclesPaint.setStyle(Paint.Style.STROKE);
            NineCirclesPaint.setAntiAlias(true);
            NineCirclesPaint.setStrokeWidth(2f);


            WzorPaint.setStyle(Paint.Style.STROKE);
            WzorPaint.setStrokeWidth(11f);
            WzorPaint.setColor(Color.YELLOW);
            WzorPaint.setStrokeJoin(Paint.Join.ROUND);
            //WzorPaint.setStrokeCap(Paint.Cap.ROUND);
            //WzorPaint.setPathEffect(new CornerPathEffect(15));
            WzorPaint.setAntiAlias(true);
            drawPaint.setStyle(Paint.Style.STROKE);
            drawPaint.setStrokeWidth(11f);
            drawPaint.setColor(Color.RED);
            drawPaint.setStrokeJoin(Paint.Join.ROUND);
            drawPaint.setStrokeCap(Paint.Cap.ROUND);

            NinePointsPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            NinePointsPaint.setColor(Color.WHITE);

        }

        @Override
        public void onDraw(Canvas canvas) {


            canvas.drawRGB(80, 80, 80);


            Tor1.RysujWzor3();



            /*
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
            */

            //Tor1.RysujWzor2(); //Wyrysowanie wzoru metoda z klasy Wzory.


            canvas.drawPath(Wzor, WzorPaint);  //rysowanie wzoru
            canvas.drawPath(drawPath, drawPaint);


            /** Rysowanie 9 punktów i kółek ******/
            canvas.drawCircle(90, 200, 6, NinePointsPaint);
            canvas.drawCircle(240, 200, 6, NinePointsPaint);
            canvas.drawCircle(390, 200, 6, NinePointsPaint);

            canvas.drawCircle(90, 400, 6, NinePointsPaint);
            canvas.drawCircle(240,400,6,NinePointsPaint);
            canvas.drawCircle(390,400,6,NinePointsPaint);

            canvas.drawCircle(90, 600, 6, NinePointsPaint);
            canvas.drawCircle(240, 600, 6, NinePointsPaint);
            canvas.drawCircle(390, 600, 6, NinePointsPaint);

            //kółka
            canvas.drawCircle(90, 200, 20, NineCirclesPaint);
            canvas.drawCircle(240, 200, 20, NineCirclesPaint);
            canvas.drawCircle(390, 200, 20, NineCirclesPaint);

            canvas.drawCircle(90, 400, 20, NineCirclesPaint);
            canvas.drawCircle(240,400,20, NineCirclesPaint);
            canvas.drawCircle(390,400,20, NineCirclesPaint);

            canvas.drawCircle(90, 600, 20, NineCirclesPaint);
            canvas.drawCircle(240,600,20, NineCirclesPaint);
            canvas.drawCircle(390,600,20, NineCirclesPaint);


            canvas.drawPath(CirclePath,drawCanvasPaint);

            //region.set(40,150,100,210); ustawianie regionu - prostokąt


        }

        //@Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:   // Wykryto  nowy dotyk

                    //drawPath.moveTo(eventX, eventY);
                    PathPoints.add(new Point(eventX,eventY));
                    /********************************* Jezeli w regionie znajduje sie palec to ma rysowac koleczko
                    if(region.contains((int)eventX,(int)eventY)){
                        Log.d("Region: ", "Touch IN");
                        //drawCanvas.drawCircle(90, 200, 20, drawCanvasPaint);
                        CirclePath.addCircle(90,200,70,Path.Direction.CW);
                    }
                    else{
                        CirclePath.reset();
                    }
                     ***************************************/
                    invalidate();
                    break;


                case MotionEvent.ACTION_MOVE:   // Przesunięcie palca

                    //drawPath.lineTo(eventX, eventY);
                    PathPoints.add(new Point(eventX, eventY));
                    //drawCanvas.drawPath(drawPath, drawPaint);
                    //drawPath.moveTo(eventX, eventY);

                    /********************************* Jezeli w regionie znajduje sie palec to ma rysowac koleczko
                    if(region.contains((int)eventX,(int)eventY)){
                        Log.d("Region: ", "Touch IN");
                        //drawCanvas.drawCircle(90, 200, 20, drawCanvasPaint);
                        CirclePath.addCircle(90, 200, 20, Path.Direction.CW);
                    }
                    else{
                        CirclePath.reset();
                     }
                     *******************************/
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                    //drawPath.lineTo(eventX, eventY);
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
                        Toast toast = Toast.makeText(contextT, message,Toast.LENGTH_SHORT);
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
                    invalidate();

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
        public void RysujPunkty(){

        }

        public void RysujWzor1() {
            Wzor.moveTo(90, 200);
            Wzor.lineTo(390, 200);
            Wzor.lineTo(240, 400);
            Wzor.lineTo(90, 600);
            Wzor.lineTo(390, 600);
            Wzor.lineTo(90, 200);
        }

        public void RysujWzor2() {
            Wzor.moveTo(240, 200);
            Wzor.lineTo(90, 400);
            Wzor.lineTo(240, 600);
            Wzor.lineTo(390, 400);
            Wzor.lineTo(240, 200);
            Wzor.lineTo(240, 400);
            Wzor.lineTo(90, 600);
            Wzor.lineTo(390, 600);
            Wzor.lineTo(240, 400);



        }

        public void RysujWzor3(){
            Wzor.moveTo(90, 200);
            Wzor.lineTo(90, 400);
            Wzor.lineTo(240, 400);
            Wzor.lineTo(240, 600);
            Wzor.lineTo(390, 600);
            Wzor.lineTo(390, 400);
            Wzor.lineTo(240, 400);
            Wzor.lineTo(240, 200);
            Wzor.lineTo(90, 200);
            Wzor.lineTo(390, 600);
        }

        public void RysujWzor4(){
            Wzor.moveTo(90, 200);
            Wzor.lineTo(390, 200);
            Wzor.lineTo(90, 600);
            Wzor.lineTo(390, 600);
        }

        public void RysujWzor5(){
            Wzor.moveTo(90, 200);
            Wzor.lineTo(240, 200);
            Wzor.lineTo(240, 400);
            Wzor.lineTo(390, 400);
            Wzor.lineTo(390, 600);


        }
        public void RysujWzor6() {
            Wzor.moveTo(90, 200);
            Wzor.lineTo(90, 600);
            Wzor.lineTo(390, 200);
            Wzor.lineTo(390, 600);
        }


        public void RysujWzor7() {
            Wzor.moveTo(240, 200);
            Wzor.lineTo(390, 400);
            Wzor.lineTo(240, 600);
            Wzor.lineTo(90, 400);
            Wzor.lineTo(240, 200);
        }


        public void RysujWzor8(){
            Wzor.moveTo(90, 400);
            Wzor.lineTo(390, 200);
            Wzor.lineTo(390, 600);
            Wzor.moveTo(90, 400);
        }
        public void RysujWzor9(){
            Wzor.moveTo(90, 200);
            Wzor.lineTo(390, 200);
            Wzor.lineTo(390, 600);
            Wzor.lineTo(90, 600);
            Wzor.lineTo(90, 200);

        }
        public void RysujWzor10(){
            Wzor.moveTo(90, 600);
            Wzor.lineTo(90, 400);
            Wzor.lineTo(240, 200);
            Wzor.lineTo(390, 400);
            Wzor.lineTo(390, 600);



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