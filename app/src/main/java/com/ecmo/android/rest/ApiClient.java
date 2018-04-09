package com.ecmo.android.rest;



import com.ecmo.android.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit_v2New = null;
    private static Retrofit retrofit_patreferal = null;

    public static Retrofit getClient()
    {
        if (retrofit_v2New==null)
        {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS).build();


            retrofit_v2New = new Retrofit.Builder()
                    .baseUrl(Constants.Config.BASE_URL_NEW)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_v2New;
    }


    public static Retrofit getClientrefpatient()
    {
        if (retrofit_patreferal==null)
        {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS).build();


            retrofit_patreferal = new Retrofit.Builder()
                    .baseUrl(Constants.Config.BASE_URL_REFPATIENT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_patreferal;
    }

}
