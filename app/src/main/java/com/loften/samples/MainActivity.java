package com.loften.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.loften.samples.CombinationImageView.CombinationImageActivity;
import com.loften.samples.Component.CanvasActivity;
import com.loften.samples.ConfigurationTest.ConfigurationActivity;
import com.loften.samples.DivViewTest.Main2Activity;
import com.loften.samples.LoopViewPager.LoopViewPagerActivity;
import com.loften.samples.OnTouchEventTest.GestureListenerActivity;
import com.loften.samples.OnTouchEventTest.OnTouchEventActivity;
import com.loften.samples.RecyclerViewTest.RecyclerViewActivity;
import com.loften.samples.RetrofitTest.RetrofitActivity;
import com.loften.samples.ScrollerTest.ScrollerTestActivity;
import com.loften.samples.SwipeToLoadLayout.SwipeToLoadLayoutActivity;
import com.loften.samples.VerificationCode.VerificationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //RecyclerView使用
    public void recyclerViewTest(View v){
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    //自定义view
    public void divViewTest(View v){
        startActivity(new Intent(this, Main2Activity.class));
    }

    //Retrofit使用
    public void myRetrofitTest(View v){
        startActivity(new Intent(this, RetrofitActivity.class));
    }

    //canvas的api使用
    public void myComponentTest(View v){
        startActivity(new Intent(this, CanvasActivity.class));
    }

    //仿微信群组头像
    public void combinationImageTest(View v){
        startActivity(new Intent(this, CombinationImageActivity.class));
    }

    //验证码
    public void verificationCodeTest(View v){
        startActivity(new Intent(this, VerificationActivity.class));
    }

    //单指拖动，多指缩放
    public void onTouchEventTest(View v){
        startActivity(new Intent(this, OnTouchEventActivity.class));
    }

    //获取Configuration信息
    public void configurationTest(View v){
        startActivity(new Intent(this, ConfigurationActivity.class));
    }

    //手势
    public void gestureListenerTest(View v){
        startActivity(new Intent(this, GestureListenerActivity.class));
    }

    //scroller的用法
    public void scrollerTest(View v){
        startActivity(new Intent(this, ScrollerTestActivity.class));
    }

    //swipeToLayout刷新加载框架使用
    public void swipeToLoadLayoutTest(View v){
        startActivity(new Intent(this, SwipeToLoadLayoutActivity.class));
    }

    //LoopViewPager广告栏的应用
    public void loopViewPagerTest(View v){
        startActivity(new Intent(this, LoopViewPagerActivity.class));
    }
}
