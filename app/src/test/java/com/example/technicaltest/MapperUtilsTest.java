package com.example.technicaltest;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.network.property.MovieProperty;
import com.example.technicaltest.util.MapperUtils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MapperUtilsTest {
    @Test
    public void stringNumberToHumanReadeableMoney() {
        String newNumber = MapperUtils.stringNumberToHumanReadableMoney("1000");
        assertEquals(newNumber, "$1.000");
    }

    @Test
    public void mapMoviePropertiesToMovie() {
        MovieProperty movieProperty1 = new MovieProperty(1, "test1");
        MovieProperty movieProperty2 = new MovieProperty(2, "test2");

        ArrayList movies = new ArrayList() {{
            add(movieProperty1);
            add(movieProperty2);
        }};

        Movie[] newMovies = MapperUtils.mapMoviePropertiesToMovie(movies);
        assertEquals(newMovies[0].id, 1);
        assertEquals(newMovies[0].name, "test1");
        assertEquals(newMovies[1].id, 2);
        assertEquals(newMovies[1].name, "test2");
    }
}