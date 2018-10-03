package com.example.jmirza.weatherforcaster.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.example.jmirza.weatherforcaster.R;
import com.example.jmirza.weatherforcaster.fragments.CurrentFragment;
import com.example.jmirza.weatherforcaster.fragments.ForcastFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {
    int numOfTabs;
    String celcius,country;
    FragmentManager manager;



    public MyPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs=numOfTabs;
    }
    public MyPagerAdapter(FragmentManager fm, String celcius, String country){
        super(fm);
        this.celcius=celcius;
        this.country= country;

    }

    @Override    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CurrentFragment currentFragment=new CurrentFragment();
                return currentFragment;
            case 1:
                ForcastFragment forcastFragment=new ForcastFragment();
                return  forcastFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override    public CharSequence getPageTitle(int position) {
        switch (position){
        case 0:
            return "CURRENT";
        case 1:
            return "7 DAYS FORECAST";
        default:
            return null;
    }
    }
}
