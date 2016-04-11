package com.loften.samples.DivViewTest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.loften.samples.R;
import com.loften.samples.Utils.BitmapUtil;
import com.loften.samples.Utils.DisplayUtils;

/**
 * Created by loften on 16/2/27.
 * 自定义view
 * paint setXfermmode 过渡模式、图像混合模式
 * AvoidXfermode（api16过期）  PixelXorXfermode（api16过期）  PorterDuffXfermode（至今）
 * 这里主要写PorterDuffXfermode,通过PorterDuffXfermode(PorterDuff.Mode mode)实现图像混合
 */
public class MyView2 extends View{

    private Paint mPaint;//画笔
    private Bitmap bitmap;//位图
    private PorterDuffXfermode porterDuffXfermode;//图形混合模式

    int x,y;//位图绘制时左上角的起点坐标
    private int screenW,screenH;//屏幕尺寸

    public MyView2(Context context) {
        this(context,null);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);

        //实例化混合模式  滤色模式
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        //初始化画笔
        initPaint();
        //初始化资源
        initRes(context);

    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        //添加锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    /**
     * 初始化资源
     */
    private void initRes(Context context){
        //获取位图
        //bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.hongfa);
        bitmap = BitmapUtil.getBitmapFromResource(context.getResources(), R.drawable.hongfa, 200, 200);

        screenW = DisplayUtils.getScreenW((Activity) context);
        screenH = DisplayUtils.getScreenH((Activity) context);
        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = screenW/2 - bitmap.getWidth()/2;
        y = screenH/2 - bitmap.getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        /**
         * 将绘制操作保存到新的图层（官方说法为离屏缓存）
         */
        int sc = canvas.saveLayer(0,0,screenW,screenH,null,Canvas.ALL_SAVE_FLAG);

        //先绘制一层颜色
        canvas.drawColor(0xcc1c093e);

        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);

        //再绘制src源图
        canvas.drawBitmap(bitmap,x,y,mPaint);

        //还原混合模式
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);
    }
}
