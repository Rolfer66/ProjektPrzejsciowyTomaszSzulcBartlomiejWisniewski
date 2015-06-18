package com.example.tooomasz.projektprzejsciowytomaszszulc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Button P1;
        final EditText WprowadzoneImie = (EditText) findViewById(R.id.ImieText);


        P1 = (Button) findViewById(R.id.buttonZagraj);

        OnClickListener c = new OnClickListener() {
            @Override

            public void onClick(View v) {
                if(TextUtils.isEmpty(WprowadzoneImie.getText())) {
                    String message = "Wprowadź najpierw swoje imię!";
                    Toast toast = Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Zygzak.class);
                    intent.putExtra("ImieUzytkownika", WprowadzoneImie.getText().toString());
                    startActivity(intent);
                }
            }
        };
        P1.setOnClickListener(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        int base = Menu.FIRST;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
