package com.example.technicaltest.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.technicaltest.R;
import com.example.technicaltest.database.Movie;

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

        return view;
    }
}