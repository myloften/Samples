package com.loften.samples.OnTouchEventTest;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.loften.samples.R;

public class OnTouchEventActivity extends AppCompatActivity implements View.OnTouchListener{

    private ImageView img_test;

    //缩放控制
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    //不同状态的表示：
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    //定义第一个按下的点，两个接触点的中点，以及初始两点的的距离
    private PointF startPoint = new PointF();
    private PointF midPoint = new PointF();
    private float oriDis = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch_event);

        img_test = (ImageView) this.findViewById(R.id.img_test);
        img_test.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView img = (ImageView)v;
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            //单指
            case MotionEvent.ACTION_DOWN:
                matrix.set(img.getImageMatrix());
                savedMatrix.set(matrix);
                startPoint.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            //双指
            case MotionEvent.ACTION_POINTER_DOWN:
                oriDis = distance(event);
                if(oriDis > 10f){
                    savedMatrix.set(matrix);
                    midPoint = middle(event);
                    mode = ZOOM;
                }
                break;
            //手指放开
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            //单指滑动事件
            case MotionEvent.ACTION_MOVE:
                if(mode == DRAG){
                    //是一个手指拖动
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
                }else if(mode == ZOOM){
                    //两个手指滑动
                    float newDist = distance(event);
                    if(newDist > 10f){
                        matrix.set(savedMatrix);
                        float scale = newDist / oriDis;
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
        }
        //设置ImageView的Matrix
        img.setImageMatrix(matrix);
        return true;
    }

    //计算两个触摸点之间的距离
    private float distance(MotionEvent event){
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    //计算两个触摸点的中点
    private PointF middle(MotionEvent event){
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2 );
    }
}
