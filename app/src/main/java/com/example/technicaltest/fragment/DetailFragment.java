package com.example.technicaltest.fragment;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.technicaltest.R;
import com.example.technicaltest.database.Movie;
import com.example.technicaltest.util.MapperUtils;

import java.util.Objects;

public class DetailFragment extends Fragment {

    private Movie movie;

    boolean isImageFitToScreen;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Spanned spannedDescription = Html.fromHtml(movie.description, Html.FROM_HTML_MODE_COMPACT);
            ((TextView)view.findViewById(R.id.movie_detail_description)).setText(spannedDescription);
        } else {
            Spanned spannedDescription = Html.fromHtml(movie.description);
            ((TextView)view.findViewById(R.id.movie_detail_description)).setText(spannedDescription);
        }
        ((TextView)view.findViewById(R.id.movie_detail_rating)).setText(movie.rating);
        if(movie.runTime.equals("")){
            ((TextView)view.findViewById(R.id.movie_detail_runtime)).setText("Duration:");
        }else{
            ((TextView)view.findViewById(R.id.movie_detail_runtime)).setText(String.format("Duration: %s minutes", movie.runTime));
        }

        ((TextView)view.findViewById(R.id.movie_detail_budget)).setText(String.format("Budget: %s", MapperUtils.stringNumberToHumanReadableMoney(movie.budget)));
        ((TextView)view.findViewById(R.id.movie_detail_box_office_revenue)).setText(String.format("B.O. revenue: %s", MapperUtils.stringNumberToHumanReadableMoney(movie.boxOfficeRevenue)));
        ((TextView)view.findViewById(R.id.movie_detail_total_revenue)).setText(String.format("Total revenue: %s", MapperUtils.stringNumberToHumanReadableMoney(movie.totalRevenue)));
        ImageView posterImage = (ImageView) view.findViewById(R.id.movie_detail_image);
        loadImage(posterImage);

        posterImage.setOnClickListener(view1 -> {
            final Dialog dialog = new Dialog(requireContext());

            dialog.setContentView(R.layout.image_modal);

            ImageView modalImage = dialog.findViewById(R.id.modal_image);
            Button goFullScreenButton = dialog.findViewById(R.id.go_full_screen_button);
            Button closeModalButton = dialog.findViewById(R.id.close_button);

            loadImage(modalImage);
            goFullScreenButton.setOnClickListener(v -> {
                dialog.dismiss();
                NavHostFragment.findNavController(this)
                    .navigate(DetailFragmentDirections.actionDetailFragmentToFullScreenImageFragment(movie.posterImageUrl));
            });
            closeModalButton.setOnClickListener(v -> dialog.dismiss());

            dialog.show();
        });

        return view;
    }

    public void loadImage(ImageView imageView){
        Glide.with(imageView.getContext())
                .load(movie.posterImageUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imageView);
    }
}