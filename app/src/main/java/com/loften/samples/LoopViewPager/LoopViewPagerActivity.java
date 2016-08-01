package com.loften.samples.LoopViewPager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class LoopViewPagerActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private LinearLayout indicators;
    private LoopViewPagerAdapter mPagerAdapter;

    OkHttpClient client;

    List<GrilBean.NewslistEntity> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_view_pager);

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new MInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(
                        new File(App.getContext().getCacheDir().getAbsolutePath(),"mycache"),
                        1024 * 1024 * 100)).build();
        Log.e("path",App.getContext().getCacheDir().getAbsolutePath());

        initView();
        getGrilInfo();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicators = (LinearLayout) findViewById(R.id.indicators);
    }

    private void getGrilInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GrilService movieService = retrofit.create(GrilService.class);
        Call<GrilBean> call = movieService.getGril("cd4ed170e43c273987b81b356ed8322e",5);
        call.enqueue(new Callback<GrilBean>() {
            @Override
            public void onResponse(Call<GrilBean> call, Response<GrilBean> response) {
                if (response.isSuccessful()) {
                    GrilBean result = response.body();
                    for(int i=0;i<result.getNewslist().size();i++) {
                        data.add(result.getNewslist().get(i));
                    }

                    mPagerAdapter = new LoopViewPagerAdapter(viewPager,indicators);
                    viewPager.setAdapter(mPagerAdapter);
                    viewPager.addOnPageChangeListener(mPagerAdapter);
                    mPagerAdapter.setList(result.getNewslist());
                }
            }

            @Override
            public void onFailure(Call<GrilBean> call, Throwable t) {

            }
        });
    }

}
