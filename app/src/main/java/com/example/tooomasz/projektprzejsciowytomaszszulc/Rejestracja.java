package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class Rejestracja extends ActionBarActivity {

    Button t2;
    Context context2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        t2=(Button) findViewById(R.id.UtworzKonto); //powrot po rejestracji do logowania
        OnClickListener b = new OnClickListener() {
            @Override
            public void onClick(View v) {
                context2 = getApplicationContext();
                Intent intent2 = new Intent(context2, MainActivity.class);
                startActivity(intent2);
            }
        };
        t2.setOnClickListener(b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rejestracja, menu);
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
