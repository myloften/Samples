package com.loften.samples.DivViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by loften on 16/3/3.
 * 自定义view
 * Paint的写字功能
 * FontMetrics字体测量
 * Paint有一个唯一的子类TextPaint
 */
public class FontView extends View{
    private static final String TEXT = "loften坎ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    private Paint textPaint,linePaint;//文本的画笔和中心线的画笔
    private Paint.FontMetrics mFontMetrics;//文本测量对象

    private int baseX,baseY;//Baseline绘制的XY坐标

    private static final String MYTEXT = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";
    private StaticLayout mStaticLayout;//文本布局
    private TextPaint mTextPaint;//文本的画笔

    public FontView(Context context) {
        this(context,null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
//        //实例化画笔
//        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        textPaint.setTextSize(70);
//        textPaint.setColor(Color.BLACK);
//
//        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        linePaint.setStyle(Paint.Style.STROKE);
//        linePaint.setStrokeWidth(1);
//        linePaint.setColor(Color.RED);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);

//        mFontMetrics = textPaint.getFontMetrics();
//
//        Log.i("fontView","acscent:"+mFontMetrics.ascent);
//        Log.i("fontView","top:"+mFontMetrics.top);
//        Log.i("fontView","leading:"+mFontMetrics.leading);
//        Log.i("fontView","descent:"+mFontMetrics.descent);
//        Log.i("fontView","bottom:"+mFontMetrics.bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mStaticLayout = new StaticLayout(MYTEXT, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL,1.0F,0.0F,false);
        mStaticLayout.draw(canvas);
        canvas.restore();
//        //计算Baseline绘制的起点x轴坐标
//        baseX = (int)(canvas.getWidth()/2 - textPaint.measureText(TEXT)/2);
//
//        //计算Baseline绘制的Y坐标
//        baseY = (int)((canvas.getHeight()/2) - ((textPaint.descent() + textPaint.ascent())/2));
//
//        canvas.drawText(TEXT,baseX,baseY,textPaint);
//
//        canvas.drawLine(0,canvas.getHeight()/2,canvas.getWidth(),canvas.getHeight()/2,linePaint);
    }
}
