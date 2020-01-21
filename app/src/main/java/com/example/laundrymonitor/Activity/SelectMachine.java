package com.example.laundrymonitor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laundrymonitor.Model.Machine;
import com.example.laundrymonitor.Model.User;
import com.example.laundrymonitor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectMachine extends AppCompatActivity {

    private TextView kol, mac, stat;
    private Button select, cancel;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    private String id, kolej, mach, status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_update);

        kol.findViewById(R.id.kolejSlot);
        mac.findViewById(R.id.machineSlot);
        stat.findViewById(R.id.statusSlot);
        select.findViewById(R.id.selectMachine);
        cancel.findViewById(R.id.cancelMachine);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        if (null != intent) {
            id = intent.getStringExtra("id");
            kolej = intent.getStringExtra("kolej");
            mach = intent.getStringExtra("mach");
            status = intent.getStringExtra("status");
        }

        final DatabaseReference databaseReference = firebaseDatabase.getReference("Kolej Tun Sri Gemala").child(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("kolej").setValue(kol.getText().toString());
                dataSnapshot.getRef().child("mach").setValue(mac.getText().toString());
                dataSnapshot.getRef().child("status").setValue(stat.getText().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SelectMachine.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mId = id;
                String mKolej = kol.getText().toString();
                String mMachine = mac.getText().toString();
                String mSlot = "Full";

                Machine machine = new Machine(mKolej, mMachine, mSlot, mId);

                databaseReference.setValue(machine);

                finish();
            }
        });

    }
}
