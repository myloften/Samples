package com.loften.samples.RecyclerViewTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loften.samples.App;
import com.loften.samples.R;
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

public class RecyclerViewActivity extends AppCompatActivity {

    private XRecyclerView sRecyclerView;
    private RVCommonAdapter<GrilBean.NewslistEntity> sAdapter;
    List<GrilBean.NewslistEntity> data = new ArrayList<GrilBean.NewslistEntity>();
    private int page;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new MInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(
                        new File(App.getContext().getCacheDir().getAbsolutePath(),"mycache"),
                        1024 * 1024 * 100)).build();
        Log.e("path",App.getContext().getCacheDir().getAbsolutePath());
        initView();
        getGrilInfo();
        initRefreash();
    }

    /**
     * 初始化RecyclerView
     */
    private void initView(){
        sRecyclerView = (XRecyclerView)findViewById(R.id.myrecyclerview);
    }

    /**
     * 初始化下拉组件
     */
    private void initRefreash() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sRecyclerView.setLayoutManager(layoutManager);

        sRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        sRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        sRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        sRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                getGrilInfo();
            }
        });



    }

    private void getGrilInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GrilService movieService = retrofit.create(GrilService.class);
        Call<GrilBean> call = movieService.getGril("cd4ed170e43c273987b81b356ed8322e",12);
        call.enqueue(new Callback<GrilBean>() {
            @Override
            public void onResponse(Call<GrilBean> call, Response<GrilBean> response) {
                if (response.isSuccessful()) {
                    GrilBean result = response.body();
                    for(int i=0;i<result.getNewslist().size();i++) {
                        data.add(result.getNewslist().get(i));
                    }
                    if(data.size()<=15||sAdapter==null) {
                        sAdapter = new RVCommonAdapter<GrilBean.NewslistEntity>(RecyclerViewActivity.this, R.layout.item_recycler_view, data) {

                            @Override
                            public void convert(ViewHolder holder, GrilBean.NewslistEntity newslistEntity) {
                                holder.setUrl(R.id.rv_iv, newslistEntity.getPicUrl());
                                holder.setText(R.id.rv_value, "标题：" + newslistEntity.getTitle() + "\n 时间："
                                        + newslistEntity.getCtime() + "\n 描述：" + newslistEntity.getDescription());
                            }
                        };

                        sRecyclerView.setAdapter(sAdapter);
                    }else {
                        sAdapter.notifyDataSetChanged();
                        sRecyclerView.refreshComplete();
                    }
                }
            }

            @Override
            public void onFailure(Call<GrilBean> call, Throwable t) {

            }
        });
    }
}
