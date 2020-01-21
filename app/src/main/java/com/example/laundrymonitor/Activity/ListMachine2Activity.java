package com.example.laundrymonitor.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.laundrymonitor.Adapter.AdminMachine2Adapter;
import com.example.laundrymonitor.Model.Machine2;
import com.example.laundrymonitor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ListMachine2Activity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    ArrayList<Machine2> list;
    AdminMachine2Adapter adminMachine2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_machine2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), AddMachine2.class);
                startActivity(startIntent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerListMachine2);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListMachine2Activity.this));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getInstance().getReference("Kolej Tun Sri Lanang");

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Machine2 m = dataSnapshot1.getValue(Machine2.class);
                    list.add(m);

                }

                adminMachine2Adapter = new AdminMachine2Adapter(ListMachine2Activity.this, list);
                recyclerView.setAdapter(adminMachine2Adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListMachine2Activity.this, "Error. Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteRef(String id) {

        databaseReference.child(id).removeValue();

    }

    public void alertDelete(final String id) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.warning_delete, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final Button delete = dialogView.findViewById(R.id.yesDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRef(id);
                alertDialog.dismiss();
                Toast.makeText(ListMachine2Activity.this,"Delete Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_machine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
            startActivity(new Intent(ListMachine2Activity.this, AdminLoginActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        startActivity(new Intent(ListMachine2Activity.this, AdminHomeActivity.class));
        finish();
    }
}
