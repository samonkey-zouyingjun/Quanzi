package com.example.zouyingjun.quanzi.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project: samworld-app1
 * Author:  Saker Jing
 * Date:    2016/9/2
 */
public class RetrofitUtils {
    public static OkHttpClient getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("userId", "DDE259F53E6121381E33940098C27AE5")
                                .addHeader("deviceType", "ANDROID")
                                .addHeader("version", "v3.1.4")
//                              .addHeader("user-agent", "Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return okHttpClient;
    }
}
