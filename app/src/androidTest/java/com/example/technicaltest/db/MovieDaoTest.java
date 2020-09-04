package com.example.technicaltest.db;

import androidx.arch.core.executor.testing.CountingTaskExecutorRule;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.database.MovieDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MovieDaoTest {
    private MovieDataBase db;

    @Rule
    public CountingTaskExecutorRule countingTaskExecutorRule = new CountingTaskExecutorRule();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    public InstantTaskExecutorRule getInstantTaskExecutorRule() {
        return instantTaskExecutorRule;
    }

    @Before
    public void initDb() {
        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                MovieDataBase.class
        ).build();
    }

    @After
    public void closeDb() throws TimeoutException, InterruptedException {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS);
        db.close();
    }

    @Test
    public void insertAndRead() throws InterruptedException {
        Movie movie = new Movie(1, "test1");

        db.movieDao().insertAll(movie);
        List<Movie> value = LiveDataTestUtil.getOrAwaitValue(db.movieDao().getMovies());
        assertEquals(value.size(), 1);
    }
}
