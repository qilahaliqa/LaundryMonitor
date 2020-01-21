package com.example.laundrymonitor.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class UpdateMachine extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView close;
    private Button update;
    private Spinner spinner1, spinner2, spinner3;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_machine);

        close.findViewById(R.id.closeBar);
        update.findViewById(R.id.updateMachine);
        spinner1.findViewById(R.id.kolejType1);
        spinner2.findViewById(R.id.machineType1);
        spinner3.findViewById(R.id.slotType1);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference("Kolej Tun Sri Gemala").child(firebaseAuth.getUid());
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.gemala_array, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.machine_array, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.slot_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateM();

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataSnapshot.getValue(Machine.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateMachine.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void updateM(){

        String kolej = spinner1.getSelectedItem().toString();
        String machineSlot = spinner2.getSelectedItem().toString();
        String slot = spinner3.getSelectedItem().toString();

        String id = databaseReference.getKey();

        Machine machine = new Machine(kolej,
                machineSlot,
                slot,
                id);

        databaseReference.child(id).setValue(machine);

        finish();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
