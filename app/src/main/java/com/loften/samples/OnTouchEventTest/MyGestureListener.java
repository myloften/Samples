package com.loften.samples.OnTouchEventTest;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by loften on 16/7/21.
 */
public class MyGestureListener implements GestureDetector.OnGestureListener{
    private String TAG = MyGestureListener.class.getName();

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown:按下");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress:手指按下一段时间,不过还没到长按");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp:手指离开屏幕的一瞬间");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll:在触摸屏上滑动");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress:长按并且没有松开");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling:迅速滑动并松开");
        return false;
    }
}
