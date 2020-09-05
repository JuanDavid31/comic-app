package com.example.technicaltest.db;

import androidx.arch.core.executor.testing.CountingTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.technicaltest.database.MovieDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class DbTest {

    MovieDataBase db;

    @Rule
    public CountingTaskExecutorRule countingTaskExecutorRule = new CountingTaskExecutorRule();

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
}
