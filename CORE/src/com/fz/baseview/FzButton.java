package com.fz.baseview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;
import com.fz.utils.FzImage;

/**
 * 
 * 按钮类
 * 
 * @author Soniy7x
 * 
 */
@SuppressLint("NewApi")
public class FzButton extends Button
{
	private static final int DEFAULT_BACKGROUNDCOLOR = Color.parseColor("#D8D8D8");
	private int backgroundColor = DEFAULT_BACKGROUNDCOLOR;
	private Bitmap bitmap;
	private float radius = 5;
	private float distanceX = 0;
	private float distanceY = 0;
	private Paint mPaint = new Paint();
	
	/**
	 * 构造方法
	 */
	public FzButton(Context context)
	{
		super(context);
		init();
	}
	
	/**
	 * 构造方法
	 */
	public FzButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	
	/**
	 * 构造方法
	 */
	public FzButton(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}
	

	/**
	 * 初始化方法
	 */
	private void init()
	{
		
		if (Build.VERSION.SDK_INT >= 11)
		{
			try
			{
				setBackgroundColor(((ColorDrawable) getBackground()).getColor());
			}
			catch (Exception e)
			{
				setBackgroundColor(backgroundColor);
			}
		}
		else
		{
			setBackgroundColor(backgroundColor);
		}
		setGravity(Gravity.CENTER);
	}
	
	/**
	 * 设置背景颜色
	 * 
	 * @param backgroundColor
	 */
	@SuppressWarnings("deprecation")
	public void setBackgroundColor(int backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		
		if (Build.VERSION.SDK_INT >= 16)
		{
			super.setBackground(FzImage.createBackgroundDrawable(backgroundColor, radius));
		}
		else
		{
			super.setBackgroundDrawable(FzImage.createBackgroundDrawable(backgroundColor, radius));
		}
	}
	
	/**
	 * 获得圆角大小
	 * 
	 * @return 圆角大小
	 */
	public float getRadius()
	{
		return radius;
	}
	
	/**
	 * 设置圆角大小
	 * 
	 * @param radius
	 *            圆角大小
	 */
	public void setRadius(float radius)
	{
		this.radius = radius;
		setBackgroundColor(backgroundColor);
	}
	
	/**
	 * 设置图片资源
	 * 
	 * @param resId
	 *            资源ID
	 */
	public void setImageResource(int resId)
	{
		setImage(new FzImage(getResources(), resId).toBitmap());
	}
	
	/**
	 * 设置图片资源
	 * 
	 * @param bitmap
	 *            图片
	 */
	public void setImage(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		this.setText("");
		invalidate();
	}
	
	/**
	 * 设置文字内容
	 * 
	 * @param text
	 *            内容
	 */
	public void setText(String text)
	{
		if (bitmap == null)
		{
			super.setText(text);
		}
	}
	
	public final float[] BT_SELECTED = new float[]
	{
			1, 0, 0, 0, -50, 0, 1, 0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0
	};
	public final float[] BT_NOT_SELECTED = new float[]
	{
			1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0
	};
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		Drawable drawable = getBackground();
		
		if (drawable != null)
		{
			switch (event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
					if (Build.VERSION.SDK_INT >= 16)
					{
						drawable.setColorFilter(Color.parseColor("#33000000"), PorterDuff.Mode.SRC_OVER);
						super.setBackground(drawable);
					}
					else
					{
						drawable.clearColorFilter();
						super.setBackgroundDrawable(drawable);
					}
					super.onTouchEvent(event);
					return true;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_CANCEL:
					drawable.clearColorFilter();
					if (Build.VERSION.SDK_INT >= 16)
					{
						super.setBackground(getBackground());
					}
					else
					{
						super.setBackgroundDrawable(getBackground());
					}
					return super.onTouchEvent(event);
				default:
					drawable.setColorFilter(Color.parseColor("#33000000"), PorterDuff.Mode.SRC_OVER);
					if (Build.VERSION.SDK_INT >= 16)
					{
						super.setBackground(getBackground());
					}
					else
					{
						super.setBackgroundDrawable(drawable);
					}
					return super.onTouchEvent(event);
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		if (bitmap != null)
		{
			distanceX = (float) ((getWidth() - bitmap.getWidth()) / 2.0);
			distanceY = (float) ((getHeight() - bitmap.getHeight()) / 2.0);
			canvas.drawBitmap(bitmap, distanceX, distanceY, mPaint);
		}
	}
	
}
