package com.loften.samples.DivViewTest;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.loften.samples.R;
import com.loften.samples.Utils.DisplayUtils;

/**
 * Created by loften on 16/2/24.
 * 自定义View(画圆并添加动画)
 */
public class MyView extends View implements Runnable{

    private Paint mPaint;
    private Context mContext;//上下文环境引用

    private int radiu;//圆环半径

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        //获取自定义属性
//        mPaint = new Paint();
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
//        mPaint.setColor(a.getColor(R.styleable.MyView_textColor,0x990000FF));
//        mPaint.setTextSize(a.getDimension(R.styleable.MyView_textSize, 30));
//        a.recycle();

        //初始化画笔
        initPaint();
    }

    private void initPaint(){

        //实例化画笔并打开锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*
        * 画笔样式
        * 1.Paint.Style.STROKE:描边
        * 2.Paint.Sytle.FILL_AND_STROKE:描边并填充
        * 3.Paint.Style.FILL:填充
        * */
        mPaint.setStyle(Paint.Style.STROKE);
        //设置画笔为浅灰色
        mPaint.setColor(Color.LTGRAY);
        //设置描边的粗细，单位：px
        //注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画矩形
        //canvas.drawRect(new Rect(10,10,300,300),mPaint);

        //绘制圆环
        canvas.drawCircle(DisplayUtils.getScreenW((Activity)mContext)/2,
                DisplayUtils.getScreenH((Activity)mContext)/2,
                radiu,mPaint);
    }

    /*
    * 添加线程动画 使圆放大后恢复，一直来来往往
    * */
    @Override
    public void run() {
        //确保线程不断执行不断刷新界面
        while (true){
            try{
                //如果半径小于200则自加否则大于200重置半径值以实现往复
                if(radiu <= 200){
                    radiu += 10;

                    //刷新view
                    postInvalidate();
                }else{
                    radiu = 0;
                }

                //每执行一次暂停40毫秒
                Thread.sleep(40);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
