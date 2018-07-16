package com.example.anton.remindme.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.anton.remindme.fragment.AbstractFragment;

import java.util.ArrayList;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter{
    private ArrayList<String> FragmentTitels;
    private ArrayList<Fragment> FragmentList;
    private Context context;

    public TabsPagerFragmentAdapter(Context context,FragmentManager fm){
        super(fm);
        this.context=context;
        FragmentTitels=new ArrayList<>();
        FragmentList=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentList.get(position);
    }

    @Override
    public int getCount() {
      return   FragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence currient=FragmentTitels.get(position);
        return currient;
    }

    public void addFragment(AbstractFragment fragment, String title) {
        FragmentList.add(fragment);
        FragmentTitels.add(title);
    }

}
