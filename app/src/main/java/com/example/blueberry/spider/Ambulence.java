package com.example.blueberry.spider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Ambulence extends ActionBarActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulence);
        db=openOrCreateDatabase("button", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS toggle(status VARCHAR);");
        ToggleButton toggle = (ToggleButton) findViewById(R.id.togglebutton);
        db.execSQL("INSERT INTO toggle VALUES('"+"of"+"');");

    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            // Enable vibrate
            Toast.makeText(getBaseContext(), "Button on", Toast.LENGTH_SHORT).show();
           // db.execSQL("INSERT INTO toggle VALUES('"+"on"+"');");
            db.execSQL("UPDATE toggle SET status='"+"on"+
                    "' WHERE status='"+"of"+"'");
          Cursor onn=db.rawQuery("SELECT status FROM toggle WHERE status='" + "on" + "'", null);
         //  String onnn=onn.toString();
         Toast.makeText(getBaseContext(), "Button = "+onn, Toast.LENGTH_SHORT).show();

        } else {
            // Disable vibrate
            Toast.makeText(getBaseContext(), "Button off", Toast.LENGTH_SHORT).show();
          //  db.execSQL("INSERT INTO toggle VALUES('"+"of"+"');");
            db.execSQL("UPDATE toggle SET status='"+"of"+
                    "' WHERE status='"+"on"+"'");
           Cursor off=db.rawQuery("SELECT status FROM toggle WHERE status='" + "of" + "'", null);
           // String offf=off.toString();
            Toast.makeText(getBaseContext(), "Button = "+off, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ambulence, menu);
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
