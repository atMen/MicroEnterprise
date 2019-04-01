package com.spring.chanba.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 */
public class RetrofitUtil {
    private static final String NEW_BASE_URL = Constant.ROOT_URL + "/baseRest/";
    private static final int DEFAULT_TIMEOUT = 10;
    private static Retrofit mRetrofit;
    private static OkHttpClient okHttpClient;

    /**
     * 请求URL
     *
     * @return
     */
    public static ApiService initRequestURL() {
        if (mRetrofit == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置请求超时
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(NEW_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit.create(ApiService.class);
    }
}
