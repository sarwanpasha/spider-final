package com.example.blueberry.spider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Emergency1 extends ActionBarActivity {
    Button btnCurrent;
    Button btnDefault;
    Button btnOther;
    Button back;
    Double lat;
    Double lon;
    SQLiteDatabase db;
    //  String username;
    IntentFilter intentFilter;
    private Context mContext;

    private BroadcastReceiver intentReceiver=new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            TextView inTxt=(TextView) findViewById(R.id.tvreceived);
            inTxt.setText(intent.getExtras().getString("sms"));



        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency1);
        db=openOrCreateDatabase("Spider", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS pasha(name VARCHAR,email VARCHAR,password VARCHAR,city VARCHAR,area VARCHAR,street VARCHAR,house VARCHAR,cnic VARCHAR);");
        intentFilter=new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
     //   db=openOrCreateDatabase("Spider", Context.MODE_PRIVATE, null);
        //final GPSTracker gpsTracker=new GPSTracker(this);
        final GPSAddress gpsAddress=new GPSAddress();
        btnCurrent = (Button) findViewById(R.id.btncurrent);
        btnDefault = (Button) findViewById(R.id.btndefault);
        btnOther = (Button) findViewById(R.id.btnother);
        back = (Button) findViewById(R.id.btnback1);

        final String[] address = new String[1];
        btnCurrent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usernamee="Current button";
                String number="03239549789";
                // sendMessage(number,usernamee);
                notification();
                //String address= GetAddress("34.0167","71.5833");
                // Toast.makeText(getBaseContext(), address, Toast.LENGTH_LONG).show();
                //Location loc = gpsTracker.getLocation();
                //  lon = loc.getLongitude();
                // lat = loc.getLatitude();
                // lat= gpsTracker.getLatitude();
//  lon= gpsTracker.getLongitude();
                // GPSAddress obj = new GPSAddress(obj.getCompleteAddressString(lat,lon));
                // address[0] =  gpsAddress.getCompleteAddressString(lat,lon);
                //  String lonn = String.valueOf(lon);
                //String latt = String.valueOf(lat);
                //Toast.makeText(getBaseContext(),address[0] , Toast.LENGTH_LONG).show();
                // Toast.makeText(getBaseContext(), latt, Toast.LENGTH_LONG).show();
                //Toast.makeText(getBaseContext(), lonn, Toast.LENGTH_LONG).show();

            }
        });

        btnDefault.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username="irfan";
                String number="03239549789";
              //  Cursor defaultt = db.rawQuery("SELECT * FROM pasha WHERE email='" + username + "'", null);
           Cursor defaultnamee=db.rawQuery("SELECT name FROM pasha WHERE email='" + "irfan" + "'", null);
//                Cursor defaultemaill=db.rawQuery("SELECT email FROM pasha WHERE email='" + username + "'", null);
//                Cursor defaultpasswordd=db.rawQuery("SELECT password FROM pasha WHERE email='" + username + "'", null);
//                Cursor defaultcityy=db.rawQuery("SELECT city FROM pasha WHERE email='" + username + "'", null);
//                Cursor defaultareaa=db.rawQuery("SELECT area FROM pasha WHERE email='" + username + "'", null);
//                Cursor defaultstreett=db.rawQuery("SELECT street FROM pasha WHERE email='" + username + "'", null);
//                Cursor defaulthousee=db.rawQuery("SELECT house FROM pasha WHERE email='" + username + "'", null);
//                Cursor defaultcnicc=db.rawQuery("SELECT cnic FROM pasha WHERE email='" + username + "'", null);

               // Cursor defaultt=db.rawQuery("SELECT * FROM pasha WHERE email='"+username+"'", null);

//                String defaultname=defaultnamee.toString();
//                String defaultemail=defaultemaill.toString();
//                String defaultpassword=defaultpasswordd.toString();
//                String defaultcity=defaultcityy.toString();
//                String defaultarea=defaultareaa.toString();
//                String defaultstreet=defaultstreett.toString();
//                String defaulthouse=defaulthousee.toString();
//                String defaultcnic=defaultcnicc.toString();

                if(defaultnamee.getCount()==0)
                {
                    Toast.makeText(Emergency1.this, "Error", Toast.LENGTH_LONG).show();
                    //showMessage("Error", "No records found");
                    return;
                }

            StringBuffer buffer=new StringBuffer();
//                buffer.append("Name: "+defaultnamee.toString()+"\n");
//                buffer.append("Email: "+defaultemaill.toString()+"\n");
//                buffer.append("Password: "+defaultpasswordd.toString()+"\n");
//                buffer.append("City: "+defaultcityy.toString()+"\n\n");

                buffer.append("Name: "+defaultnamee.toString()+"\n");
