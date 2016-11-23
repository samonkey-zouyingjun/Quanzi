package com.fz.baseview;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 永动跑马灯效果TextView
 * @author Administrator
 *
 */
public class ForeverMarqueeTextView extends TextView{

	public ForeverMarqueeTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public ForeverMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public ForeverMarqueeTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	

	
	private void init()
	{
		// TODO Auto-generated method stub
		setSingleLine(true);
		setEllipsize(TruncateAt.MARQUEE);
	
	}

	@Override
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
	}
}
