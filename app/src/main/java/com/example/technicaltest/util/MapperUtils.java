package com.example.technicaltest.util;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.network.property.MovieProperty;

import java.text.NumberFormat;
import java.util.List;

public class MapperUtils {

    public static Movie[] mapMoviePropertiesToMovie(List<MovieProperty> movieProperties) {
        Movie[] movies = new Movie[movieProperties.size()];
        for (int i = 0; i < movieProperties.size(); i++) {
            MovieProperty movieProperty = movieProperties.get(i);
            Movie movie = new Movie();
            movie.id = movieProperty.id;
            movie.name = movieProperty.name;
            movie.description = movieProperty.description != null ? movieProperty.description : "";
            movie.thumbImageUrl = movieProperty.image.thumbUrl != null ? movieProperty.image.thumbUrl : "";
            movie.posterImageUrl = movieProperty.image.smallUrl != null ? movieProperty.image.smallUrl : "";
            movie.rating = movieProperty.rating != null ? movieProperty.rating : "";
            movie.runTime = movieProperty.runTime != null ? movieProperty.runTime : "";
            movie.budget = movieProperty.budget != null ? movieProperty.budget : "";
            movie.boxOfficeRevenue = movieProperty.boxOfficeRevenue != null ? movieProperty.boxOfficeRevenue : "";
            movie.totalRevenue = movieProperty.totalRevenue != null ? movieProperty.totalRevenue : "";
            movies[i] = movie;
        }
        return movies;
    }

    public static String stringNumberToHumanReadableMoney(String number){
        String humanReadableNumber = "";
        try{
            int i = Integer.parseInt(number);
            humanReadableNumber = NumberFormat.getIntegerInstance().format(i);
        }catch (NumberFormatException e){
            humanReadableNumber = number;
        }

        if(humanReadableNumber.trim().startsWith("$")){
            return humanReadableNumber;
        }else{
            return String.format("$%s", humanReadableNumber);
        }
    }
}
