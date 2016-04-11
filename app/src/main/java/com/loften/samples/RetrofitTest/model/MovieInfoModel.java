package com.loften.samples.RetrofitTest.model;

import android.content.Context;

import com.loften.samples.RetrofitTest.RetrofitWrapper;
import com.loften.samples.RetrofitTest.bean.MovieBean;
import com.loften.samples.RetrofitTest.bean.MovieBeanReq;
import com.loften.samples.RetrofitTest.netApi.MovieService;

import retrofit2.Call;

/**
 * Created by loften on 16/4/3.
 */
public class MovieInfoModel {
    private static MovieInfoModel movieInfoModel;
    private MovieService movieService;

    /**
     * 单例模式
     *
     * @return
     */
    public static MovieInfoModel getInstance(Context context){
        if(movieInfoModel == null){
            movieInfoModel = new MovieInfoModel(context);
        }
        return movieInfoModel;
    }

    private MovieInfoModel(Context context){
        movieService = (MovieService) RetrofitWrapper.getInstance().create(MovieBean.class);
    }

    /**
     * 查询影片
     */
    public Call<MovieBean> queryMovie(MovieBeanReq movieBeanReq){
        Call<MovieBean> movieBeanCall = movieService.getTopMovie(movieBeanReq.start,movieBeanReq.count);
        return movieBeanCall;
    }
}
