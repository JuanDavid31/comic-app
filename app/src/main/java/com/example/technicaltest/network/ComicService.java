package com.example.technicaltest.network;

import com.example.technicaltest.network.property.ResponseProperty;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ComicService {
    @GET("movies/")
    Call<ResponseProperty> getMovies();
}
