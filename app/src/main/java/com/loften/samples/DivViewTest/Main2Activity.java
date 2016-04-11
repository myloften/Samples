package com.loften.samples.DivViewTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loften.samples.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initViews();
    }

    private void initViews(){
        MyTextView mtv = (MyTextView)findViewById(R.id.myTextView);
        Log.i("MyTextView", "top:" + mtv.getTop() + " left:" + mtv.getLeft() + " bottom:" + mtv.getBottom() + " right:" + mtv.getRight());

//        //自定义view
//        MyView myView = (MyView)findViewById(R.id.myView);
//        //开线程
//        new Thread(myView).start();


    }
}
