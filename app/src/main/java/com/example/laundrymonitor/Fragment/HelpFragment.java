package com.example.laundrymonitor.Fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.laundrymonitor.R;

public class HelpFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.help_preferences, rootKey);

    }
}
