package com.loften.samples.DivViewTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loften.samples.R;

import java.util.ArrayList;

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

        MyArc au = (MyArc)findViewById(R.id.apiUser);
        PieData pd = new PieData("num1",30);
        PieData pd2 = new PieData("num2",40);
        PieData pd3 = new PieData("num2",60);
        PieData pd4 = new PieData("num2",80);
        PieData pd5 = new PieData("num2",10);
        PieData pd6 = new PieData("num2",40);
        ArrayList<PieData> al= new ArrayList<PieData>();
        al.add(pd);
        al.add(pd2);
        al.add(pd3);
        al.add(pd4);
        al.add(pd5);
        al.add(pd6);

        au.setData(al);
    }
}
