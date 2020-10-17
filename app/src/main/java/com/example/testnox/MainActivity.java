package com.example.testnox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button chngTimeButton = findViewById(R.id.button);
        final EditText editText = findViewById(R.id.editTextNumberSigned);
        final EditText editText1 = findViewById(R.id.editTextNumber2);
        Random rnd = new Random();
        final int possibility = rnd.nextInt(101);
        chngTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(possibility <= Integer.parseInt(String.valueOf(editText.getText()))) {
                    System.out.println("kek");
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                    int delta = Integer.parseInt(String.valueOf(editText1.getText()));
                    intent.putExtra("minutes", delta);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);
                    Calendar calendar = Calendar.getInstance();
                    long nextAlarm = alarmManager.getNextAlarmClock().getTriggerTime();
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextAlarm-20*60000-delta, pendingIntent);
                }
            }
        });

    }
    public static String getDate(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}



