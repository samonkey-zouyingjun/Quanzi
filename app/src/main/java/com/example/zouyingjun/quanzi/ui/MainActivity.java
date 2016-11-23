package com.example.zouyingjun.quanzi.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.fragment.JingXuanFragment;
import com.example.zouyingjun.quanzi.fragment.QuanZiFragment;
import com.example.zouyingjun.quanzi.fragment.ShiPinFragment;
import com.example.zouyingjun.quanzi.fragment.WoDeFragment;
import com.example.zouyingjun.quanzi.fragment.YouXiFragment;

import java.util.List;

public class MainActivity extends FragmentActivity {


    List<Fragment> fgms;
    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setSystemStatus();
        setContentView(R.layout.activity_main);

        initView();
    }

    /**5.0以后会有透明条*/
    private void setSystemStatus() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

//            Log.d("TAG","SNK版本:"+Build.VERSION.SDK_INT);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FF443F"));

        }

    }

    FragmentManager fm;
    FragmentTransaction ft;

    private void initView() {

        fm = MainActivity.this.getSupportFragmentManager();

        rg = (RadioGroup) findViewById(R.id.rg_main);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                int cid = rg.getCheckedRadioButtonId();

                switch (cid){
                    case R.id.rb_jinxuan:
                        setCheckedFragment(new JingXuanFragment());
                        break;
                    case R.id.rb_video:
                        setCheckedFragment(new ShiPinFragment());
                        break;
                    case R.id.rb_game:
                        setCheckedFragment(new YouXiFragment());
                        break;
                    case R.id.rb_community:
                        setCheckedFragment(new QuanZiFragment());
                        break;
                    case R.id.rb_my:
                        setCheckedFragment(new WoDeFragment());
                        break;
                }
            }
        });

        rb = (RadioButton) findViewById(R.id.rb_jinxuan);

        rb.setChecked(true);

    }
    private void setCheckedFragment(Fragment fg) {

        ft = fm.beginTransaction();
        ft.replace(R.id.fl_main_contain,fg);
        ft.commit();
        ft = null;

    }
}
