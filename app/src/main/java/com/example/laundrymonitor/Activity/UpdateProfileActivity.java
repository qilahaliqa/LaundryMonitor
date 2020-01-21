package com.example.laundrymonitor.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.laundrymonitor.R;
import com.example.laundrymonitor.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileActivity extends AppCompatActivity {

    private ImageView close;
    private Button save, logout;
    private EditText profileUsername, profileEmail, profilePhone, profilePassword;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        close = findViewById(R.id.close);
        save = findViewById(R.id.update);
        logout = findViewById(R.id.action_logout);
        profileUsername = findViewById(R.id.profile_username);
        profileEmail =  findViewById(R.id.profile_email);
        profilePhone = findViewById(R.id.profile_phone);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference("User").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                profileUsername.setText(user.getUsername());
                profilePhone.setText(user.getPhone());
                profileEmail.setText(user.getEmail());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = profileUsername.getText().toString();
                String phone = profilePhone.getText().toString();
                String email = profileEmail.getText().toString();

                User user = new User(email, username, phone);

                databaseReference.setValue(user);

                finish();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });

    }
}
