package com.fz.baseview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * FzView 的基类
 * @author cate
 * 2015-10-9 下午3:56:36   
 */

public abstract class FzView
{
	private final static float NO_RADIUS = -1;
	
	private static final int DEFAULT_BACKGROUNDCOLOR = Color.parseColor("#D8D8D8");
	
	protected int mPressColor = DEFAULT_BACKGROUNDCOLOR;
	protected int mNormalColor = DEFAULT_BACKGROUNDCOLOR;
	protected int mUnableColor = DEFAULT_BACKGROUNDCOLOR;
	protected float leftTopRadius = 0;
	protected float leftBottomRadius = 0;
	protected float rightTopRadius = 0;
	protected float rightBottomRadius = 0;
	
	protected float mCustomRadius = NO_RADIUS;
	
	
	/**
	 * 初始化
	 */
	protected void init( )
	{
		if (mPressColor!=DEFAULT_BACKGROUNDCOLOR)
		{
			StateListDrawable stateDrawable = new StateListDrawable();
			
			GradientDrawable pressDrawable = new GradientDrawable();
			GradientDrawable normalDrawble = new GradientDrawable();
			GradientDrawable unableDrawble = new GradientDrawable();
			if (mCustomRadius!=NO_RADIUS)
			{
				pressDrawable.setCornerRadius(mCustomRadius);
				normalDrawble.setCornerRadius(mCustomRadius);
				unableDrawble.setCornerRadius(mCustomRadius);
			}else {
				pressDrawable.setCornerRadii(getnerateRadius(leftTopRadius, leftBottomRadius, rightTopRadius, rightBottomRadius));
				normalDrawble.setCornerRadii(getnerateRadius(leftTopRadius, leftBottomRadius, rightTopRadius, rightBottomRadius));
				unableDrawble.setCornerRadii(getnerateRadius(leftTopRadius, leftBottomRadius, rightTopRadius, rightBottomRadius));
			}
			
//			if (mPressColor!=DEFAULT_BACKGROUNDCOLOR)
//			{
				pressDrawable.setColor(mPressColor);
//				pressDrawable.setColor(mPressColor);
//				pressDrawable.setColor(mPressColor);
//			}
//			if (mNormalColor!=DEFAULT_BACKGROUNDCOLOR)
//			{
				normalDrawble.setColor(mNormalColor);
//			}
			
//			if (mUnableColor!=DEFAULT_BACKGROUNDCOLOR)
//			{
				unableDrawble.setColor(mUnableColor);
//			}
			
			
			
			stateDrawable.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressDrawable);
			stateDrawable.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, pressDrawable);
			stateDrawable.addState(new int[] { android.R.attr.state_window_focused }, unableDrawble);
			stateDrawable.addState(new int[] { }, normalDrawble);
			
			setDrawable(stateDrawable);
		}
		
	
	}
	
	public abstract void setDrawable(Drawable drawable);
	
	
	
//	top-left, top-right, bottom-right, bottom-left.
	private float[] getnerateRadius(float leftTop,float leftBottom,float rightTop,float rightBottom){
		return new float[]{leftTop,leftTop,rightTop,rightTop,rightBottom,rightBottom,leftBottom,leftBottom};
	}
 	
//    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {  
//        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };  
//        int[][] states = new int[6][];  
//        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };  
//        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };  
//        states[2] = new int[] { android.R.attr.state_enabled };  
//        states[3] = new int[] { android.R.attr.state_focused };  
//        states[4] = new int[] { android.R.attr.state_window_focused };  
//        states[5] = new int[] {};  
//        ColorStateList colorList = new ColorStateList(states, colors);  
//        return colorList;  
//    }  
}
