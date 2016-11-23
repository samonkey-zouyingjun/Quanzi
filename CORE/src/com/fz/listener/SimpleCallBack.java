package com.fz.listener;

import android.content.Context;
import com.fz.R;
import com.fz.afinal.http.FzHttpResponse;
import com.fz.pop.BaseFzProgressDialog;
import com.fz.pop.FzProgressDialog;
import com.fz.pop.ProgressDialog;
import com.fz.utils.SystemUtils;
import com.fz.utils.ToastUtils;

/**
 * @author cate 2015-2-4 下午7:09:55
 */
public abstract class SimpleCallBack<T> extends FzCallBack<T>
{
	private BaseFzProgressDialog mProgressDialog;
	private Context mContext;
	private boolean showDialog;
	
	/**
	 * 
	 */
	public SimpleCallBack(Context context)
	{
		this(context, false);
	}
	
	/**
	 * 
	 * @param context
	 * @param dialog
	 * @param emptyview
	 */
	public SimpleCallBack(Context context, boolean isShowDialog)
	{
		this.mContext = context;
		this.showDialog = isShowDialog;
		if (isShowDialog)
		{
			this.mProgressDialog = new ProgressDialog(context);
		}
	}
	
	public SimpleCallBack(Context context, BaseFzProgressDialog dialog)
	{
		this.mContext = context;
		this.showDialog = true;
		if (dialog!=null)
		{
			this.mProgressDialog = dialog;
		}
		
	}
	
	@Override
	public void onSuccess(FzHttpResponse<T> response)
	{
		if (response.getFlag().equals("1"))
		{
			ToastUtils.showLongToast(response.getMsg());
		}
		showDialog(false);
	}
	
	@Override
	public void onFailure(Throwable t, int errorNo, String strMsg)
	{
		showDialog(false);
		if (!SystemUtils.checkNet(mContext))
		{
			ToastUtils.showLongToast(R.string.error_net);
		}
		else
		{
			ToastUtils.showLongToast(R.string.tips_failedload);
		}
	}
	
	@Override
	public void onStart()
	{
		showDialog(true);
	}
	
	private void showDialog(boolean isShow)
	{
		if (showDialog)
		{
			if (mContext!=null && mProgressDialog != null)
			{
				if (isShow)
				{
					mProgressDialog.showProgress();
				}
				else
				{
					mProgressDialog.dismissProgress();
				}
			}
		}
		
	}
	
}
