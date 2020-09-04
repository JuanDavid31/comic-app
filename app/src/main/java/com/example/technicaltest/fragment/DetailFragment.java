package com.example.technicaltest.fragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.technicaltest.R;
import com.example.technicaltest.database.Movie;
import com.example.technicaltest.databinding.FragmentDetailBinding;
import com.example.technicaltest.util.MapperUtils;

public class DetailFragment extends Fragment {

    private Movie movie;

    FragmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailFragmentArgs detailFragmentArgs = DetailFragmentArgs.fromBundle(requireArguments());
        movie = detailFragmentArgs.getMovie();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        binding.movieDetailName.setText(movie.name);
        binding.movieDetailDescription.setText(getHtml(movie.description));
        binding.movieDetailRating.setText(getHtml(String.format("<b>%s</b>", movie.rating)));

        if(movie.runTime.equals("")){
            binding.movieDetailRuntime.setText(getHtml("<b>%s</b> No info.", "Duration:"));
        }else{
            binding.movieDetailRuntime.setText(getHtml("<b>Duration:</b> %s minutes", movie.runTime));
        }

        binding.movieDetailBudget.setText(getHtml("<b>Budget:</b> %s", MapperUtils.stringNumberToHumanReadableMoney(movie.budget)));
        binding.movieDetailBoxOfficeRevenue.setText(getHtml("<b>B.O. revenue:</b> %s", MapperUtils.stringNumberToHumanReadableMoney(movie.boxOfficeRevenue)));
        binding.movieDetailTotalRevenue.setText(getHtml("<b>Total revenue:</b> %s", MapperUtils.stringNumberToHumanReadableMoney(movie.totalRevenue)));
        ImageView posterImage = binding.movieDetailImage;
        loadImage(posterImage);

        posterImage.setOnClickListener(this::onPosterImageClick);

        return binding.getRoot();
    }

    private Spanned getHtml(String html){
        Spanned spannedHtml;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spannedHtml = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        } else {
            spannedHtml = Html.fromHtml(html);
        }
        return spannedHtml;
    }

    private Spanned getHtml(String format, String html){
        Spanned spannedHtml;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spannedHtml = Html.fromHtml(String.format(format, html), Html.FROM_HTML_MODE_COMPACT);
        } else {
            spannedHtml = Html.fromHtml(String.format(format, html));
        }
        return spannedHtml;
    }

    private void loadImage(ImageView imageView){
        Glide.with(imageView.getContext())
                .load(movie.posterImageUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imageView);
    }

    private void onPosterImageClick(View view){
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
    }
}