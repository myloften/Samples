package com.loften.samples.RetrofitTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.loften.samples.R;
import com.loften.samples.RetrofitTest.bean.MovieBean;
import com.loften.samples.RetrofitTest.bean.MovieBeanReq;
import com.loften.samples.RetrofitTest.bean.RegistBean;
import com.loften.samples.RetrofitTest.bean.RegistBeanReq;
import com.loften.samples.RetrofitTest.model.MovieInfoModel;
import com.loften.samples.RetrofitTest.netApi.MovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    TextView tv_value;
    private MovieInfoModel movieInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //movieInfoModel = MovieInfoModel.getInstance(this);

        initView();
        //getInfo();
        //getMove();
        regist();
    }

    private void initView(){
        tv_value = (TextView)findViewById(R.id.tv_value);
    }

    private MovieBeanReq initParams(){
        MovieBeanReq movieBeanReq = new MovieBeanReq();
        movieBeanReq.start = 0;
        movieBeanReq.count = 10;
        return movieBeanReq;
    }

    private void getInfo(){
        //创建访问的API请求
        Call<MovieBean> call = movieInfoModel.queryMovie(initParams());
        //发送请求
        call.enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                if(response.isSuccessful()){
                    MovieBean result = response.body();
                    if(result!=null){
                        List<MovieBean.SubjectsEntity> entities = result.getSubjects();
                        tv_value.setText(entities.get(0).toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });
    }

    private void getMove(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieBean> call = movieService.getTopMovie(0,10);
        call.enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                if(response.isSuccessful()){
                    MovieBean result = response.body();
                    if(result!=null){
                        List<MovieBean.SubjectsEntity> entities = result.getSubjects();
                        tv_value.setText(entities.get(0).toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });
    }

    private String initParam(){
        RegistBeanReq rb = new RegistBeanReq();
        rb.setEmail("999999@qq.com");
        rb.setPasswd("123456");
        rb.setRepasswd("123456");
        rb.setHead("");
        String jsonstr = null;
        String jsons = JsonHelper.toJsonString(rb);
        try {
            Log.e("jj",JsonHelper.toJsonString(rb));
            jsonstr = AesUtils.Encrypt(jsons, Constant.AES_KEY);
            Log.e("jsonstr",jsonstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonstr;
    }

    private void regist(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<RegistBean> call = movieService.getTokken(initParam());
        call.enqueue(new Callback<RegistBean>() {
            @Override
            public void onResponse(Call<RegistBean> call, Response<RegistBean> response) {
                if(response.isSuccessful()){
                    RegistBean result = response.body();
                    if(result!=null){
                        tv_value.setText(result.getMessage()+"----------"+result.getOauth_token()+"---------"+result.getOauth_token_secret()+"--------"+result.getUid());
                    }

                }
            }

            @Override
            public void onFailure(Call<RegistBean> call, Throwable t) {

            }
        });
    }

}
