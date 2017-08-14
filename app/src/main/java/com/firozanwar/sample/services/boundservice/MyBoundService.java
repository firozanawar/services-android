package com.firozanwar.sample.services.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBoundService extends Service {

    private final IBinder myBinder = new MyLocalBinder();

    public class MyLocalBinder extends Binder {

        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return df.format(new Date());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyBoundService", "Bound service destroyed");
    }
}
