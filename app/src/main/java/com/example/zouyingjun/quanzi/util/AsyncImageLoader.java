package com.example.zouyingjun.quanzi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.zouyingjun.quanzi.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import java.io.File;
import java.io.IOException;

import okhttp3.internal.DiskLruCache;


/**
 * 图片加载,防止错乱（基于imageloader）
 * Created by j on 2016/11/17.
 */

public class AsyncImageLoader {
    private static final ImageLoader loader = ImageLoader.getInstance();
    private Context context;
    //内存缓存8M
    static final int MEM_CACHER_DEFAULT_SIZE = 8*1024*1024;
    //文件缓存10M
    static final int DISK_CACHE_DEFAULT_SIZE = 10*1024*1024;
    /**一级缓存*/
    private LruCache<String ,Bitmap> memCache;
    /**二级缓存*/
    private DiskLruCache diskCache;

    public static void dispaly(String uri, ImageView iv){
        loader.displayImage(uri,iv,getSimpleOptions());
    }

    public AsyncImageLoader(Context context) {
        this.context = context;
        initLruCache();
//        initDisLruCache();
    }
    /**初始化问件缓存*/
//    private void initDisLruCache() {
//        try {
//            File cacheDir = getDiskCacheDir(context, "bitmap");
//            if (!cacheDir.exists()) {
//                cacheDir.mkdirs();
//            }
////            diskCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, DISK_CACHE_DEFAULT_SIZE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    /**初始化内存缓存*/
    private void initLruCache() {
        memCache = new LruCache<String,Bitmap>(MEM_CACHER_DEFAULT_SIZE){
            @Override
            protected int sizeOf(String key, Bitmap bt) {
                return bt.getByteCount();
            }
        };

    }




    /**
     * 设置常用的设置项
     * @return
     */
    private static DisplayImageOptions getSimpleOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_ing) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.logo)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.img_nointernet)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        return options;
    }

}
