package com.fz.listener;


import android.content.Context;
import android.text.TextUtils;

import com.fz.R;
import com.fz.pop.BaseFzProgressDialog;
import com.fz.pop.FzProgressDialog;
import com.fz.utils.SystemUtils;
import com.fz.utils.ToastUtils;

/**  
 *   
 * @author cate
 * 2014-12-3 下午5:43:06   
 */

/**
 * 已弃用
 * 用来应付基本的网络数据请求
 * @author Administrator
 *
 */

@Deprecated
public abstract class SimpleFzHttpListener implements FzHttpListener
{
	private BaseFzProgressDialog mProgressDialog;
	private String mLoadingTips;
	private Context mContext;
	
	
	/**
	 * 
	 */
	public SimpleFzHttpListener(Context context){
		this.mContext = context;
		mProgressDialog = new FzProgressDialog(mContext);
	}
	
	/**
	 * 构造
	 * @param dialog  该参数若为空，则调用默认的dialog
	 */
	public SimpleFzHttpListener(Context context,BaseFzProgressDialog dialog)
	{
		this.mContext = context;
		mProgressDialog = dialog;
		
	}
	
	/**
	 * 
	 * @param dialog
	 * @param tips
	 */
	public SimpleFzHttpListener(Context context,BaseFzProgressDialog dialog ,String tips)
	{
		this.mContext = context;
		mProgressDialog = dialog;
		mLoadingTips = tips;
		showDialog(true);
	}
	
	@Override
	public void onStart()
	{
		showDialog(true);
	}
	

	@Override
	public  void onSuccess(Object t)
	{
		if (t==null)
		{
			ToastUtils.showLongToast( R.string.tips_nodata);
		}
		showDialog(false);
	}

	@Override
	public void onFailure(Throwable t, int errorNo, String strMsg)
	{
		showDialog(false);
		if (!SystemUtils.checkNet(mContext))
		{
			ToastUtils.showLongToast( R.string.error_net);
		}else {
			if (errorNo==888)
			{
				ToastUtils.showLongToast(strMsg);
			}
			else 
			{
				ToastUtils.showLongToast(R.string.tips_failedload);
			}
			
		}
		
	}

	@Override
	public void onLoading(long count, long current)
	{
		
	}
	
	private void showDialog(boolean isShow){
		if (mProgressDialog!=null)
		{
			mProgressDialog.setShowMessage(mLoadingTips);
			if (isShow)
			{
				mProgressDialog.showProgress();
			}
			else {
				mProgressDialog.dismissProgress();
			}
		}
//		else {
//			if (isShow)
//			{
//				TipsUtils.showProgressDialog( mLoadingTips);
//				
//				
//				
//			}
//			else {
//				TipsUtils.dismissProgressDialog();
//			}
//		}
	}
}
