package com.example.technicaltest.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.database.MovieDao;
import com.example.technicaltest.database.MovieDataBase;
import com.example.technicaltest.repository.MovieRepository;

import java.util.List;

public class HostFragmentViewModel extends ViewModel {

    private MovieDao movieDao;

    public LiveData<List<Movie>> getMovies(){
        return movieDao.getMovies();
    }

    private LiveData<MovieRepository.LoadingResult> loadingResult;

    public HostFragmentViewModel(Context context){
        MovieDataBase db = MovieDataBase.getInstance(context);
        MovieRepository movieRepository = new MovieRepository(context, db);
        movieDao = db.movieDao();
        loadingResult = movieRepository.refreshVideos();
    }

    public LiveData<MovieRepository.LoadingResult> getLoadingResult() {
        return loadingResult;
    }
}
