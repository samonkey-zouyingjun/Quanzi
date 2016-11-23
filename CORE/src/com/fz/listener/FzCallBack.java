
package com.fz.listener;

import java.lang.reflect.ParameterizedType;
import com.fz.afinal.http.FzHttpResponse;

/**
 * @author cate 2015-2-4 下午2:34:52
 */
public abstract class FzCallBack<T>
{
	
	private boolean progress = true;
	private int rate = 1000 * 1;// 每秒
	
	public boolean isProgress()
	{
		return progress;
	}
	
	public int getRate()
	{
		return rate;
	}
	
	/**
	 * 设置进度,而且只有设置了这个了以后，onLoading才能有效。
	 * 
	 * @param progress
	 *            是否启用进度显示
	 * @param rate
	 *            进度更新频率
	 */
	public FzCallBack<T> progress(boolean progress, int rate)
	{
		this.progress = progress;
		this.rate = rate;
		return this;
	}
	
	public void onStart()
	{
	};
	
	/**
	 * onLoading方法有效progress
	 * 
	 * @param count
	 * @param current
	 */
	public void onLoading(long count, long current)
	{
	};
	
	public  void onSuccess(FzHttpResponse<T> response)
	{
	};
	
	public void onFailure(Throwable t, int errorNo, String strMsg)
	{
	};
	
	public Class<T> getTClass()
	{
		Class<T> mClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return mClass;
	}
}
