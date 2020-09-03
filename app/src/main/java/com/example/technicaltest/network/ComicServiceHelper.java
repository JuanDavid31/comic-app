package com.example.technicaltest.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ComicServiceHelper {

    private final static String BASE_URL = "https://comicvine.gamespot.com/api/";

    private static ComicService apiService = null;

    public static ComicService getClient(){
        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            apiService = retrofit.create(ComicService.class);
        }
        return apiService;
    }
}

