package com.fz.baseview;

import com.fz.utils.FzImage;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;

/**
 * 用来
 * @author cate
 * 2015-10-9 下午7:08:10   
 */

public class FzViewWraper
{
	private View mView;

	
	public FzViewWraper(View mView)
	{
		super();
		this.mView = mView;
		StateListDrawable dra;
		
		
		
	}
	
	/**
	 * 设置背景和圆角
	 * @param normalColor
	 * @param pressColor
	 * @param unableColor
	 * @param radius
	 */
	@SuppressLint("NewApi")
	public void setBackgroundAndRadius(int normalColor,int pressColor,int unableColor,float radius){
		if (this.mView==null)
		{
			try
			{
				throw new Exception("附着的view不能为空");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}else {
			Drawable drawable = FzImage.generateDrawable(normalColor, pressColor, unableColor, radius, radius, radius, radius, radius);
			if (Build.VERSION.SDK_INT>=16)
			{
				this.mView.setBackground(drawable);
			}else {
				this.mView.setBackgroundDrawable(drawable);
			}
		}
	}
	
	/**
	 * 设置背景和圆角
	 * @param normalColor   平常颜色
	 * @param pressColor
	 * @param radius
	 */
	public void setBackgroundAndRadius(int normalColor,int pressColor, float radius){
		this.setBackgroundAndRadius(normalColor, pressColor,  FzImage.NO_COLOR, radius);
	}
	
	
	/**
	 * 设置背景和圆角 按下颜色和不可用颜色
	 * @param color
	 * @param radius
	 */
	public void setBackgroundAndRadius(int color,float radius){
		this.setBackgroundAndRadius(color, FzImage.NO_COLOR,  FzImage.NO_COLOR, radius);
	}
	
}
