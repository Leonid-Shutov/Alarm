package com.example.testnox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int delta = intent.getIntExtra("minutes", 0);
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis()+delta*60000);
        String date = format.format(calendar.getTime());
        System.out.println(date);
        System.out.println(calendar.getTime());
        changeSystemTime(date);
    }
    private void changeSystemTime(String newDate){ //mmddHHmmYYYY.ss
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("date " + newDate + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
