package com.loften.samples.DivViewTest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.loften.samples.R;
import com.loften.samples.Utils.DisplayUtils;

/**
 * Created by loften on 16/3/4.
 * 自定义View之Shader的使用
 */
public class ShaderView extends View {

    private static final int RECT_SIZE = 400;//矩形尺寸的一半

    private Paint mPaint;//画笔

    private int left,top,right,bottom;//矩形左上右下坐标



    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取屏幕中点坐标
        int screenX = DisplayUtils.getScreenW((Activity)context)/2;
        int screenY = DisplayUtils.getScreenH((Activity)context)/2;

        //计算矩形左上右下坐标值
        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        //实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);

        //获取位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);

        //设置着色器
        //mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));
        //mPaint.setShader(new LinearGradient(left,top,right,bottom,Color.RED, Color.YELLOW,Shader.TileMode.REPEAT));
        //mPaint.setShader(new LinearGradient(left, top, right - RECT_SIZE, bottom - RECT_SIZE, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT));
        //mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, new float[] { 0, 0.1F, 0.5F, 0.7F, 0.8F }, Shader.TileMode.MIRROR));
        //mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, null, Shader.TileMode.MIRROR));
        //mPaint.setShader(new SweepGradient(screenX,screenY,Color.RED,Color.YELLOW));
        mPaint.setShader(new SweepGradient(screenX,screenY,new int[]{Color.GREEN,Color.WHITE,Color.GREEN},null));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制矩形
        canvas.drawRect(left,top,right,bottom,mPaint);
    }
}
