package com.sample.shashank.servicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class LocalBinderService extends Service {

    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    private String TAG = LocalBinderService.class.getSimpleName();

    class MyServiceBinder extends Binder {
        public LocalBinderService getService() {
            return LocalBinderService.this;
        }
    }

    private IBinder mIBinder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "In onBind");
        return mIBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "In onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "In Service onStartCommand" + Thread.currentThread().getId());
        mIsRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumber();
            }
        }).start();
        return START_STICKY;
    }

    private void startRandomNumber() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(3000);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(TAG, "In Service startRandomNumber" + Thread.currentThread().getId() + " " + mRandomNumber);
                }
            } catch (InterruptedException e) {
                Log.i(TAG, " Inside startRandomMethod" + "Thread Interrupted");
                e.printStackTrace();
            }
        }
    }


    private void stopRandomNumberGenerator() {
        mIsRandomGeneratorOn = false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public int getRandomNumber() {
        return mRandomNumber;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
    }
}
