package com.example.laundrymonitor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laundrymonitor.R;

public class AdminListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location2);

        Button lanang = (Button)findViewById(R.id.btnLanang2);
        lanang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ListMachine2Activity.class);
                startActivity(startIntent);
            }
        });

        Button gemala = (Button)findViewById(R.id.btnGemala2);
        gemala.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ListMachineActivity.class);
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
