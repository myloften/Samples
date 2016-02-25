package com.loften.samples.DivViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.loften.samples.R;

/**
 * Created by loften on 16/2/23.
 * 通过继承TextView实现自定义view
 */
public class MyTextView extends TextView{

    Paint mPaint1;
    Paint mPaint2;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint1 = new Paint();
        mPaint1.setColor(ContextCompat.getColor(context,R.color.colorPrimary));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    /**
     * 计算宽度
     */
    private int measureWidth(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);//获取测量模式EXACTLY,AT_MOST,UNSPECIFIED
        int specSize = MeasureSpec.getSize(measureSpec);//获取大小
        Log.i("MyTextView","specSizeWidth:"+specSize);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = 200;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return result;
    }

    /**
     * 计算高度
     */
    private int measureHeight(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);//获取测量模式EXACTLY,AT_MOST,UNSPECIFIED
        int specSize = MeasureSpec.getSize(measureSpec);//获取大小
        Log.i("MyTextView","specSizeHeight:"+specSize);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = 100;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        canvas.save();
        canvas.translate(10,0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
