package com.example.technicaltest.network;

import android.content.Context;

import com.example.technicaltest.R;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ComicServiceHelper {

    private static String API_KEY = "api_key";
    private static String FORMAT = "format";
    private static String FORMAT_VALUE = "json";

    private static ComicService apiService = null;

    public static ComicService getClient(Context context) {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getCustomClient(context))
                    .baseUrl(getBaseUrl(context))
                    .build();
            apiService = retrofit.create(ComicService.class);
        }
        return apiService;
    }

    private static OkHttpClient getCustomClient(Context context) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(API_KEY, context.getString(R.string.comic_vine_api_key))
                    .addQueryParameter(FORMAT, FORMAT_VALUE)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        return clientBuilder.build();
    }

    private static String getBaseUrl(Context context){
        return context.getString(R.string.comic_vine_api_url);
    }
}

