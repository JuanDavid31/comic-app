package com.example.technicaltest.db;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.technicaltest.database.Movie;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MovieDaoTest extends DbTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    public InstantTaskExecutorRule getInstantTaskExecutorRule() {
        return instantTaskExecutorRule;
    }

    @Test
    public void insertAndRead() throws InterruptedException {
        Movie movie = new Movie(1, "test1");

        db.movieDao().insertAll(movie);
        List<Movie> value = LiveDataTestUtil.getOrAwaitValue(db.movieDao().getMovies());
        assertEquals(value.size(), 1);
    }
}
