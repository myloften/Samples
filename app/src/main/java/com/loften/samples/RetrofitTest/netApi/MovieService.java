package com.loften.samples.RetrofitTest.netApi;

import com.loften.samples.RetrofitTest.bean.MovieBean;
import com.loften.samples.RetrofitTest.bean.RegistBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by loften on 16/4/3.
 */
public interface MovieService {
    @GET("top250")
    Call<MovieBean> getTopMovie(@Query("start") int start,@Query("count") int count);

    @FormUrlEncoded
    @POST("index.php?app=api&mod=Oauth&act=regist")
    Call<RegistBean> getTokken(@Field("cont") String cont);
}
