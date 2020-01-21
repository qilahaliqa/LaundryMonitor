package com.example.laundrymonitor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laundrymonitor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.adminText);
        password = findViewById(R.id.adminText2);
        btnSignIn = findViewById(R.id.buttonAdminLogin);

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@androidx.annotation.NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(AdminLoginActivity.this,"Logged In", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(AdminLoginActivity.this,"Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                final String pwd = password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Field are empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@androidx.annotation.NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(AdminLoginActivity.this, "Login Error, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intMain = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                                startActivity(intMain);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(AdminLoginActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        startActivity(new Intent(AdminLoginActivity.this, MainActivity.class));
        finish();
    }
}
