package com.example.technicaltest.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.technicaltest.R;
import com.example.technicaltest.database.Movie;
import com.example.technicaltest.util.MapperUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetailFragment extends Fragment {

    private Movie movie;

    public DetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailFragmentArgs detailFragmentArgs = DetailFragmentArgs.fromBundle(requireArguments());
        movie = detailFragmentArgs.getMovie();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ((TextView)view.findViewById(R.id.movie_detail_name)).setText(movie.name);
        ((TextView)view.findViewById(R.id.movie_detail_description)).setText(movie.description);
        ((TextView)view.findViewById(R.id.movie_detail_rating)).setText(movie.rating);
        ((TextView)view.findViewById(R.id.movie_detail_release_date)).setText(String.format("Release date: %s", MapperUtils.formatDate(movie.releaseDate)));
        ((TextView)view.findViewById(R.id.movie_detail_budget)).setText(String.format("Budget: %s", MapperUtils.stringNumberToHumanReadableNumber(movie.budget)));
        ((TextView)view.findViewById(R.id.movie_detail_total_revenue)).setText(String.format("Total revenue: %s", MapperUtils.stringNumberToHumanReadableNumber(movie.totalRevenue)));
        ImageView posterImage = view.findViewById(R.id.movie_detail_image);

        Glide.with(posterImage.getContext())
                .load(movie.posterImageUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(posterImage);

        return view;
    }
}