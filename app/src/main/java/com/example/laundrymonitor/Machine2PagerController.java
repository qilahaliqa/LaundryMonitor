package com.example.laundrymonitor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.laundrymonitor.Fragment.MachineLAFragment;
import com.example.laundrymonitor.Fragment.MachineLBFragment;

public class Machine2PagerController extends FragmentPagerAdapter {
    int tabCounts;

    public Machine2PagerController(@NonNull FragmentManager fm, int tabCounts) {
        super(fm, tabCounts);
        this.tabCounts = tabCounts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MachineLAFragment();
            case 1:
                return new MachineLBFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCounts;
    }
}
