package com.fz.baseview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.fz.R;

/**
 * FzLinearLayout
 * @author cate
 * 2015-10-9 下午5:17:59   
 */

public class FzLinearLayout extends LinearLayout
{
	
	@SuppressLint("NewApi")
	public FzLinearLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public FzLinearLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public FzLinearLayout(Context context)
	{
		super(context);
	}
	
	private void init(Context context, AttributeSet attrs){
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FzView);
//		if (a.hasValue(R.styleable.FzView_pressColor)) {
//			mMode = Mode.mapIntToValue(a.getInteger(R.styleable.PullToRefresh_ptrMode, 0));
//		}
//		if (a.hasValue(R.styleable.PullToRefresh_ptrLayoutmode)) {
//			setLoadingMode(LoadingMode.mapIntToValue(a.getInteger(R.styleable.PullToRefresh_ptrLayoutmode, 0)));
//		}
//
//		if (a.hasValue(R.styleable.PullToRefresh_ptrAnimationStyle)) {
//			mLoadingAnimationStyle = AnimationStyle.mapIntToValue(a.getInteger(
//					R.styleable.PullToRefresh_ptrAnimationStyle, 0));
//		}
	}
	
	
	
	
}
