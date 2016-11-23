package com.fz.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * 
 * @author cate
 *2015-3-9
 */
public class TextStyleUtils {

	private SpannableString spannableString;

	public TextStyleUtils() {
	}
	
	public TextStyleUtils(String str) {
		spannableString = new SpannableString(str);
	}
	
	public void setString(String str) {
		spannableString = new SpannableString(str);
	}
	
	public SpannableString getSpannableString() {
		return spannableString;
	}

	/**
	 * 设置字体大小（绝对值,单位：默认为像素，如果最后一个参数dp为true，则字体大小为dp）  
	 * @param size
	 * @param start
	 * @param end
	 * @param dp   如果为true，表示前面的字体大小单位为dip，否则为像素
	 * @return
	 */
	public TextStyleUtils setAbsoluteSize(int size,int start,int end,boolean dp) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new AbsoluteSizeSpan(size, dp), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
		return this;
	}
	/**
	 * 设置字体大小（相对值, 0.5f表示默认字体大小的一半）  
	 * @param size
	 * @param start
	 * @param end
	 * @param dp
	 * @return
	 */
	public TextStyleUtils setRelativeSize(float size,int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new RelativeSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 0.5f表示默认字体大小的一半
		return this;
	}
	
	/**
	 * 设置字体前景色
	 * @param Color
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setForegroundColor(int Color,int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new ForegroundColorSpan(Color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		return this;
	}
	
	//设置字体背景色  
	public TextStyleUtils setBackgroundColor(int Color,int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new BackgroundColorSpan(Color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为洋红色
		return this;
	}
	
	public static void setFakeBold(TextView textView,boolean isBold) {
		TextPaint tp = textView.getPaint();
		tp.setFakeBoldText(isBold);
	}
	
	/**
	 * 粗体
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setBold(int start,int end){
		return setStyleSpan(android.graphics.Typeface.BOLD, start, end);
	}
	/**
	 * 斜体  
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setItalic(int start,int end){
		return setStyleSpan(android.graphics.Typeface.ITALIC, start, end);
	}
	/**
	 * 粗斜体   
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setBoldItalic(int start,int end){
		return setStyleSpan(android.graphics.Typeface.BOLD_ITALIC, start, end);
	}
	
	public  TextStyleUtils setStyleSpan(int style ,int start,int end){
		spannableString.setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return this;
	}
	
	
	/**
	 * 设置下划线   
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setUnderlineSpan(int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为洋红色
		return this;
	}
	/**
	 * 设置删除线
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setStrikethroughSpan(int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为洋红色
		return this;
	}
	
	/**
	 * 设置下标
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setSubscriptSpan(int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		return this;
	}
	/**
	 * 设置上标
	 * @param start
	 * @param end
	 * @return
	 */
	public TextStyleUtils setSuperscriptSpan(int start,int end) {
		if (spannableString == null) {
			return this;
		}
		spannableString.setSpan(new SuperscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		return this;
	}
}
