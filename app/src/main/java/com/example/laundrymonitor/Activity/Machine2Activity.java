package com.example.laundrymonitor.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.laundrymonitor.Adapter.Machine2Adapter;
import com.example.laundrymonitor.Model.Machine2;
import com.example.laundrymonitor.R;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class Machine2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    ArrayList<Machine2> list;
    Machine2Adapter machine2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerMachine2);
        recyclerView.setLayoutManager(new LinearLayoutManager(Machine2Activity.this));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getInstance().getReference().child("Kolej Tun Sri Lanang");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
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

                machine2Adapter = new Machine2Adapter(Machine2Activity.this, list);
                recyclerView.setAdapter(machine2Adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Machine2Activity.this, "Error. Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.machine2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        firebaseAuth = FirebaseAuth.getInstance();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent startIntent = new Intent(Machine2Activity.this, SettingsActivity.class);
            startActivity(startIntent);

            return false;

        } else if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Machine2Activity.this, LoginActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent startIntent = new Intent(Machine2Activity.this, HomeActivity.class);
            startActivity(startIntent);

        } else if (id == R.id.nav_laundry) {
            Intent startIntent = new Intent(Machine2Activity.this, MachineActivity.class);
            startActivity(startIntent);

        } else if (id == R.id.nav_profile) {
            Intent startIntent = new Intent(Machine2Activity.this, UpdateProfileActivity.class);
            startActivity(startIntent);

        } else if (id == R.id.nav_support) {
            Intent startIntent = new Intent(Machine2Activity.this, SliderActivity.class);
            startActivity(startIntent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
