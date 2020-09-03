package com.example.technicaltest.util;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.network.property.MovieProperty;

import java.util.ArrayList;
import java.util.List;

public class MapperUtils {

    public static List<Movie> mapMoviePropertiesToMovie(List<MovieProperty> movieProperties) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (MovieProperty movieProperty : movieProperties) {
            int id = movieProperty.id;
            String name = movieProperty.name;
            String description = movieProperty.description;
            String thumbImgUrl = movieProperty.image.thumbUrl;
            String smallImgUrl = movieProperty.image.smallUrl;
            movies.add(new Movie(id, name, description, thumbImgUrl, smallImgUrl));
        }
        return movies;
    }
}
