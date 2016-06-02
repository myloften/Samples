package com.loften.samples;

import android.app.Application;
import android.content.Context;

import com.loften.samples.RetrofitTest.config.MInterceptor;
import com.loften.samples.RetrofitTest.config.OkHttpDownLoader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by loften on 16/4/18.
 */
public class App extends Application {
    private static Context context;

    //获取全局context
    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new MInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(
                        new File(App.getContext().getFilesDir(),"mycache"),
                        1024 * 1024 * 10)).build();
        Picasso picasso = new Picasso.Builder(App.getContext()).downloader(new OkHttpDownLoader(client)).build();
        Picasso.setSingletonInstance(picasso);
    }
}
