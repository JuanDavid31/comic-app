package com.example.technicaltest.fragment;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.technicaltest.R;
import com.example.technicaltest.database.Movie;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends ListAdapter<Movie, RecyclerViewAdapter.ItemViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private Fragment fragment;

    public RecyclerViewAdapter(AdapterDiffCallback adapterDiffCallback, Fragment fragment){
        super(adapterDiffCallback);
        this.fragment = fragment;
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ItemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        holder.movieName.setText(currentMovie.getName());
        /*holder.movieDescription
        holder.movieDescription.setText(currentMovie.getDescription());*/

        Glide.with(holder.movieThumbnail.getContext())
                .load(currentMovie.getThumbImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(holder.movieThumbnail);

        holder.root.setOnClickListener(view -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(HostFragmentDirections.actionHostFragmentToDetailFragment(currentMovie));
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{

        private View root;
        private ImageView movieThumbnail;
        private TextView movieName;
        //private TextView movieDescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;
            movieThumbnail = itemView.findViewById(R.id.movie_item_thumbnail_image);
            movieName = itemView.findViewById(R.id.movie_item_title_text);
            //movieDescription = itemView.findViewById(R.id.movie_item_description_text);
        }
    }

    static class AdapterDiffCallback extends DiffUtil.ItemCallback<Movie> {

        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }

}
