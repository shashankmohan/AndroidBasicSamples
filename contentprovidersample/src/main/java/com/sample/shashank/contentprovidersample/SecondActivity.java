package com.sample.shashank.contentprovidersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    private String TAG = SecondActivity.this.getLocalClassName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.e(TAG,"onCreate");

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart");
    }
}
