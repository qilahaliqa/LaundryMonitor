package com.example.laundrymonitor.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundrymonitor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MachineGAFragment extends Fragment {


    public MachineGAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_machine_ga, container, false);
    }

}
