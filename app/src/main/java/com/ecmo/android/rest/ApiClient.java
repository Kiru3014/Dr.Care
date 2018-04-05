package com.ecmo.android.rest;



import com.ecmo.android.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


public class ApiClient {

    private static Retrofit retrofit_v2New = null;
    private static Retrofit retrofit_v2_create_order = null;

    public static Retrofit getnewV2Client(String countrycode)
    {
        if (retrofit_v2New==null)
        {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("mpwuyxjlcn7ussqh0s1q8e3dp32hyiy9", "rwwe41ddowolt310ly0ct8r39w0vprv6");
            consumer.setTokenWithSecret("iw9unb8rixqk8ce57r22esn6a7b96xca", "kuiyhnngyjvgodtphgjwspexu2g7gwrw");

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new SigningInterceptor(consumer))
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

}
