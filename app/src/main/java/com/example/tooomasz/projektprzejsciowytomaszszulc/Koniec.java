package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
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
