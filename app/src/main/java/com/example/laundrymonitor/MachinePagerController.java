package com.example.laundrymonitor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.laundrymonitor.Fragment.MachineGAFragment;
import com.example.laundrymonitor.Fragment.MachineGBFragment;
import com.example.laundrymonitor.Fragment.MachineGCFragment;

public class MachinePagerController extends FragmentPagerAdapter {
    int tabCounts;

    public MachinePagerController(@NonNull FragmentManager fm, int tabCounts) {
        super(fm, tabCounts);
        this.tabCounts = tabCounts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MachineGAFragment();
            case 1:
                return new MachineGBFragment();
            case 2:
                return new MachineGCFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCounts;
    }
}
