package com.example.zouyingjun.quanzi.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by j on 2016/11/17.
 */

public class MyApp extends Application{
    /**千万记得配置*/
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context= this;
        //ImageLoader做初始化
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

        Log.i("TAG","ImageLoader做初始化");

    }
}
