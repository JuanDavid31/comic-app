package com.example.technicaltest.repository;

import android.annotation.SuppressLint;
import android.content.Context;
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

    private Context context;

    public MovieRepository(Context context, MovieDataBase db){
        this.context = context;
        movieDao = db.movieDao();
    }

    public LiveData<LoadingResult> refreshVideos(){
        MutableLiveData<LoadingResult> loadingResult = new MutableLiveData<LoadingResult>();
        ComicServiceHelper.getClient(context).getMovies().enqueue(new Callback<ResponseProperty>() {
            @Override
            public void onResponse(Call<ResponseProperty> call, Response<ResponseProperty> response) {
                ResponseProperty responseProperty = response.body();
                List<MovieProperty> movieProperties = responseProperty.movies;
                Movie[] movies = MapperUtils.mapMoviePropertiesToMovie(movieProperties);
                loadingResult.setValue(LoadingResult.SUCCESS);
                MovieDataBase.databaseWriteExecutor.execute(() -> movieDao.insertAll(movies));
            }

            @Override
            public void onFailure(Call<ResponseProperty> call, Throwable t) {
                Log.e("MovieVideoRepository", "Failure: " + t.getMessage());
                loadingResult.setValue(LoadingResult.ERROR);
            }
        });

        return loadingResult;
    }
}