package com.example.laundrymonitor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundrymonitor.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button activity1 = (Button)findViewById(R.id.addHome);
        activity1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AdminAddActivity.class);
                startActivity(startIntent);
            }
        });

        Button activity2 = (Button)findViewById(R.id.listHome);
        activity2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AdminListActivity.class);
                startActivity(startIntent);
            }
        });

        Button activity3 = (Button)findViewById(R.id.adminLogout);
        activity3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), AdminLoginActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
