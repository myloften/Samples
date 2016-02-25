package com.loften.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.loften.samples.DivViewTest.Main2Activity;
import com.loften.samples.RecyclerViewTest.RecyclerViewActivity;

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
}
