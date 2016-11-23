package com.fz.baseview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author cate 2014-12-20 下午2:33:54
 */

public class NoScrollListView extends ListView {

	public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
//		if (getAdapter() != null) {
//			expandSpec = expandSpec + getDividerHeight()
//					* getAdapter().getCount();
//
//		}

		super.onMeasure(widthMeasureSpec, expandSpec);

//		ListAdapter listAdapter = getAdapter();
//		if (listAdapter == null)
//			return;
//
//		int desiredWidth = MeasureSpec.makeMeasureSpec(getWidth(),
//				MeasureSpec.UNSPECIFIED);
//		int totalHeight = 0;
//		View view = null;
//		for (int i = 0; i < listAdapter.getCount(); i++) {
//			view = listAdapter.getView(i, view, this);
//			if (i == 0)
//				view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth,
//						LayoutParams.WRAP_CONTENT));
//
//			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
//			totalHeight += view.getMeasuredHeight();
//		}
////		ViewGroup.LayoutParams params = getLayoutParams();
////		params.height = totalHeight
////				+ (getDividerHeight() * (listAdapter.getCount() - 1));
////		setLayoutParams(params);
////		requestLayout();
//		
//		 int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//		setMeasuredDimension(widthSize , totalHeight
//				+ (getDividerHeight() * (listAdapter.getCount() - 1)));

	}

}
