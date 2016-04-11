package com.loften.samples.RetrofitTest;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 网络接口服务的包装类
 * Created by Xiho on 2016/3/14.
 */
public class RetrofitWrapper {
    private static RetrofitWrapper instance;
    private Context mContext;
    private Retrofit retrofit;

    private RetrofitWrapper() {
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /**
     * 单例模式
     *
     * @return
     */
    public static RetrofitWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper.class){
                if (instance==null){
                    instance = new RetrofitWrapper();
                }
            }
        }
        return instance;
    }


    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
