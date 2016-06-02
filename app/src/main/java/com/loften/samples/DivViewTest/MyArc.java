package com.loften.samples.DivViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by loften on 16/4/15.
 * 圆形占百分比
 */
public class MyArc extends View{

    private int[] mColors = {
            0xFFCCFF00,0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00
    };

    private float mStartAngle = 0;
    private int mWidth,mHeight;
    private ArrayList<PieData> mData;
    Paint mPaint;

    public MyArc(Context context) {
        this(context,null);
    }

    public MyArc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null == mData)
            return;

        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth/2,mHeight/2);
        float r = (float)(Math.min(mWidth,mHeight)/2*0.8);
        RectF rectF = new RectF(-r,-r,r,r);

        for(int i=0;i<mData.size();i++){
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF,currentStartAngle,pie.getAngle(),true,mPaint);
            currentStartAngle += pie.getAngle();
        }
    }

    public void setStartAngle(int startAngle){
        this.mStartAngle = startAngle;
        invalidate();
    }

    public void setData(ArrayList<PieData> mData){
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    private void initData(ArrayList<PieData> mData){
        if(mData == null || mData.size() == 0){
            return;
        }

        float sumValue = 0;
        for(int i = 0; i<mData.size();i++){
            PieData pie = mData.get(i);

            sumValue += pie.getValue();

            int j = i % mColors.length;
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for(int i = 0;i<mData.size();i++){
            PieData pieData = mData.get(i);
            float percentage = pieData.getValue()/sumValue;
            float angle = percentage*360;

            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
            sumAngle += angle;
            Log.e("angle","" + pieData.getAngle());
        }
    }
}
