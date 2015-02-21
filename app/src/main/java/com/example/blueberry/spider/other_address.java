package com.example.blueberry.spider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class other_address extends ActionBarActivity {
    Button send;
    Button back;
    EditText cityyy;
    EditText areaaa;
    EditText street_numberrr;
    EditText house_numberrr;
    String num="03239549789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_address);


        send = (Button) findViewById(R.id.btnSend);
        back = (Button) findViewById(R.id.btnBack);
        cityyy = (EditText) findViewById(R.id.addressCity);
        areaaa = (EditText) findViewById(R.id.addressArea);
        street_numberrr = (EditText) findViewById(R.id.addressStreet);
        house_numberrr = (EditText) findViewById(R.id.addressHouse);

        String city = cityyy.getText().toString();
        String area = areaaa.getText().toString();
        String street_number  = street_numberrr.getText().toString();
        String house_number  = house_numberrr.getText().toString();


      //  int i=0;
        //while(i<4)
        //{

           // i++;
       // }
       // showMessage("Details", buffer.toString());
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "City = "+cityyy.getText().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "area = "+areaaa.getText().toString(), Toast.LENGTH_SHORT).show();
                StringBuffer buffer=new StringBuffer();
                buffer.append("City: "+cityyy.getText().toString()+"\n");
                buffer.append("Area: "+areaaa.getText().toString()+"\n");
                buffer.append("Street: "+street_numberrr.getText().toString()+"\n");
                buffer.append("House: "+house_numberrr.getText().toString()+"\n\n");
                String sendd = send.getText().toString();
            //    SendSMS();
                sendMessage(num,buffer.toString());
                showMessage("Details", buffer.toString());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Emergency1.class);
                startActivity(i);
                finish();

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
                        Toast.makeText(other_address.this, "SMS Sent", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(other_address.this, "SMS Delivered", Toast.LENGTH_LONG).show();
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
    public void SendSMS() {
        String phonenumber="03239549789";
        String cityy = cityyy.getText().toString();
        String areaa = areaaa.getText().toString();
        String street_numberr = street_numberrr.getText().toString();
        String house_numberr = house_numberrr.getText().toString();
        try {
            SmsManager.getDefault().sendTextMessage(String.valueOf(phonenumber),
                    null, "City = "+cityy + "AREA =  "+areaa + "Steet Number = "+street_numberr+"House Number = "+house_numberr
                            + " ", null, null);
        } catch (Exception ex) {
            Log.d("SMS Error: ", ex.getMessage().toString());
        }
        Toast.makeText(getBaseContext(), "Your Detail has been sended", Toast.LENGTH_SHORT).show();


    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other_address, menu);
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
