package com.example.technicaltest.network;

import com.example.technicaltest.network.property.ResponseProperty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ComicService {
    @GET("movies/?api_key=603aa41c30f8420b9d4fa00584acf281ddca30ef&format=json")
    Call<ResponseProperty> getMovies();
}
