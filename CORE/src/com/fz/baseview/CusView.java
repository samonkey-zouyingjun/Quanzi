
package com.fz.baseview;

import android.graphics.Color;

/**
 * simple introduction
 * 
 * <p>
 * detailed comment
 * 
 * @author cate 2015-10-9 下午5:29:22
 */

public interface CusView
{
	final static float NO_RADIUS = -1;
	
	static final int DEFAULT_BACKGROUNDCOLOR = Color.parseColor("#D8D8D8");
	int mPressColor = DEFAULT_BACKGROUNDCOLOR;
	int mNormalColor = DEFAULT_BACKGROUNDCOLOR;
	int mUnableColor = DEFAULT_BACKGROUNDCOLOR;
	float leftTopRadius = 0;
	float leftBottomRadius = 0;
	float rightTopRadius = 0;
	float rightBottomRadius = 0;
	
	
}
