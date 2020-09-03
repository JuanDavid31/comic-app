package com.example.technicaltest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM MOVIE")
    LiveData<List<Movie>> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Movie... movie);
}
