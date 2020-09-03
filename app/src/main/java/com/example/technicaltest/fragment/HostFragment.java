package com.example.technicaltest.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.technicaltest.R;
import com.example.technicaltest.database.Movie;
import com.example.technicaltest.databinding.FragmentHostBinding;
import com.example.technicaltest.repository.MovieRepository;
import com.example.technicaltest.viewmodel.HostFragmentViewModel;
import com.example.technicaltest.viewmodel.ViewModelFactory;

import java.util.List;


public class HostFragment extends Fragment {

    private View loadingPanel;
    private View connectionErrorImage;

    private RecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHostBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_host, container, false);

        ViewModelFactory vmFactory = new ViewModelFactory(getContext());
        HostFragmentViewModel vm = new ViewModelProvider(this, vmFactory).get(HostFragmentViewModel.class);

        adapter = new RecyclerViewAdapter(new RecyclerViewAdapter.AdapterDiffCallback(), this);

        loadingPanel = binding.loadingPanel;
        connectionErrorImage = binding.connectionErrorImage;
        RecyclerView recyclerView = binding.recyclerView;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);

        vm.getMovies().observe(getViewLifecycleOwner(), this::observeMovies);
        vm.getLoadingResult().observe(getViewLifecycleOwner(), this::observeLoadingResult);

        return binding.getRoot();
    }

    private void observeMovies(List<Movie> movies){
        if(movies.size() == 0){
            Log.i("HostFragment", "No movies"); //Si se llama
        }else{
            loadingPanel.setVisibility(View.GONE);
            connectionErrorImage.setVisibility(View.GONE);
        }
        adapter.setMovies(movies);
    }

    private void observeLoadingResult(MovieRepository.LoadingResult result){
        loadingPanel.setVisibility(View.GONE);
        if(result == MovieRepository.LoadingResult.ERROR && adapter.getItemCount() == 0){
            connectionErrorImage.setVisibility(View.VISIBLE);
        }
    }
}