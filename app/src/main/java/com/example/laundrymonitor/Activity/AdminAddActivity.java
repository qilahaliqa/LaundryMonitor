package com.example.laundrymonitor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laundrymonitor.R;

public class AdminAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button lanang = (Button)findViewById(R.id.btnLanang);
        lanang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AddMachine2.class);
                startActivity(startIntent);
            }
        });

        Button gemala = (Button)findViewById(R.id.btnGemala);
        gemala.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AddMachine.class);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }
}
