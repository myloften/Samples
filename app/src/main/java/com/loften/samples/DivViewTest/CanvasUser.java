package com.loften.samples.DivViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by loften on 16/4/15.
 */
public class CanvasUser extends View{

    private Paint mPaint;
    private int mWidth,mHeight;

    public CanvasUser(Context context) {
        this(context,null);
    }

    public CanvasUser(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasUser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);
        RectF rectF = new RectF(0,0,100,100);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF,mPaint);
        canvas.rotate(180);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);



    }
}
