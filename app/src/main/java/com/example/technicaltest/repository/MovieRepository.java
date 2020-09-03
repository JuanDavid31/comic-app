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

    private MovieDao movieDao;
    private MutableLiveData<List<Movie>> moviesLiveData;

    public MovieRepository(MovieDataBase db, MutableLiveData<List<Movie>> moviesLiveData){
        movieDao = db.movieDao();
        this.moviesLiveData = moviesLiveData;
    }

    public void refreshVideos(){
        ComicServiceHelper.getClient().getMovies().enqueue(new Callback<ResponseProperty>() {

            //@SuppressLint("NewApi")//TODO: Borrar cuando quite el forEach
            @Override
            public void onResponse(Call<ResponseProperty> call, Response<ResponseProperty> response) {
                ResponseProperty responseProperty = response.body();
                List<MovieProperty> movieProperties = responseProperty.movies;
                //movieProperties.forEach(movie -> Log.i("MovieVideoRepository", movie.toString()));
                Log.d("MovieVideoRepository", responseProperty.toString());
                List<Movie> movies = MapperUtils.mapMoviePropertiesToMovie(movieProperties);
                moviesLiveData.setValue(movies);
            }

            @Override
            public void onFailure(Call<ResponseProperty> call, Throwable t) {
                Log.e("MovieVideoRepository", "Failure: " + t.getMessage());
            }
        });
    }
}