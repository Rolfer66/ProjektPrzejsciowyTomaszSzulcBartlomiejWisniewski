package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.os.Environment;
import android.view.MenuItem;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Zygzak extends ActionBarActivity {

    Path Wzor = new Path();
    Path drawPath = new Path();
    List<Point> PathPoints = new ArrayList<Point>();
    Tory Tor1 = new Tory();
    int rand=0;
    int Licznik=1, LicznikProb=1;    // zmienna do liczenia wystąpień rysowanych wzorów


    String user;

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

            //paaint okregow danego punkut
            drawCanvasPaint.setColor(Color.WHITE);
            drawCanvasPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            drawCanvasPaint.setStrokeWidth(1f);
            drawCanvasPaint.setAntiAlias(true);


            // pain okregow
            NineCirclesPaint.setColor(Color.WHITE);
            NineCirclesPaint.setStyle(Paint.Style.STROKE);
            NineCirclesPaint.setAntiAlias(true);
            NineCirclesPaint.setStrokeWidth(2f);

            //paint wzoru
            WzorPaint.setStyle(Paint.Style.STROKE);
            WzorPaint.setStrokeWidth(11f);
            WzorPaint.setColor(Color.YELLOW);
            WzorPaint.setStrokeJoin(Paint.Join.ROUND);
            WzorPaint.setAntiAlias(true);

            //paint sciezki uzytkownika
            drawPaint.setStyle(Paint.Style.STROKE);
            drawPaint.setStrokeWidth(11f);
            drawPaint.setColor(Color.RED);
            drawPaint.setStrokeJoin(Paint.Join.ROUND);
            drawPaint.setStrokeCap(Paint.Cap.ROUND);

        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawRGB(80, 80, 80);

            //Wybieranie wzoru z bazy
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

            canvas.drawPath(Wzor, WzorPaint);  //rysowanie wzoru
            canvas.drawPath(drawPath, drawPaint);


            // Rysowanie 9 punktów
            canvas.drawCircle(90, 200, 9, NinePointsPaint);
            canvas.drawCircle(240, 200, 9, NinePointsPaint);
            canvas.drawCircle(390, 200, 9, NinePointsPaint);

            canvas.drawCircle(90, 400, 9, NinePointsPaint);
            canvas.drawCircle(240,400,9,NinePointsPaint);
            canvas.drawCircle(390,400,9,NinePointsPaint);

            canvas.drawCircle(90, 600, 9, NinePointsPaint);
            canvas.drawCircle(240, 600, 9, NinePointsPaint);
            canvas.drawCircle(390, 600, 9, NinePointsPaint);

            //okregi dookola punktow
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
        }

        //@Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();


            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:   // Wykryto  nowy dotyk
                    int Psize = PathPoints.size();
                    drawPath.moveTo(eventX, eventY);
                    while(PathPoints.get(Psize).getX() != eventX  && PathPoints.get(Psize).getY() != eventY) {
                        PathPoints.add(new Point(eventX, eventY));
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:   // Przesunięcie palca
                    int Psize1 = PathPoints.size();
                    drawPath.lineTo(eventX, eventY);
                    while(PathPoints.get(Psize1).getX() != eventX  && PathPoints.get(Psize1).getY() != eventY) {
                        PathPoints.add(new Point(eventX, eventY));
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP: // Palec zabrany z ekranu
                    int Psize3 = PathPoints.size();
                    while(PathPoints.get(Psize3).getX() != eventX  && PathPoints.get(Psize3).getY() != eventY) {
                        PathPoints.add(new Point(eventX, eventY));
                    }
                    drawPath.reset();
                    Wzor.reset();                       //usuniecie z pamieci poprzedniego

                    SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd (Z)");
                    System.out.println("dzisiaj mamy: " + simpleDateHere.format(new Date()).toString());

                    File file1;
                    FileOutputStream outputStream;
                    String FileName ="ścieżka nr: "+rand+".txt";

                        try {
                            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Baza danych/"+user+"/"+simpleDateHere.format(new Date()).toString()+"/"+"Proba: "+LicznikProb);
                            //TODO dokladna nazwa pliku
                            dir.mkdirs();
                            file1 = new File(dir, FileName);
                            outputStream = new FileOutputStream(file1);
                            System.out.println(PathPoints.size());
                            System.out.println("Zapis rozpoczety");

                            for (int i = 0; i < PathPoints.size(); i++) {
                                float FBuffX = PathPoints.get(i).getX();
                                float FBuffY = PathPoints.get(i).getY();
                                FBuffX= Math.round(FBuffX);                   //zaokraglenie liczb
                                FBuffY= Math.round(FBuffY);
                                String SBuff = "Punkt nr: "+i+"  X: "+ FBuffX + ", Y: " + FBuffY + "\r\n"; //zapis do pliku
                                System.out.println(SBuff);
                                outputStream.write(SBuff.getBytes());
                            }
                            System.out.println("Zapis zakonczony");
                            outputStream.close();
                            System.out.println("Plik zamkniety");
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    PathPoints.clear();   //wyczysc


                    rand++;

                    if(Licznik==10) {
                                     //TODO//zliczanie ilosci wykonanych wzorow i komunikat o koncu !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        rand = 0;

                        if(LicznikProb==5) {
                            Context contextT = getApplicationContext();
                            String message = "DZIĘKI ZA WSPÓLNĄ ZABAWĘ :)";
                            Toast toast = Toast.makeText(contextT, message, Toast.LENGTH_SHORT);
                            toast.show();
                            Licznik = 0;

                            finish();
                            Intent Koniec = new Intent(getApplicationContext(), Koniec.class);
                            Koniec.putExtra("ImieUzytkownikaKoniec", user);
                            startActivity(Koniec);
                            System.out.println("..jest");
                            LicznikProb=0;
                        }
                        LicznikProb++;
                        Licznik=0;
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