package com.example.zouyingjun.quanzi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.adapter.MainViewPagerAdaper;
import com.example.zouyingjun.quanzi.view.TabGroup;

import java.util.ArrayList;
import java.util.List;

public class QuanZiFragment extends Fragment {
    TabGroup tg;
    ViewPager vp;
    List<Fragment> fgs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_quan_zi, container, false);

        initData();

        initTabGroup(v);

        initViewPager(v);


        return v;
    }

    private void initTabGroup(final View v) {
        tg = (TabGroup) v.findViewById(R.id.tg_quanzi);
        ((RadioButton)tg.getChildAt(0)).setChecked(true);
        tg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                RadioButton rb = (RadioButton) v.findViewById(checkedId);
                int i = rg.indexOfChild(rb);
                tg.updata(i,0);
                vp.setCurrentItem(i);
            }
        });

    }


    private void initData() {
        fgs = new ArrayList<>();

        QJiaoLiuFragment qjl = new QJiaoLiuFragment();
        QJinXuanFragment qjx = new QJinXuanFragment();
        QZiYuanFragment qzy = new QZiYuanFragment();

        fgs.add(qjx);
        fgs.add(qzy);
        fgs.add(qjl);

    }

    private void initViewPager(View v) {

        vp = (ViewPager) v.findViewById(R.id.vp_quanzi);

//        FragmentManager fm = ((FragmentActivity) getActivity()).getSupportFragmentManager();
        FragmentManager fm = getChildFragmentManager();


        MainViewPagerAdaper adapter = new MainViewPagerAdaper(fm,fgs);

        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tg.updata(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)tg.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);
    }


}
