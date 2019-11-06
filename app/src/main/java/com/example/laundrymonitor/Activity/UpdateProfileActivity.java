package com.example.laundrymonitor.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundrymonitor.R;
import com.example.laundrymonitor.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileActivity extends AppCompatActivity {

    private ImageView close, profileImage;
    private TextView changeProfile;
    private Button save;
    private TextView profileUsername, profileEmail, profilePhone;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        close = findViewById(R.id.close);
        profileImage = findViewById(R.id.image_profile);
        changeProfile = findViewById(R.id.change_profile);
        save = findViewById(R.id.update);
        profileUsername = findViewById(R.id.profile_username);
        profileEmail =  findViewById(R.id.profile_email);
        profilePhone = findViewById(R.id.profile_phone);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

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

                User user = new User(username, phone, email);

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


    }
}
