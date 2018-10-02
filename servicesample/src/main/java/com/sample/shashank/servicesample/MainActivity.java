package com.sample.shashank.servicesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "ID : " + Thread.currentThread().getId());

        Button btnStartService = findViewById(R.id.btn_start_service);
        Button btnStopService = findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);

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
                break;
        }
    }
}
