package com.loften.samples.OnTouchEventTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.loften.samples.R;

/**
 *
 * Step 1: 创建GestureDetector对象，创建时需实现GestureListener传入
 * Step 2: 将Activity或者特定组件上的TouchEvent的事件交给GestureDetector处理
 */
public class GestureListenerActivity extends AppCompatActivity {

    private MyGestureListener myGestureListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_listener);
        //实例化GestureListener与GestureDetector对象
        myGestureListener = new MyGestureListener();
        mDetector = new GestureDetector(this, myGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
}
