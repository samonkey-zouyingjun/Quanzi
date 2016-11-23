package com.fz.afinal.http;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fz.afinal.FinalHttp;
import com.fz.listener.FzHttpListener;
import com.fz.utils.FzConfig;
import com.fz.utils.LogUtils;

/**    
 * @author cate
 * 2014-12-2 下午4:34:24   
 */


@Deprecated
public class FzHttpRequest
{
	
	private static FzHttpRequest mDao;

	public static FzHttpRequest getInstance() {
		if (mDao == null) {
			mDao = new FzHttpRequest();
		}
		return mDao;
	}
	private FzHttpRequest(){}
	
	@Deprecated
	public void getString(String URL,
			AjaxParams params,FzHttpListener listener)
	{
		this.get(URL, params, listener, null);
	}
	@Deprecated
	public void getPostString(String URL,
			AjaxParams params,FzHttpListener listener)
	{
		this.post(URL, params, listener, null);
	}
	
	@Deprecated
	public void get(String URL,
			AjaxParams params,FzHttpListener listener, final Class<?> mclass) {
		FinalHttp http = new FinalHttp();
		http.get(URL, params, new Callback(listener, mclass));
	}
	@Deprecated
	public void post(String URL,
			AjaxParams params,FzHttpListener listener, final Class<?> mclass) {
		FinalHttp http = new FinalHttp();
		http.post(URL, params, new Callback(listener, mclass));
	}
	@Deprecated
	class Callback extends AjaxCallBack<String>{
		private FzHttpListener mListener;
		private Class<?> mClass;
		public Callback(FzHttpListener listener,Class<?> mclass)
		{
			mListener = listener;
			mClass = mclass;
		}
		@Override
		public boolean isProgress()
		{
			return super.isProgress();
		}
		@Override
		public int getRate()
		{
			return super.getRate();
		}
		@Override
		public AjaxCallBack<String> progress(boolean progress, int rate)
		{
			return super.progress(progress, rate);
		}
		@Override
		public void onStart()
		{
			super.onStart();
			mListener.onStart();
		}
		@Override
		public void onLoading(long count, long current)
		{
			super.onLoading(count, current);
			LogUtils.e("onLoading", "count-->"+count+"  current--> "+current);
			if (mListener!=null)
			{
				mListener.onLoading(count, current);
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void onSuccess(String t)
		{
			super.onSuccess(t);
			
			LogUtils.e("onSuccess", "t-->"+t);
			long startTime = System.currentTimeMillis();
			if (mClass==null)
			{
				mListener.onSuccess(t);
			}
			else {
				JSONObject jsonObject = JSON.parseObject(t.toString());
				String status = jsonObject.getString(FzConfig.STATUS);
				String msg=jsonObject.getString(FzConfig.MSG);
				String result = "";
				if (status.equals(FzConfig.SUCCESS)) {
					result = jsonObject.getString(FzConfig.DATA);
					if (result != null && !result.equals("")) {
						if (result.charAt(0) == '[') {
							List<?> list = JSON.parseArray(result, mClass);//
							if (mListener!=null)
							{
								mListener.onSuccess(list);
							}
						} else {
							Object object = JSON.parseObject(result, mClass);
							if (mListener!=null)
							{
								mListener.onSuccess(object);
							}
						}
					}else if(result.equals("")){
						if (mListener!=null)
						{
							mListener.onSuccess(new ArrayList());
						}
					}
				} else {
					if (mListener!=null)
					{
						mListener.onFailure(null, 888, msg);
//						mListener.onSuccess(null);
					}
				}
			}
			
			long endTime = System.currentTimeMillis();
			System.out.println("旧版耗时--》"+(endTime-startTime));
		}

		@Override
		public void onFailure(Throwable t, int errorNo, String strMsg)
		{
			super.onFailure(t, errorNo, strMsg);
			if (mListener!=null)
			{
				mListener.onFailure(t, 0, strMsg);
			}
			LogUtils.e("Failure", "t-->"+t.getMessage()+" no-->"+errorNo+" strMsg-->"+strMsg);
		}
	}
	
}
