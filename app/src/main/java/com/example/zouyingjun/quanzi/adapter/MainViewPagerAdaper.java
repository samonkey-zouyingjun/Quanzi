package com.example.zouyingjun.quanzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 2016/11/14.
 */

public class MainViewPagerAdaper extends FragmentPagerAdapter {

    List<Fragment> mfgs;

    public MainViewPagerAdaper(FragmentManager fm, List<Fragment> list) {
        super(fm);
        if(list!=null){
            mfgs = list;
        }else{
            mfgs = new ArrayList<>();
        }

    }

    @Override
    public Fragment getItem(int position) {
        return mfgs.get(position);
    }

    @Override
    public int getCount() {
        return mfgs.size();
    }
}
