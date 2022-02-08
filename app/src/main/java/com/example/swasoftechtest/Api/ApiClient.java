package com.example.swasoftechtest.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {


        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit == null) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build();
                ////////////////////////////////////////////////////
                retrofit = new Retrofit.Builder()
                        .baseUrl(ApplicationConstant.INSTANCE.baseUrl)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
            return retrofit;

        }
    }


