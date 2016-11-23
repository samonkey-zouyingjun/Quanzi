package com.example.zouyingjun.quanzi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RadioGroup;

/**
 * Created by j on 2016/11/14.
 */

public class TabGroup extends RadioGroup {

    private float varwith,with;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TabGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取控件的宽
        with = w;

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.save();

        paint.setColor(Color.parseColor("#FE4545"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        Path p = new Path();
        p.moveTo(varwith,getHeight());

        p.lineTo(varwith+with*1.0f/getChildCount(),getHeight());

        canvas.drawPath(p,paint);
        canvas.restore();
    }

    /**更新方法*/
    public void updata(int position,float offset){
        //修改varwith
        varwith = (with*1.0f/getChildCount())*(position+offset);

        //回调dispathDraw
        invalidate();


    }
}
