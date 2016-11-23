package com.fz.pop;


/** 
 * 进度条加载的接口
 * @author cate
 * 2014-12-3 下午6:08:20   
 */

public interface BaseFzProgressDialog
{
	
	/**
	 * 设置显示消息
	 * @param msg
	 * @return
	 */
	public BaseFzProgressDialog setShowMessage(String msg);
	/**
	 * 显示dialog
	 * @return
	 */
	public BaseFzProgressDialog showProgress();
	
	/**
	 * 隐藏dialog
	 * @return
	 */
	public BaseFzProgressDialog dismissProgress();
	
	/**
	 * 是否正在显示
	 * @return
	 */
	public boolean isDialogShowing();
}
