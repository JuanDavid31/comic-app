package com.example.technicaltest.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = Movie.class)
public abstract class MovieDataBase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDataBase INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MovieDataBase getInstance (Context context){
        synchronized (MovieDataBase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), MovieDataBase.class, "movies.db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return INSTANCE;
        }
    }
}