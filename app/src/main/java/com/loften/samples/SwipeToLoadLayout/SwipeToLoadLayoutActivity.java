package com.loften.samples.SwipeToLoadLayout;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.loften.samples.App;
import com.loften.samples.R;
import com.loften.samples.RecyclerViewTest.RVCommonAdapter;
import com.loften.samples.RecyclerViewTest.ViewHolder;
import com.loften.samples.RetrofitTest.Constant;
import com.loften.samples.RetrofitTest.bean.GrilBean;
import com.loften.samples.RetrofitTest.config.MInterceptor;
import com.loften.samples.RetrofitTest.netApi.GrilService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SwipeToLoadLayoutActivity extends AppCompatActivity  implements OnRefreshListener, OnLoadMoreListener {

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;

    private RVCommonAdapter<GrilBean.NewslistEntity> sAdapter;
    List<GrilBean.NewslistEntity> data = new ArrayList<>();
    private int page;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_load_layout);

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new MInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(
                        new File(App.getContext().getCacheDir().getAbsolutePath(),"mycache"),
                        1024 * 1024 * 100)).build();
        Log.e("path",App.getContext().getCacheDir().getAbsolutePath());
        initView();
        initRefreash();
    }

    private void initView(){
        swipeToLoadLayout = (SwipeToLoadLayout)findViewById(R.id.swipeToLoadLayout);
        recyclerView = (RecyclerView)findViewById(R.id.swipe_target);
    }

    private void initRefreash(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sAdapter);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)){
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
        swipeToLoadLayout.setRefreshing(true);
    }

    private void getGrilInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GrilService movieService = retrofit.create(GrilService.class);
        Call<GrilBean> call = movieService.getGril("cd4ed170e43c273987b81b356ed8322e",10);
        call.enqueue(new Callback<GrilBean>() {
            @Override
            public void onResponse(Call<GrilBean> call, final Response<GrilBean> response) {
                swipeToLoadLayout.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            GrilBean result = response.body();
                            for(int i=0;i<result.getNewslist().size();i++) {
                                data.add(result.getNewslist().get(i));
                            }
                            if(data.size()<=15||sAdapter==null) {
                                sAdapter = new RVCommonAdapter<GrilBean.NewslistEntity>(SwipeToLoadLayoutActivity.this, R.layout.item_recycler_view, data) {

                                    @Override
                                    public void convert(ViewHolder holder, GrilBean.NewslistEntity newslistEntity) {
                                        holder.setUrl(R.id.rv_iv, newslistEntity.getPicUrl());
                                        holder.setText(R.id.rv_value, "标题：" + newslistEntity.getTitle() + "\n 时间："
                                                + newslistEntity.getCtime() + "\n 描述：" + newslistEntity.getDescription());
                                    }
                                };

                                recyclerView.setAdapter(sAdapter);
                            }else {
                                sAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                },3000);

            }

            @Override
            public void onFailure(Call<GrilBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadMore() {
        getGrilInfo();
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        getGrilInfo();
        swipeToLoadLayout.setRefreshing(false);
    }
}
