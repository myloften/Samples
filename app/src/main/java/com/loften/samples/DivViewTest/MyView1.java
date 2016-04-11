package com.loften.samples.DivViewTest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import com.loften.samples.R;
import com.loften.samples.Utils.BitmapUtil;
import com.loften.samples.Utils.DisplayUtils;

/**
 * Created by loften on 16/2/26.
 * 自定义view（）
 * 添加图片资源
 * Paint 设置颜色过滤的几种
 * ColorMatrixColorFilter，LightingColorFilter，PorterDuffColorFilter（主要给图片颜色过滤）
 */
public class MyView1 extends View {

    private Paint mPaint;//画笔
    private Context mContext;//上下文
    private Bitmap bitmap;//位图
    private int x,y;//位图绘制时左上角的起点坐标

    public MyView1(Context context) {
        this(context,null);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //初始化画笔
        initPaint();
        //初始化资源
        initRes(context);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        //添加锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        //设置样式，填充
//        mPaint.setStyle(Paint.Style.FILL);
//        //设置颜色
//        mPaint.setColor(Color.argb(255,255,128,103));
//        //设置描边的宽度
//        mPaint.setStrokeWidth(10);
//
//        /*
//        * 设置画笔ColorMatrixColorFilter（色彩矩阵颜色过滤器）
//        */
        //1.生成色彩矩阵，从而进行过滤
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.33f, 0.59f, 0.11f, 0, 0,
//                0.33f, 0.59f, 0.11f, 0, 0,
//                0.33f, 0.59f, 0.11f, 0, 0,
//                0, 0, 0, 1, 0,
//        });
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //2.设置颜色过滤
        //mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));
        //3.设置颜色过滤
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
    }

    /**
     * 初始化资源
     */
    private void initRes(Context context){
        //获取位图
        //bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.hongfa);
        bitmap = BitmapUtil.getBitmapFromResource(context.getResources(),R.drawable.hongfa,200,200);
        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = DisplayUtils.getScreenW((Activity)context)/2 - bitmap.getWidth()/2;
        y = DisplayUtils.getScreenH((Activity) context)/2 - bitmap.getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //绘制圆
//        canvas.drawCircle(DisplayUtils.getScreenW((Activity)mContext)/2,
//                DisplayUtils.getScreenH((Activity)mContext)/2,
//                200,mPaint);

        canvas.drawBitmap(bitmap,x,y,mPaint);
    }
}
