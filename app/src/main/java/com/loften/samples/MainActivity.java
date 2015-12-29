package com.loften.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loften.samples.RecyclerViewTest.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //RecyclerView使用
    private void recyclerViewTest(){
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }
}
