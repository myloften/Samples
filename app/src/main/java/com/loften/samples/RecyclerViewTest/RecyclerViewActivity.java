package com.loften.samples.RecyclerViewTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.loften.samples.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initView(){
        myRecyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
//        //设置布局管理器
//        myRecyclerView.setLayoutManager(layout);
//        //设置adapter
//        myRecyclerView.setAdapter(adapter);
//        //设置Item增加、移除动画
//        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加分割线
//        myRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_LIST));
//    
    }
}
