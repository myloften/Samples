package com.loften.samples.RetrofitTest.config;

import com.loften.samples.RetrofitTest.netApi.MovieService;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by loften on 16/4/3.
 * Retrofit2+OkHttp3下统一设置Http请求的Headers
 */
public class RetrofitAPIManager {

    public static final String SERVER_URL = "url";

//    public static ClientAPI provideClientApi() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(SERVER_URL)
//                .client(genericClient())
//                .build();
//        return retrofit.create(ClientAPI.class);
//    }

    private static Retrofit stringRetrofit = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(genericClient())
            .build();

    private static MovieService getStringService(){
        MovieService service = stringRetrofit.create(MovieService.class);
        return service;
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "*/*")
                                .addHeader("Set-Cookie", "android framework request")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
//    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
//        Request request = chain.request();
//        if(!NetUtils.hasNetwork(context)){
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//            Logger.t(TAG).w("no network");
//        }
//        Response originalResponse = chain.proceed(request);
//        if(NetUtils.hasNetwork(context)){
//            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//            String cacheControl = request.cacheControl().toString();
//            return originalResponse.newBuilder()
//                    .header("Cache-Control", cacheControl)
//                    .removeHeader("Pragma")
//                    .build();
//        }else{
//            return originalResponse.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
//                    .removeHeader("Pragma")
//                    .build();
//        }
//    };

    //服务器不支持
}
