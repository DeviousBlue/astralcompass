package com.mciad.astralcompass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpireStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();

    public SpireStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        fragList.add(fragment);
        titleList.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        if(i<fragList.size() && i>0){
            return fragList.get(i);
        }else{
            return fragList.get(0);
        }
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
