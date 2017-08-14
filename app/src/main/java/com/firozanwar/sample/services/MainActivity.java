package com.firozanwar.sample.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firozanwar.sample.services.boundservice.MyBoundService;
import com.firozanwar.sample.services.boundservice.MyBoundService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    TextView date;
    Button btn;
    MyBoundService myBoundService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (TextView) findViewById(R.id.date);
        btn = (Button) findViewById(R.id.btn);

        Intent i = new Intent(this, MyBoundService.class);
        bindService(i, connection, Context.BIND_AUTO_CREATE);
    }

    public void getTime(View v) {
        date.setText(myBoundService.getCurrentTime());
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder myLocalBinder = (MyLocalBinder) service;
            myBoundService = myLocalBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
