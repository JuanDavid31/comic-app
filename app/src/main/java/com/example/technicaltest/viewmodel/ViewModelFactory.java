package com.example.technicaltest.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public ViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == HostFragmentViewModel.class){
            return (T) new HostFragmentViewModel(context);
        }
        throw new IllegalArgumentException("Impossible to instantiate class");
    }
}
