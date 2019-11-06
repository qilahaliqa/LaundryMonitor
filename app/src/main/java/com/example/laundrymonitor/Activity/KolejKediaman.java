package com.example.laundrymonitor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundrymonitor.R;

public class KolejKediaman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolej_kediaman);

        Button activityLogin = (Button)findViewById(R.id.TunLanang);
        activityLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Machine2Activity.class);
                startActivity(startIntent);
            }
        });

        Button activitySignUp = (Button)findViewById(R.id.TunGemala);
        activitySignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MachineActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
