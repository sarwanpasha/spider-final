package com.example.blueberry.spider;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class server extends ActionBarActivity {
    Button logout;
    double LATITUDE = 33.64557150000000;
    double LONGITUDE = 72.99034469999992;
    String correctaddress="";
    String number="03239549789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        logout = (Button) findViewById(R.id.btnlogout);
        location();
        Toast.makeText(getBaseContext(), "Address = "+correctaddress, Toast.LENGTH_LONG).show();
        sendMessage(number,correctaddress);

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.package.ACTION_LOGOUT");
                registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {

                        System.out.println("onReceive Log out in progress");
                        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       startActivity(intent1);
                        finish();
                    }
                }, intentFilter);
            }
        });

    }

    protected void sendMessage(String theNum, String myMsg) {
        String sent= "Message Send";
        String Delivered= "Message Delivered";
        PendingIntent sentPI= PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
        PendingIntent deliverPI=PendingIntent.getBroadcast(this, 0, new Intent(Delivered), 0);
        registerReceiver(new BroadcastReceiver()
        {

            public void onReceive(Context arg0, Intent arg1){
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(server.this, "SMS Sent", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_LONG).show();
                        break;


                }
            }
        }, new IntentFilter(sent));

        registerReceiver(new BroadcastReceiver()
        {

            public void onReceive(Context arg0, Intent arg1){
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(server.this, "SMS Delivered", Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS Not Delivered", Toast.LENGTH_LONG).show();
                        break;


                }
            }
        }, new IntentFilter(Delivered));

        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(theNum, null, myMsg, sentPI, deliverPI);
        Toast.makeText(getBaseContext(), "Message sended ", Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server, menu);
        return true;
    }
public void location(){
  //  TextView myLatitude = (TextView)findViewById(R.id.mylatitude);
    //TextView myLongitude = (TextView)findViewById(R.id.mylongitude);
    TextView myAddress = (TextView)findViewById(R.id.tvreceived);

   // myLatitude.setText("Latitude: " + String.valueOf(LATITUDE));
    //myLongitude.setText("Longitude: " + String.valueOf(LONGITUDE));

    Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

    try {
        List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

        if(addresses != null) {
            Address returnedAddress = addresses.get(0);
            StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
            for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
            }
            myAddress.setText(strReturnedAddress.toString());
            correctaddress=(strReturnedAddress.toString());
        }
        else{
            myAddress.setText("No Address returned!");
        }
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        myAddress.setText("Canont get Address!");
    }
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
