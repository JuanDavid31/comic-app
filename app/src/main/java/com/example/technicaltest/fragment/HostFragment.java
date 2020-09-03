package com.example.technicaltest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.technicaltest.R;
import com.example.technicaltest.viewmodel.HostFragmentViewModel;
import com.example.technicaltest.viewmodel.ViewModelFactory;


public class HostFragment extends Fragment {

    private HostFragmentViewModel vm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_host, container, false);

        ViewModelFactory vmFactory = new ViewModelFactory(getContext());
        vm = new ViewModelProvider(this, vmFactory).get(HostFragmentViewModel.class);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(new RecyclerViewAdapter.AdapterDiffCallback(), this);

        RecyclerView recyclerView = (RecyclerView)fragment.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);

        vm.getMovies().observe(getViewLifecycleOwner(), adapter::setMovies);

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}