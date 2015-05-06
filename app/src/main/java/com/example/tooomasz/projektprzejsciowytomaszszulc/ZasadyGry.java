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


public class ZasadyGry extends ActionBarActivity {

    Button bpowrot;
    Context cpowrot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zasady_gry);

        bpowrot = (Button) findViewById(R.id.ZasadyPowrot);     //przejscie menu z zasad gry
        OnClickListener powrot = new OnClickListener(){

            @Override
            public void onClick(View v){
                cpowrot = getApplicationContext();
                Intent intent = new Intent(cpowrot, MainMenu.class);
                startActivity(intent);
            }
        };
        bpowrot.setOnClickListener(powrot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zasady_gry, menu);
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
