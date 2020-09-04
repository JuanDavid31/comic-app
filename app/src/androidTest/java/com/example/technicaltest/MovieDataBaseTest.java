package com.example.technicaltest;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.database.MovieDao;
import com.example.technicaltest.database.MovieDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MovieDataBaseTest {

    private MovieDao movieDao;
    private MovieDataBase db;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MovieDataBase.class).build();
        movieDao = db.movieDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        Movie movie = new Movie(1, "test1");
        Movie movie2 = new Movie(1, "test1");

        movieDao.insertAll(movie, movie2);
        Thread.sleep(3000);
        List<Movie> value = movieDao.getMovies().getValue();
        value.stream().forEach(movie1 -> Log.i("MovieDatabaseTest", movie1.toString()));
    }
}
