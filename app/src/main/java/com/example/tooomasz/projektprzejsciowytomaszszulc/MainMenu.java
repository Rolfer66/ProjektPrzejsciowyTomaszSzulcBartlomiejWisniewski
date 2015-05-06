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
import android.widget.TextView;


public class MainMenu extends ActionBarActivity {

    TextView tzasady, tgraj, tstatystyki,twyloguj;
    Context czasady, cgraj, cstatystyki, cwyloguj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tzasady = (TextView) findViewById(R.id.ZasadyGry);     //przejscie do zasad gry
        OnClickListener zasady = new OnClickListener(){

            @Override
            public void onClick(View v){
                czasady = getApplicationContext();
                Intent intent = new Intent(czasady, ZasadyGry.class);
                startActivity(intent);
            }
        };
        tzasady.setOnClickListener(zasady);

        twyloguj = (TextView) findViewById(R.id.textView7);     //wylogowanie i przejscie do pierwszego widoku
        OnClickListener wyloguj = new OnClickListener(){

            @Override
            public void onClick(View v){
                cwyloguj = getApplicationContext();
                Intent intent = new Intent(cwyloguj, MainActivity.class);
                startActivity(intent);
            }
        };
        twyloguj.setOnClickListener(wyloguj);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        super.onCreateOptionsMenu(menu);
        int base = Menu.FIRST;
        menu.add(base, base, base, "GRAJ!");
        menu.add(base, base+1, base+1, "TWOJE STATYSTYKI");
        menu.add(base, base+2, base+2, "ZASADY GRY");
        menu.add(base, base+3, base+3, "WYLOGUJ");

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
