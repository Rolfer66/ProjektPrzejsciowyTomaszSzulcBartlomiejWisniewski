package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.view.MenuItem;

public class MainMenu extends ActionBarActivity {



    Context context1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Button P1;
        final EditText WprowadzoneImie = (EditText) findViewById(R.id.ImieText);


        P1= (Button)findViewById(R.id.buttonZagraj);
        OnClickListener c = new OnClickListener(){


            @Override
            public void onClick(View v){
                context1 = getApplicationContext();
                Intent intent = new Intent(context1,Zygzak.class);
                intent.putExtra("ImieUzytkownika", WprowadzoneImie.getText().toString());
                startActivity(intent);
            }
        };
        P1.setOnClickListener(c);




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
