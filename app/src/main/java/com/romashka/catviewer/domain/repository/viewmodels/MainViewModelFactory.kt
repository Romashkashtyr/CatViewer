package com.romashka.catviewer.domain.repository.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress()
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown viewModel")
    }
}
