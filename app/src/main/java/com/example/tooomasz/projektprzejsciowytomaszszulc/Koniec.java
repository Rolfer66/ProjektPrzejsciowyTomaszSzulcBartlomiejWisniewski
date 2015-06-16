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


public class Koniec extends ActionBarActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koniec);

        Button koniec, again, nowy;

        Intent t = getIntent();
        user = t.getStringExtra("ImieUzytkownikaKoniec");                   //odebranie imienia

        koniec = (Button) findViewById(R.id.Koniec);                              //przycisk konczacy aplikacje
        OnClickListener klik = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        };
        koniec.setOnClickListener(klik);



        again = (Button) findViewById(R.id.Again);
        OnClickListener JeszczeRaz = new OnClickListener() {               //przycisk do ponownego zagrania
            @Override
            public void onClick(View v) {
                finish();


                Intent intent = new Intent(getApplicationContext(), Zygzak.class);
                intent.putExtra("ImieUzytkownika", user);
                startActivity(intent);

            }
        };
        again.setOnClickListener(JeszczeRaz);



        nowy = (Button) findViewById(R.id.NewUser);                                       //przycisk nowego uzytkownika
        OnClickListener Nowy = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent Koniec = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(Koniec);

            }
        };
        nowy.setOnClickListener(Nowy);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_koniec, menu);
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
