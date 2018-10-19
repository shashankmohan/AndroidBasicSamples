package com.sample.shashank.servicesample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    private LocalBinderService myService;
    private boolean isServiceBound;
    private ServiceConnection mServiceConnection;

    public Intent localBinderService;
    private TextView tvRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "ID : " + Thread.currentThread().getId());

        Button btnStartService = findViewById(R.id.btn_start_service);
        Button btnStopService = findViewById(R.id.btn_stop_service);
        Button btnLocalIBinder = findViewById(R.id.btn_local_binder);
        Button btnBindService = findViewById(R.id.btn_bind_service);
        Button btnUnBindService = findViewById(R.id.btn_unbind_service);
        Button btnGetRandomNumber = findViewById(R.id.btn_random_number);
        tvRandomNumber = findViewById(R.id.tv_random_number);


        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnLocalIBinder.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        btnGetRandomNumber.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_start_service:

              /* Service run on the main UI thread,
                if we want long running operation than we need to spawn new thread in service*/
                startService(new Intent(MainActivity.this, MySimpleService.class));
                break;
            case R.id.btn_stop_service:
                stopService(new Intent(MainActivity.this, MySimpleService.class));
                break;
            case R.id.btn_local_binder:
                startService(new Intent(MainActivity.this, LocalBinderService.class));
                break;
            case R.id.btn_bind_service:
                bindService();
                localBinderService = new Intent(MainActivity.this, LocalBinderService.class);
                startService(localBinderService);
                break;
            case R.id.btn_unbind_service:
                unbindService();
                break;
            case R.id.btn_random_number:
                setRandomNumber();
                break;
        }
    }

    private void setRandomNumber() {

        if (isServiceBound) {
            tvRandomNumber.setText("Random Number :" + myService.getRandomNumber());
        } else {
            tvRandomNumber.setText("Service not found");
        }
    }

    private void unbindService() {
        if (isServiceBound) {
            unbindService(mServiceConnection);
            isServiceBound = false;
        }
    }


    private void bindService() {
        if (mServiceConnection == null) {
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    LocalBinderService.MyServiceBinder myServiceBinder = (LocalBinderService.MyServiceBinder) iBinder;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                }
            };
        }
        localBinderService = new Intent(MainActivity.this, LocalBinderService.class);
        startService(localBinderService);
        bindService(localBinderService, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
