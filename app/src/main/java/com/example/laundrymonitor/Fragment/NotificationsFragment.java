package com.example.laundrymonitor.Fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.laundrymonitor.R;

public class NotificationsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.notifications_preferences, rootKey);

    }
}
