package com.example.technicaltest.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.database.MovieDao;
import com.example.technicaltest.database.MovieDataBase;
import com.example.technicaltest.network.ComicServiceHelper;
import com.example.technicaltest.network.property.MovieProperty;
import com.example.technicaltest.network.property.ResponseProperty;
import com.example.technicaltest.util.MapperUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    public enum LoadingResult{ SUCCESS, ERROR}

    private MovieDao movieDao;
    private MutableLiveData<List<Movie>> moviesLiveData;

    public MovieRepository(MovieDataBase db, MutableLiveData<List<Movie>> moviesLiveData){
        movieDao = db.movieDao();
        this.moviesLiveData = moviesLiveData;
    }

    public LiveData<LoadingResult> refreshVideos(){
        MutableLiveData loadingResult = new MutableLiveData();
        ComicServiceHelper.getClient().getMovies().enqueue(new Callback<ResponseProperty>() {
            @Override
            public void onResponse(Call<ResponseProperty> call, Response<ResponseProperty> response) {
                ResponseProperty responseProperty = response.body();
                List<MovieProperty> movieProperties = responseProperty.movies;
                List<Movie> movies = MapperUtils.mapMoviePropertiesToMovie(movieProperties);
                loadingResult.setValue(LoadingResult.SUCCESS);
                moviesLiveData.setValue(movies);
            }

            @Override
            public void onFailure(Call<ResponseProperty> call, Throwable t) {
                Log.e("MovieVideoRepository", "Failure: " + t.getMessage());
                loadingResult.setValue(LoadingResult.SUCCESS);
            }
        });

        return loadingResult;
    }
}