package com.cralos.myapplicationlogin.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitInstance = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(45, TimeUnit.SECONDS)
                    .connectTimeout(45, TimeUnit.SECONDS)
                    .build();
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(EndPoints.URL_SERVER)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

}
