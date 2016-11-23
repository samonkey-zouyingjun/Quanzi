
package com.fz.afinal.bitmap.display;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.fz.afinal.bitmap.core.BitmapDisplayConfig;

public class RoundedBitmapDisplayer implements Displayer
{
	private int roundPixels;
	
	public RoundedBitmapDisplayer(int roundPixels)
	{
		this.setRoundPixels(roundPixels);
	}
	
	public static class RoundedDrawable extends Drawable
	{
		protected final float cornerRadius;
		protected final int margin;
		
		protected final RectF mRect = new RectF(), mBitmapRect;
		protected final BitmapShader bitmapShader;
		protected final Paint paint;
		
		public RoundedDrawable(Bitmap bitmap, int cornerRadius, int margin)
		{
			this.cornerRadius = cornerRadius;
			this.margin = margin;
			
			bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
			mBitmapRect = new RectF(margin, margin, bitmap.getWidth() - margin, bitmap.getHeight() - margin);
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setShader(bitmapShader);
		}
		
		@Override
		protected void onBoundsChange(Rect bounds)
		{
			super.onBoundsChange(bounds);
			mRect.set(margin, margin, bounds.width() - margin, bounds.height() - margin);
			// Resize the original bitmap to fit the new bound
			Matrix shaderMatrix = new Matrix();
			shaderMatrix.setRectToRect(mBitmapRect, mRect, Matrix.ScaleToFit.FILL);
			bitmapShader.setLocalMatrix(shaderMatrix);
			
		}
		
		@Override
		public void draw(Canvas canvas)
		{
			canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
		}
		
		@Override
		public int getOpacity()
		{
			return PixelFormat.TRANSLUCENT;
		}
		
		@Override
		public void setAlpha(int alpha)
		{
			paint.setAlpha(alpha);
		}
		
		@Override
		public void setColorFilter(ColorFilter cf)
		{
			paint.setColorFilter(cf);
		}
	}
	
	@Override
	public void loadCompletedisplay(View imageView, Bitmap bitmap, BitmapDisplayConfig config)
	{
		Drawable drawable = new RoundedDrawable(bitmap, getRoundPixels(), 0);
		switch (config.getAnimationType())
		{
			case BitmapDisplayConfig.AnimationType.fadeIn:
				fadeInDisplay(imageView, drawable);
				break;
			case BitmapDisplayConfig.AnimationType.userDefined:
				animationDisplay(imageView, drawable, config.getAnimation());
				break;
			default:
				break;
		}
		// if (imageView instanceof ImageView)
		// {
		// ((ImageView) imageView).setImageDrawable(drawable);
		// }
		// else
		// {
		// imageView.setBackgroundDrawable(drawable);
		// }
	}
	
	@Override
	public void loadFailDisplay(View imageView, Bitmap bitmap)
	{
		// TODO Auto-generated method stub
		Drawable drawable = new RoundedDrawable(bitmap, getRoundPixels(), 0);
		try
		{
			if (imageView instanceof ImageView)
			{
				((ImageView) imageView).setImageDrawable(drawable);
			}
			else
			{
				imageView.setBackgroundDrawable(drawable);
			}
		}
		catch (IllegalStateException e)
		{
			// TODO: handle exception
		}
		
	}
	
	private void fadeInDisplay(View imageView, Drawable bitmap)
	{
		try
		{
			final TransitionDrawable td = new TransitionDrawable(new Drawable[]
			{
					new ColorDrawable(android.R.color.transparent), bitmap
			});
			
			if (imageView instanceof ImageView)
			{
				((ImageView) imageView).setImageDrawable(td);
			}
			else
			{
				imageView.setBackgroundDrawable(td);
			}
			td.startTransition(200);
			
		}
		catch (IllegalStateException e)
		{
			// TODO: handle exception
		}
	}
	
	private void animationDisplay(View imageView, Drawable bitmap, Animation animation)
	{
		try
		{
			animation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
			if (imageView instanceof ImageView)
			{
				((ImageView) imageView).setImageDrawable(bitmap);
			}
			else
			{
				imageView.setBackgroundDrawable(bitmap);
			}
			imageView.startAnimation(animation);
		}
		catch (IllegalStateException e)
		{
			// TODO: handle exception
		}
	}
	
	public int getRoundPixels()
	{
		return roundPixels;
	}
	
	public void setRoundPixels(int roundPixels)
	{
		this.roundPixels = roundPixels;
	}
	
}
