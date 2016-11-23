package com.fz.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import com.fz.afinal.FinalBitmap;
import com.fz.afinal.annotation.view.Ioc;
import com.fz.pop.FzProgressDialog;

/**
 * 封装了Activity的基本操作 规范
 * 执行顺序fzOnCreate（必须在此设置setContentView）   -->initView    -->initData  -->initDataFromThread  -->threadDataInited
 * @author cate 2014-12-2 下午4:29:27
 */

public abstract class BaseActivity extends FragmentActivity implements OnClickListener
{
	
	private FinalBitmap mFinalBitmap;
	private FzProgressDialog mDialog;
	
	
	/**
	 * 获取异步图片加载对象FinalBitmap
	 * @return FinalBitmap
	 */
	protected FinalBitmap getBitmapLoader()
	{
		if (mFinalBitmap == null)
		{
			mFinalBitmap = FinalBitmap.create(this);
		}
		return mFinalBitmap;
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (mDialog!=null)
		{
			mDialog.dismiss();
			mDialog=null;
		}
	}
	
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		if (mFinalBitmap != null)
		{
			mFinalBitmap.clearMemoryCache();
		}
		
	}
	@Override
	protected void onPause()
	{
		super.onPause();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
	}
	
	/**
	 * 如果标题栏使用R.layout.view_titlebar的话  该方法在返回按钮点击时生效
	 * @param view
	 */
	public void backPressed(View view)
	{
		onBackPressed();
	}
	
	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		fzOnCreate(arg0);
		initView();
		initEvent();
		initData();
		new Thread()
		{
			public void run()
			{
				initDataFromThread();
				handler.sendEmptyMessage(0x37213722);
			}
		}.start();
		
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == 0x37213722)
			{
				threadDataInited();
			}
		}
		
	};
	
	
	
	
	/**
	 * Activity.onCreate 需在此setContentView 必须实现
	 * @param arg0
	 */
	protected abstract void fzOnCreate(Bundle arg0);
	
	/**
	 * 初始化View  必须在fzOnCreate（setContentView）后
	 * 
	 */
	protected void initView()
	{
		// TODO 初始化view
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
		// TODO 初始化数据
	}
	
	/**
	 * 异步加载数据
	 */
	protected void initDataFromThread()
	{
		// TODO 此方法在线程中执行
	}
	
	/**
	 * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
	 */
	protected void threadDataInited()
	{
		// TODO 线程方法执行完成后 在此回调
	}
	
	
	/**
	 * 无传递参数简单跳转Activity方法
	 * @param clazz 将要跳转的目的地
	 * @param isClose 完成后是否关闭此类
	 */
	public void startActivity(Class<?> clazz, boolean isClose){
		startActivity(new Intent(this, clazz));
		if (isClose) {
			finish();
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
			finish();
		}
	}
	
	
	@Override
	public void onClick(View v)
	{
		
	}
	@Override  
	public Resources getResources() {  
	    Resources res = super.getResources();    
	    Configuration config=new Configuration();    
	    config.setToDefaults();    
	    res.updateConfiguration(config,res.getDisplayMetrics() );  
	    return res;  
	}  
	
	/**
	 * 获取进度条对话框
	 * @return
	 */
	public FzProgressDialog getProgressDialog(){
		if (mDialog==null)
		{
			mDialog = new FzProgressDialog(this);
		}
		return mDialog;
	}
	
	
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		Ioc.initInjectedView(this,getWindow().getDecorView());
	}


	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		Ioc.initInjectedView(this,getWindow().getDecorView());
	}


	public void setContentView(View view) {
		super.setContentView(view);
		Ioc.initInjectedView(this,getWindow().getDecorView());
	}
	

	
}
