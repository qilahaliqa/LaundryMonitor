package com.example.laundrymonitor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundrymonitor.Model.Machine2;
import com.example.laundrymonitor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMachine2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Spinner spin1, spin2, spin3;
    Button addMachine2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine2);

        firebaseAuth = FirebaseAuth.getInstance();
        spin1 = findViewById(R.id.kolejType);
        spin2 = findViewById(R.id.machineType);
        spin3 = findViewById(R.id.slotType);
        addMachine2 = findViewById(R.id.addMachine2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Kolej Tun Sri Lanang");

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.lanang_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.machine_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.slot_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adapter1);
        spin2.setAdapter(adapter2);
        spin3.setAdapter(adapter3);

        addMachine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMachine2();

            }
        });

    }

    public void addMachine2() {

        String kolej = spin1.getSelectedItem().toString();
        String machineSlot = spin2.getSelectedItem().toString();
        String slot = spin3.getSelectedItem().toString();
        String id = databaseReference.push().getKey();

        Machine2 machine2 = new Machine2(

                kolej,
                machineSlot,
                slot,
                id

        );

        databaseReference.child(id).setValue(machine2);

        Toast.makeText(AddMachine2.this, "Machine added", Toast.LENGTH_SHORT).show();
        Intent intAdd = new Intent(AddMachine2.this, ListMachine2Activity.class);
        startActivity(intAdd);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }
}
