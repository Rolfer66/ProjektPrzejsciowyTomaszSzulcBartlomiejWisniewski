package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {

    Button t1,t3,t4;
    Context context,context3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (Button) findViewById(R.id.button);
        OnClickListener a = new OnClickListener(){

            @Override
            public void onClick(View v){
                context = getApplicationContext();
                Intent intent = new Intent(context, Rejestracja.class);
                startActivity(intent);
            }
        };
        t1.setOnClickListener(a);

        t3 = (Button) findViewById(R.id.buttonZaloguj);
        OnClickListener c = new OnClickListener(){

            @Override
            public void onClick(View v){
                context3 = getApplicationContext();
                Intent intent3 = new Intent(context3, MainMenu.class);
                startActivity(intent3);
            }
        };
        t3.setOnClickListener(c);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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