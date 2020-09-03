package com.example.technicaltest.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.database.MovieDataBase;
import com.example.technicaltest.repository.MovieRepository;

import java.util.List;

public class HostFragmentViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<List<Movie>> movies = new MutableLiveData();
    public LiveData<List<Movie>> getMovies(){
        return movies;
    }

    private LiveData<MovieRepository.LoadingResult> loadingResult;

    public HostFragmentViewModel(Context context){
        movieRepository = new MovieRepository(MovieDataBase.getInstance(context), movies); //TODO: Eliminar despues
        loadingResult = movieRepository.refreshVideos();
    }

    public LiveData<MovieRepository.LoadingResult> getLoadingResult() {
        return loadingResult;
    }
}
