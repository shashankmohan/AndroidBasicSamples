package com.sample.shashank.callbacksample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSelect = findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionsDialog optionsDialog = new OptionsDialog();
                optionsDialog.show(getSupportFragmentManager(), OptionsDialog.class.getSimpleName());

                optionsDialog.SetOnSelectListener(new OnSelectListener() {
                    @Override
                    public void onEdit() {
                        Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDelete() {
                        Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
