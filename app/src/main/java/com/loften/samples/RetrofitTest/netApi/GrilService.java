package com.loften.samples.RetrofitTest.netApi;

import com.loften.samples.RetrofitTest.bean.GrilBean;
import com.loften.samples.RetrofitTest.bean.MovieBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by loften on 16/4/18.
 */
public interface GrilService {

    @GET("txapi/mvtp/meinv")
    Call<GrilBean> getGril(@Header("apikey") String key,@Query("num") int num);
}
