package com.fz.base;

import java.lang.reflect.Field;

import com.fz.afinal.FinalBitmap;
import com.fz.afinal.FinalActivity.Method;
import com.fz.afinal.annotation.view.EventListener;
import com.fz.afinal.annotation.view.Ioc;
import com.fz.afinal.annotation.view.Select;
import com.fz.afinal.annotation.view.ViewInject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * 封装了Fragment的基本操作 规范    
 * 类似BaseActivity
 * @see com.fz.base.BaseActivity
 * @author cate
 * 2014-12-6 下午2:27:26   
 */
public abstract class BaseFragment extends Fragment  implements OnClickListener
{
	
	private FinalBitmap mFinalBitmap;
	
	/**
	 * 获取异步图片加载对象FinalBitmap
	 * @return FinalBitmap
	 */
	protected FinalBitmap getBitmapLoader()
	{
		if (mFinalBitmap == null)
		{
			mFinalBitmap = FinalBitmap.create(getActivity());
		}
		return mFinalBitmap;
	}
	
	
     @SuppressLint("HandlerLeak")
	Handler threadHandle = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x37213721) {
            	threadDataInited();
            }
        };
    };
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	ViewGroup container,
	Bundle savedInstanceState)
	{
		return inflaterView(inflater, container, savedInstanceState);
	}
	
	
	@Override
	public void onViewCreated(View view, 
	Bundle savedInstanceState)
	{
		Ioc.initInjectedView(this,view);
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		initEvent();
		initData();
		 new Thread(new Runnable() {
	            @Override
	            public void run() {
	                // 在线程中执行初始化数据
	                initDataFromThread();
	                // 初始化完成发送一条message
	                threadHandle.sendEmptyMessage(0x37213721);
	            }
	        }).start();
	}
	
	/**
	 * Fragment.onCreateView（）
	 * @param inflater
	 * @param container
	 * @param bundle
	 * @return
	 */
    protected abstract View inflaterView(LayoutInflater inflater,
            ViewGroup container, Bundle bundle);
    
    /**
     * 初始化View
     * @param view
     */
	protected void initView(View view)
	{
		
	}
	
	/**
	 * 初始化事件监听  
	 */
	protected void initEvent()
	{
		// TODO 初始化事件监听  
	}
	
	/**
	 * 初始化数据，执行在主线程
	 */
	protected void initData()
	{
		
	}
	
	/**
	 * 异步加载数据
	 */
	protected void initDataFromThread()
	{
		
	}
	 /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
	protected void threadDataInited()
	{
		
	}
	
	
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	@Override
	public void onPause()
	{
		super.onPause();
	}
	@Override
	public void onResume()
	{
		super.onResume();
	}
	
	@Override
	public void onLowMemory()
	{
		// TODO Auto-generated method stub
		super.onLowMemory();
		if (mFinalBitmap != null)
		{
			mFinalBitmap.clearMemoryCache();
		}
		
	}
	
	/**
	 * 无传递参数简单跳转Activity方法
	 * @param clazz 将要跳转的目的地
	 * @param isClose 完成后是否关闭此类
	 */
	public void startActivity(Class<?> clazz, boolean isClose){
		startActivity(new Intent(getActivity(), clazz));
		if (isClose) {
			getActivity().finish();
		}
	}
	
	/**
	 * 简单跳转Activity方法
	 * @param intent 你懂得
	 * @param isClose 完成后是否关闭此类
	 */
	public void startActivity(Intent intent, boolean isClose){
		startActivity(intent);
		if (isClose) {
			getActivity().finish();
		}
	}
	
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
	}

	
}
