package com.fz.baseview;

import com.fz.afinal.FinalBitmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**    
 * @author cate
 * 2015-1-23 下午3:36:22   
 */

public class NetWorkImageView extends ImageView
{

	private FinalBitmap mBitmapLoader;
	

	public NetWorkImageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public NetWorkImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public NetWorkImageView(Context context)
	{
		super(context);
	}
	
	public void setImageUrl(String url){
		
		
		
	}
	
	
	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		
		if (mBitmapLoader!=null)
		{
			mBitmapLoader.onDestroy();
			setImageBitmap(null);
			
		}
		
	}
	
}
