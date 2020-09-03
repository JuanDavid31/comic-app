package com.example.technicaltest.util;

import com.example.technicaltest.database.Movie;
import com.example.technicaltest.network.property.MovieProperty;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapperUtils {

    public static List<Movie> mapMoviePropertiesToMovie(List<MovieProperty> movieProperties) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (MovieProperty movieProperty : movieProperties) {
            Movie movie = new Movie();
            movie.id = movieProperty.id;
            movie.name = movieProperty.name;
            movie.description = movieProperty.description;
            movie.thumbImageUrl = movieProperty.image.thumbUrl;
            movie.posterImageUrl = movieProperty.image.smallUrl;
            movie.rating = movieProperty.rating;
            movie.releaseDate = movieProperty.releaseDate;
            movie.budget = movieProperty.budget;
            movie.totalRevenue = movieProperty.totalRevenue;
            movies.add(movie);
        }
        return movies;
    }

    public static String formatDate(String initialDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(initialDate);
            System.out.println(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = new Date();
            return dateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String stringNumberToHumanReadableNumber(String number){
        int i = Integer.parseInt(number);
        return NumberFormat.getIntegerInstance().format(i);
    }
}