//                buffer.append("Email: "+defaultt.getString(1)+"\n");
//                buffer.append("Password: "+defaultt.getString(2)+"\n");
//                buffer.append("City: "+defaultt.getString(3)+"\n");
//                buffer.append("Area: "+defaultt.getString(4)+"\n");
//                buffer.append("Street: "+defaultt.getString(5)+"\n");
//                buffer.append("house: "+defaultt.getString(6)+"\n");
//                buffer.append("cnic: "+defaultt.getString(7)+"\n\n");

             // showMessage("Details", buffer.toString());

             Toast.makeText(Emergency1.this, "username = " + buffer.toString(), Toast.LENGTH_LONG).show();
                //sendDefaulMessage(defaultname,defaultemail,defaultpassword,defaultcity,defaultarea,defaultstreet,defaulthouse,defaultcnic);
              //  sendMessage(number,buffer.toString());
             Toast.makeText(Emergency1.this, "Success", Toast.LENGTH_LONG).show();

            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),other_address.class);
                startActivity(i);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dashboard.class);
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
                        Toast.makeText(Emergency1.this, "SMS Sent", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(Emergency1.this, "SMS Delivered", Toast.LENGTH_LONG).show();
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
    private void notification() {
        //  mp.setLooping(true);
        //mp.start();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Emergeny Message"); // Set Alert dialog title
        // here
        alert.setMessage("Is everything OK?");

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                SendSMS();
                // Toast.makeText(getBaseContext(), "Your current location has been send", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
    //    public void sendDefaulMessage(Cursor name,Cursor email,Cursor password,Cursor city,Cursor area,Cursor street,Cursor house,Cursor cnic)
//    {
//
//
//            String pinpoint = "http://www.maps.google.com/maps?q=" + lat + ","
//                    + lon;
//            String phonenumber="03239549789";
//            String messages="I love you";
//        try {
//            SmsManager.getDefault().sendTextMessage(String.valueOf(phonenumber),
//                    null, "City = "+name + "AREA =  "+area + "Steet Number = "+street+"House Number = "+house
//                            + " ", null, null);
//        } catch (Exception ex) {
//            Log.d("SMS Error: ", ex.getMessage().toString());
//        }
//        Cursor c=db.rawQuery("SELECT * FROM student WHERE email='" + username + "'", null);
//        if(c.getCount()==0){
//            Toast.makeText(getBaseContext(), "Username = "+ username, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getBaseContext(), "No record found", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            StringBuffer buffer = new StringBuffer();
//            while (c.moveToNext()) {
//                buffer.append("name: " + c.getString(0) + "\n");
//                buffer.append("email: " + c.getString(1) + "\n");
//                buffer.append("password: " + c.getString(2) + "\n\n");
//            }
//            showMessage("Student Details", buffer.toString());
//        }
//    }
    public void SendSMS() {

        try {
            GPSTracker gps = new GPSTracker(mContext);
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                String   lat = String.valueOf(latitude);
                String lon = String.valueOf(longitude);
                // Toast.makeText(getBaseContext(), "Zero by Zero  ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getBaseContext(), "Can't get location  ", Toast.LENGTH_SHORT).show();
            }
            String pinpoint = "http://www.maps.google.com/maps?q=" + lat + ","
                    + lon;
            // String address = GetAddress(lat, lon);
            String phonenumber="03239549789";
            String messages=" ";
            //  DataInsertion obj = new DataInsertion();
            //  String[] phonenumber = obj.getphonenumbers(getApplicationContext());
            // String[] messages = obj.getmessages(getApplicationContext());
            // for (int i = 0; i <= phonenumber.length; i++)
            SmsManager.getDefault().sendTextMessage(String.valueOf(phonenumber),
                    null, messages + "I am at: " + " " + pinpoint, null, null);
        } catch (Exception ex) {
            Log.d("SMS Error: ", ex.getMessage().toString());
        }
        Toast.makeText(getBaseContext(), "Lattitude = "+lat, Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), "Lognitude = "+lon, Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), "Your Detail has been sended", Toast.LENGTH_SHORT).show();


    }
    public void GetAddress1(String lat, String lon)throws IOException {

    }
    public String GetAddress(String lat, String lon) {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String ret = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(
                    Double.parseDouble(lat), Double.parseDouble(lon), 1);
            if (addresses != null)
            {
                Toast.makeText(getBaseContext(), "Getting the Address", Toast.LENGTH_SHORT).show();
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                ret = strReturnedAddress.toString();
                // Log.w("My Current loction address", "" + strReturnedAddress.toString());
                Toast.makeText(getBaseContext(), "My Current loction address = "+strReturnedAddress.toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Null Address", Toast.LENGTH_SHORT).show();
                ret = null;
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = null;
        }
        Toast.makeText(getBaseContext(), "Address = "+ret, Toast.LENGTH_SHORT).show();
        return ret;
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
        getMenuInflater().inflate(R.menu.menu_emergency1, menu);
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
