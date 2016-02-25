package com.loften.samples.DivViewTest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.loften.samples.R;

/**
 * Created by loften on 16/2/24.
 * 自定义View
 */
public class MyView extends View{

    private Paint mPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mPaint.setColor(a.getColor(R.styleable.MyView_textColor,0x990000FF));
        mPaint.setTextSize(a.getDimension(R.styleable.MyView_textSize, 30));
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect(10,10,300,300),mPaint);
    }
}
