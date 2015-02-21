package com.example.blueberry.spider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

/**
 * Created by Blue Berry on 2/21/2015.
 */
public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Bundle bundle= intent.getExtras();
        SmsMessage[] messages=null;
        String str="";
        if(bundle!=null){
            Object[] pdus=(Object[]) bundle.get("pdus");
            messages=new SmsMessage[pdus.length];
            for(int i=0; i<messages.length;i++){
                messages [i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                str +="Message from" + messages[i].getOriginatingAddress();
                str +=":";
                str +=messages[i].getMessageBody().toString();
                str +="\n";

            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

            Intent broadcastIntent= new Intent();
            broadcastIntent.setAction("SMS Received Action");
            broadcastIntent.putExtra("sms", str);
            context.sendBroadcast(broadcastIntent);


        }

    }

}

