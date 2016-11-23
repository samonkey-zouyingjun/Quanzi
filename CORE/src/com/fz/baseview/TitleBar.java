package com.fz.baseview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fz.R;

/**    
 * @author cate
 * 2014-11-25 10:56:37   
 */

public class TitleBar extends View
{
	
	
	@SuppressLint("NewApi")
	public TitleBar(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initTitle(attrs);
	}

	public TitleBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initTitle(attrs);
	}
	public TitleBar(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		initTitle(null);
	}
	
	private void initTitle(AttributeSet attrs)
	{
		// TODO Auto-generated method stub
		mView = LayoutInflater.from(getContext()).inflate(R.layout.view_titlebar, null);
	}
	private View mView;
	private ImageView mBack;
	private TextView mTitle;
	private ImageView mMore;
	private TextView mTxtMore;
	
	
	
}
