package com.fz.listener;

/** 
 * 已弃用  建议适用FzHttpReq加FzCallBack
 * 网络数据请求的回调接口，开发者可根据业务需要实现此接口   
 * @author cate
 * 2014-12-2 下午5:14:19   
 */
@Deprecated  
public interface  FzHttpListener
{
	/**
	 * 网络数据请求成功时的回调
	 * @param t
	 */
	public void onSuccess(Object t);
	/**
	 * 网络数据请求失败时的回调
	 * @param t
	 * @param errorNo
	 * @param strMsg
	 */
	public void onFailure(Throwable t,int errorNo ,String strMsg);
	/**
	 * 网络数据加载时的回调  （暂未实现）
	 * @param count
	 * @param current
	 */
	public void onLoading(long count,long current);
	public void onStart();
}
