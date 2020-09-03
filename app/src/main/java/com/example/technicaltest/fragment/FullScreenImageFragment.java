package com.example.technicaltest.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.technicaltest.R;

public class FullScreenImageFragment extends Fragment {

    private String imgUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreenImageFragmentArgs fullscreenImageFragmentArgs = FullScreenImageFragmentArgs.fromBundle(requireArguments());
        imgUrl = fullscreenImageFragmentArgs.getImgUrl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        ImageView imageView = root.findViewById(R.id.movie_full_image);

        Glide.with(imageView.getContext())
                .load(imgUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imageView);

        return root;
    }
}